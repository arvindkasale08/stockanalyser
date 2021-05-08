package com.arvind.crawler.stockanalyzer.retriever;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Retriever {

	List<String> retrieve();

	default Document call(String url) {
		System.out.println("Calling url: "+ url);
		try {
			return Jsoup.connect(url)
				.userAgent("Mozilla")
				.timeout(30000)
				.followRedirects(true)
				.referrer("http://google.com")
				.ignoreContentType(true)
				.get();
		} catch (IOException e) {
			System.out.println("Exception occured "+ e.getMessage() );
		}
		return null;
	}
}
