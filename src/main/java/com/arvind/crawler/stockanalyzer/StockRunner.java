package com.arvind.crawler.stockanalyzer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arvind.crawler.stockanalyzer.messenger.WatsappSender;
import com.arvind.crawler.stockanalyzer.retriever.RetrieverRepository;

@Slf4j
@Component
public class StockRunner{

	@Autowired
	private WatsappSender sender;
	@Autowired
	private RetrieverRepository retrieverRepository;

	public void run() {
		log.info("Running stock runner to get latest stock information at {}", LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
		// Get information from crawler and send it to watsapp
		List<String> data = retrieverRepository.retrieve();
		data.stream()
			.forEach(s -> sender.send(s));
	}
}
