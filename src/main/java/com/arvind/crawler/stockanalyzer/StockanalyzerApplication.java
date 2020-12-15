package com.arvind.crawler.stockanalyzer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class StockanalyzerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StockanalyzerApplication.class, args);
		StockRunner runner = context.getBean(StockRunner.class);

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
		executorService.scheduleAtFixedRate(() -> runner.run()
			,
			1,
			30,
			TimeUnit.MINUTES);

		ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(4);
		executorService.scheduleAtFixedRate(() -> {
			log.info("...........................Health check...........................");
			}
			,
			1,
			2,
			TimeUnit.MINUTES);

	}

}
