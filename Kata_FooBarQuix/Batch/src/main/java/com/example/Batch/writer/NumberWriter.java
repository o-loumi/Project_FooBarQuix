package com.example.Batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Component
public class NumberWriter implements ItemWriter<String> {

    @Value("${output.file.path:output.txt}")
    private String outputFilePath;

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        try (Writer writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            // Itérer sur chaque élément du chunk et les écrire dans le fichier
            for (String item : chunk) {
                writer.write(item + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new Exception("Error writing to output file", e);
        }
    }
}
