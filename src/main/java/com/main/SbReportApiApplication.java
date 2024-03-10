package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.main.service.IEligibleService;

@SpringBootApplication
public class SbReportApiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SbReportApiApplication.class, args);
		/*IEligibleService service = ctx.getBean("eService", IEligibleService.class);
		service.getUniquePlanNames();
		service.getUniquePlanStatus();
		
		((ConfigurableApplicationContext) ctx).close();*/
	}

}
