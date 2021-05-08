package com.arvind.crawler.stockanalyzer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
	private String session_id;
	private String date;
	private int available_capacity;
	private int min_age_limit;
	private String vaccine;
}
