package com.arvind.crawler.stockanalyzer.retriever;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arvind.crawler.stockanalyzer.response.CowinResolver;
import com.arvind.crawler.stockanalyzer.response.Resolver;

@Component
public class CowinRetriever implements Retriever {

	@Autowired
	private Function<Date, CowinResolver> cowinResolverFactory;

	private List<Resolver> resolvers;

	@PostConstruct
	public void setup() {
		resolvers = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		resolvers.add(cowinResolverFactory.apply(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_WEEK, 7);
		resolvers.add(cowinResolverFactory.apply(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_WEEK, 14);
		resolvers.add(cowinResolverFactory.apply(calendar.getTime()));
	}

	@Override
	public List<String> retrieve() {
		// Call the resolver iteratively
		List<String> results = new ArrayList<>();
		resolvers.stream()
			// call the url in a resolver
			.forEach(resolver -> {
				Document document = call(resolver.getUrl());
				if(document != null) {
					results.addAll(resolver.resolve(document));
				}
			});
		return results;
	}
}
