package com.arvind.crawler.stockanalyzer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

	@GetMapping("/health")
	public String health() {
		return "OK";
	}

}
