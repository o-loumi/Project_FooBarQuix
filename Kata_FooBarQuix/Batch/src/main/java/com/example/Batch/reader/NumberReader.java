package com.example.Batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;



@Component
public class NumberReader extends FlatFileItemReader<Integer> {

    public NumberReader(@Value("${input.file.path}") String filePath) {
        super();
        // Configure le fichier source
        this.setResource(new FileSystemResource(filePath));
        this.setName("numberReader");

        // Configure un line mapper personnalisé
        this.setLineMapper((line, lineNumber) -> {
            // Laisser la ligne brute et la convertir en entier
            return Integer.parseInt(line.trim());
        });
    }
}
