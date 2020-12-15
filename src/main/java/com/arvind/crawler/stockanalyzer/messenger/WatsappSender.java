package com.arvind.crawler.stockanalyzer.messenger;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arvind.crawler.stockanalyzer.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Component
@Slf4j
public class WatsappSender {

	@Autowired
	private TwilioProperties properties;

	public void send(String body) {
		log.info("Sending message={} using properties={}", body, properties);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Twilio.init(properties.getAccountId(), properties.getAuth());
		Message message = Message.creator(
			new com.twilio.type.PhoneNumber(properties.getTo()),
			new com.twilio.type.PhoneNumber(properties.getFrom()),
			body)
			.create();
		System.out.println(message);
	}
}
