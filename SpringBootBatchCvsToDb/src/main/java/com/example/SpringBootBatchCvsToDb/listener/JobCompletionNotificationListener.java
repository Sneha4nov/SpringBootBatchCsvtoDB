package com.example.SpringBootBatchCvsToDb.listener;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getBatchStatus() == BatchStatus.COMPLETED) {

		}
	}

}
