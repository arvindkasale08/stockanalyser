package com.arvind.crawler.stockanalyzer.retriever;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieverRepository {

	@Autowired
	private List<Retriever> retrieverList;

	public List<String> retrieve() {
		List<String> result = new ArrayList<>();
		retrieverList.stream()
			.forEach(retriever -> {
				result.addAll(retriever.retrieve());
			});
		return  result;
	}
}
