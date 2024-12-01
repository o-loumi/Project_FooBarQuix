package com.example.Batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NumberTransformListener implements JobExecutionListener {

    public static final String HTTP_ERROR_COUNT = "httpErrorCount";
    public static final String PROCESSES_ITEMS_COUNT = "processedItems";

    private JobExecution jobExecution;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("--------------------------------------------------------------------");
        this.jobExecution = jobExecution;
        log.info("Job Started: {}", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
        logStepExecutionStats(stepExecution);
        log.info("--------------------------------------------------------------------");

    }

    private void logStepExecutionStats(StepExecution stepExecution) {
        long itemsRead = stepExecution.getReadCount();
        long itemsProcessed = (Integer) stepExecution.getExecutionContext().get(PROCESSES_ITEMS_COUNT);
        long itemsWritten = stepExecution.getWriteCount();
        long readSkipCount = stepExecution.getReadSkipCount();
        long writeSkipCount = stepExecution.getWriteSkipCount();

        // Log step statistics
        log.info("Step '{}' completed.", stepExecution.getStepName());
        log.info("Items read: {}", itemsRead);
        log.info("Items processed: {}", itemsProcessed);
        log.info("Items written: {}", itemsWritten);
        log.info("Items skipped during reading: {}", readSkipCount);
        log.info("Items skipped during writing: {}", writeSkipCount);

        // Retrieve and log HTTP error count if present
        Integer httpErrorCount = (Integer) jobExecution.getExecutionContext().get(HTTP_ERROR_COUNT);
        if (httpErrorCount != null) {
            log.info("HTTP Errors encountered: {}", httpErrorCount);
        } else {
            log.info("No HTTP errors encountered.");
        }
    }

}
