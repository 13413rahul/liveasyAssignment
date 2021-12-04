package com.livesy.internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class InternshipApplication {

	public static void main(String[] args) {

		SpringApplication.run(InternshipApplication.class, args);
	}

	/**
		// I used localHot(127.0.0.1)
		// 8090 is server post
		// Here, I used loads for identify loadController

		// 1. post "/load"			Api for this is http://127.0.0.1:8090/loads/load (POST Request)

		//2. GET “/load”			Api for this is http://127.0.0.1:8090/loads/load?shipperId = (any value)  (GET Request)

		   // 	1.	GET “load/{loadId}”		    Api for this is http://127.0.0.1:8090/loads/load/{loadId} (GET Request)

		  //	2.	PUT “load/{loadId}”			Api for this is http://127.0.0.1:8090/loads/load/{loadId} (PUT Request)

		  //	3.	DELETE “load/{loadId}”		Api for this is http://127.0.0.1:8090/loads/load/{loadId} (DELETE Request)

	 */


}
