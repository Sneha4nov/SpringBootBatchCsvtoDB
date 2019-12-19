package com.example.SpringBootBatchCvsToDb.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.SpringBootBatchCvsToDb.model.Person;

public class Writer implements ItemWriter<Person> {

	JdbcTemplate jdbcTemplate;

	public Writer(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void write(List<? extends Person> items) throws Exception {
		System.out.println("--------------WRITER --------------");
		jdbcTemplate.batchUpdate("insert into Person (first_name, last_name, email, age) values(?, ?, ?,?)",
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, items.get(i).getFirstName());
						ps.setString(2, items.get(i).getLastName());
						ps.setString(3, items.get(i).getEmail());
						ps.setInt(4, items.get(i).getAge());
					}

					public int getBatchSize() {
						return items.size();
					}

				});
	}

}
