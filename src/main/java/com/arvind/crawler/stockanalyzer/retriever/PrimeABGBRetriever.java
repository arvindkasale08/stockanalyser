package com.arvind.crawler.stockanalyzer.retriever;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arvind.crawler.stockanalyzer.response.PrimeABGB3080Resolver;
import com.arvind.crawler.stockanalyzer.response.PrimeABGBAMDResolver;
import com.arvind.crawler.stockanalyzer.response.Resolver;

@Component
public class PrimeABGBRetriever implements Retriever {

	@Autowired
	private PrimeABGB3080Resolver primeABGB3080Resolver;
	@Autowired
	private PrimeABGBAMDResolver primeABGBAMDResolver;

	private List<Resolver> resolvers;

	@PostConstruct
	public void setup() {
		resolvers = new ArrayList<>();
		resolvers.add(primeABGBAMDResolver);
		resolvers.add(primeABGB3080Resolver);
	}

	@Override
	public List<String> retrieve() {
		// Call the resolver iteratively
		List<String> results = new ArrayList<>();
		resolvers.stream()
			// call the url in a resolver
			.forEach(resolver -> {
				Document document = call(resolver.getUrl());
				results.addAll(resolver.resolve(document));
			});
		return results;
	}
}
