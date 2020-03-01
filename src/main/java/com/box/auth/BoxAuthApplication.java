package com.box.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages= {"com.box.**"})
@EnableAsync
@ServletComponentScan
public class BoxAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxAuthApplication.class, args);
	}

}
