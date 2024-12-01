package com.example.Batch.processor;

import com.example.Batch.job.NumberTransformListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class NumberProcessor implements ItemProcessor<Integer,String> {

    private int numberHttpErrors;
    private int processedItems;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public String process(Integer item) throws Exception {
        String transformedNumber = getTransformedNumber(item);
        processedItems++;
        return item + "\t" + transformedNumber;
    }

    private String getTransformedNumber(int number) throws URISyntaxException {
        String url = apiUrl + number;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        // Créer un client HTTP
        HttpClient client = HttpClient.newHttpClient();

        try {
            // Envoyer la requête et récupérer la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Vérifier la réponse HTTP
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                numberHttpErrors++;
                return "Error: " + response.statusCode();
            }
        } catch (Exception e) {
            numberHttpErrors++;
            return "Error: " + e.getMessage();
        }
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        stepExecution.getExecutionContext().put(NumberTransformListener.HTTP_ERROR_COUNT, numberHttpErrors);
        stepExecution.getExecutionContext().put(NumberTransformListener.PROCESSES_ITEMS_COUNT, processedItems);
    }
}
