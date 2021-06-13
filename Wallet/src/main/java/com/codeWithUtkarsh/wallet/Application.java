package com.codeWithUtkarsh.wallet;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) throws IOException
	{
		SpringApplication.run(Application.class, args);
	}
}
