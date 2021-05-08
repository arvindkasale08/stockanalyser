package com.arvind.crawler.stockanalyzer.retriever;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arvind.crawler.stockanalyzer.response.SetuResponse;

@Component
public class AarogyaSetuRetriever implements JsonRetriever<SetuResponse> {

	public static final String URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=%s&date=%s";
	public static final int DISTRICT = 363;

	@Override
	public List<String> retrieve() {
		SetuResponse setuResponse = call(URL, SetuResponse.class);
		System.out.println(setuResponse);
		return null;
	}
}
