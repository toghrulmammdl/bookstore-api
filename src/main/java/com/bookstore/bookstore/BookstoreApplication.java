package com.bookstore.bookstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BookstoreApplication {

	public static void main(String[] args) {
		try{
			Dotenv dotenv = Dotenv.load();
			dotenv.entries().forEach(entry->System.setProperty(entry.getKey(), entry.getValue()));

		} catch (io.github.cdimascio.dotenv.DotenvException e){
			System.err.println("Warning: .env file not found or could not be loaded. Ensure environment variables are set externally if this is not a development environment.");
		}

		SpringApplication.run(BookstoreApplication.class, args);

	}

}
