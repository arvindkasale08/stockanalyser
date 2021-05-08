package com.arvind.crawler.stockanalyzer.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Center {

	private String center_id;
	private String name;
	private String address;
	private String state_name;
	private String  district_name;
	private int pincode;
	private String fee_type;
	private List<Session> sessions;
}
