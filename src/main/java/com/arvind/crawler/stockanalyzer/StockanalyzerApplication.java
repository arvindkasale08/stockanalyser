package com.arvind.crawler.stockanalyzer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class StockanalyzerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StockanalyzerApplication.class, args);
		StockRunner runner = context.getBean(StockRunner.class);

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
		executorService.scheduleAtFixedRate(() -> runner.run()
			,
			1,
			2,
			TimeUnit.MINUTES);
	}

}
