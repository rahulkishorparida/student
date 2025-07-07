package com.sms.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//@SpringBootApplication(
//	    exclude = {
//	        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
//	        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
//	    }
//	)
@SpringBootApplication(exclude = {
	    HibernateJpaAutoConfiguration.class,
	    DataSourceAutoConfiguration.class
	})

public class StudentmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementsystemApplication.class, args);
	}

}
