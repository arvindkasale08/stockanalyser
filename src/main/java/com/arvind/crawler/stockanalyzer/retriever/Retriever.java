package com.arvind.crawler.stockanalyzer.retriever;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Retriever {

	List<String> retrieve();

	default Document call(String url) {
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
