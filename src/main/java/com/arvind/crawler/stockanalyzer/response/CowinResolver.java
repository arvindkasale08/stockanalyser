package com.arvind.crawler.stockanalyzer.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.jsoup.nodes.Document;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@AllArgsConstructor
public class CowinResolver implements Resolver {

	private Date date;
	private static  final List<Integer> PUNE_PINCODES = Arrays.asList(111045, 410038, 411000, 411001, 411002, 411003, 411004, 411005, 411006, 411007, 411008, 411009, 411011, 411012, 411013, 411014, 411015, 411016, 411020, 411021, 411022, 411023, 411024, 411025, 411028, 411029, 411030, 411031, 411032, 411036, 411037, 411038, 411040, 411041, 411042, 411043, 411045, 411046, 411047, 411048, 411051, 411052, 411053, 411055, 411058, 411060, 411066, 411067, 411078, 411230, 412029, 412047, 412105, 412115, 412207, 412307, 412308, 413337);

	@Override
	public List<String> resolve(Document doc) {
		String data = doc.body().text();
		List<String> output = new ArrayList<>();
		if (data != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				SetuResponse response = mapper.readValue(data, SetuResponse.class);
				System.out.println("Received response: "+ response);
				if (!CollectionUtils.isEmpty(response.getCenters())) {
					System.out.println("Found centers");
					response.getCenters()
						.stream()
						.filter(center -> {
							//return true;
							return PUNE_PINCODES.contains(center.getPincode());
						})
						.forEach(center -> {
							if (hasSessions(center)) {
								output.add(template(center));
							}
						});
				}
			} catch (Exception e) {
				System.out.println("CONVERSION FAILED"+ e.getMessage());
			}
		}
		System.out.println(output);
		return output;
	}

	private String template(Center center) {
		/*return String.format("*Product Price:* %s\n *Product In Stock:* %s \n *Product Url:* %s", productPrice, productInStock ? "*YES*" : "NO", center);*/
		return String.format("*Center Id:* %s \n " +
			"*Center Name:* %s \n " +
			"*Center Address:* %s \n " +
			"*Center Pincode:* %s \n " +
			"*Fee Type:* %s \n " +
			"*Session:*\n %s \n ",
			center.getCenter_id(),
			center.getName(),
			center.getAddress(),
			center.getPincode(),
			center.getFee_type(),
			getSession(center)
			);
	}

	private String getSession(Center center) {
		String session = "";
		for (Session centerSession : center.getSessions()) {
			session += "Date: " + centerSession.getDate().concat(",").concat(" Capacity: ").concat(String.valueOf(centerSession.getAvailable_capacity()).concat(",").concat(" Age: ").concat(String.valueOf(centerSession.getMin_age_limit())).concat("\n"));
		}
		return session;
	}

	private boolean hasSessions(Center center) {
		boolean hasSession = center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0).count() > 0;
		return hasSession;
	}



	@Override
	public String getUrl() {
		String districtId = "363";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String date = df.format(this.date);
		return String.format("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=%s&date=%s", districtId, date);
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
