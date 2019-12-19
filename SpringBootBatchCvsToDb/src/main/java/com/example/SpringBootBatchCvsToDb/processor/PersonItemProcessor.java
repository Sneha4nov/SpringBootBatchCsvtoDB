package com.example.SpringBootBatchCvsToDb.processor;
import org.springframework.batch.item.ItemProcessor;

import com.example.SpringBootBatchCvsToDb.model.Person;


public class PersonItemProcessor implements  ItemProcessor<Person, Person> {

	//private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	@Override
	public Person process(final Person person) throws Exception {
		System.out.println("-----------------PROCESSOR ----------------");
		person.setFirstName(person.getFirstName().toUpperCase());
		person.setLastName(person.getLastName().toUpperCase());
		
		final Person transformedPerson = new Person(person.getFirstName(), person.getLastName(), person.getEmail(), person.getAge());
		
		//log.info("Converting (" + person + ")into(" + transformedPerson + ")");
		return transformedPerson;
		
	}
	
}
