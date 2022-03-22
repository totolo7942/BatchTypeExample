package com.example.batchtypeexample;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableBatchProcessing
public class BatchTypeExampleApplication {

	public static void main(String[] args) {
		System.out.println("init boot");
		SpringApplication.run(BatchTypeExampleApplication.class, args);
	}

}
