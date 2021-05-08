package com.arvind.crawler.stockanalyzer.retriever;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface JsonRetriever<P> {

	List<String> retrieve();

	default P call(String url, Class<P> pClass) {
		try {
			System.out.println("Calling url "+ url);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<P> entity = restTemplate.getForEntity(url, pClass);
			if (entity.getStatusCode() == HttpStatus.OK) {
				return entity.getBody();
			}
		} catch (Exception e) {
			System.out.println("Exception occured "+ e.getMessage() );
		}
		return null;
	}
}
