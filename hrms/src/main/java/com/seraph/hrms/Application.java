package com.seraph.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
@SpringBootApplication
@ImportResource({"classpath:META-INF/spring/hibernate.xml", "classpath:META-INF/spring/beans.xml"})
public class Application {

	public static void main(String ... args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
