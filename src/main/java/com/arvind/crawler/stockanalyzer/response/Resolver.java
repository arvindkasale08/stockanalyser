package com.arvind.crawler.stockanalyzer.response;

import java.util.List;

import org.jsoup.nodes.Document;

public interface Resolver {

	String getUrl();

	List<String> resolve(Document input);
}
