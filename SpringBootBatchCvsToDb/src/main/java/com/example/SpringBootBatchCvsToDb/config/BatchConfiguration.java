package com.example.SpringBootBatchCvsToDb.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.SpringBootBatchCvsToDb.listener.JobCompletionNotificationListener;
import com.example.SpringBootBatchCvsToDb.model.Person;
import com.example.SpringBootBatchCvsToDb.processor.PersonItemProcessor;
import com.example.SpringBootBatchCvsToDb.reader.Reader;
import com.example.SpringBootBatchCvsToDb.writer.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Bean
	public Reader reader() throws UnexpectedInputException, ParseException, Exception {
		return new Reader();
	}

	@Bean
	public PersonItemProcessor processor1() {
		return new PersonItemProcessor();
	}

	@Bean
	public Writer writer() {
		return new Writer(jdbcTemplate);
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener)
			throws UnexpectedInputException, ParseException, Exception {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() throws UnexpectedInputException, ParseException, Exception {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader()).processor(processor1())
				.writer(writer()).build();
	}

}
