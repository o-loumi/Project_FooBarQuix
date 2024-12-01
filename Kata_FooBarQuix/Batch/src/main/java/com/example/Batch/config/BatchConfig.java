package com.example.Batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Value("${chunk.size}") // Default to 10 if not set
    private int chunkSize;

    @Bean
    public Job numberBatchJob(Step step, JobExecutionListener listener) {
        return new JobBuilder("numberBatchJob", jobRepository)
                .start(step)
                .listener(listener)
                .build();
    }

    @Bean
    public Step step(ItemReader<Integer> reader, ItemProcessor<Integer, String> processor, ItemWriter<String> writer) {
        return new StepBuilder("step", jobRepository)
                .<Integer, String>chunk(chunkSize, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant() // Enable fault tolerance to allow item skipping
                .skip(Exception.class)  // Skip items that cause an exception
                .skipLimit(10)  // Limit the number of items to skip per chunk
                .build();
    }
}
