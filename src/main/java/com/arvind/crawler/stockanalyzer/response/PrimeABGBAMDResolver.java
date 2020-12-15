package com.arvind.crawler.stockanalyzer.response;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PrimeABGBAMDResolver implements Resolver {

	@Override
	public List<String> resolve(Document doc) {
		Elements products = doc.select(".product-item");
		List<String> result = new ArrayList<>();
		products.forEach(product -> {
			String productName = "NA";
			boolean productInStock = false;
			String productPrice = "NA";
			String productHref = "http://google.com";
			if (product != null) {
				Element priceElement = product.select(".price").get(0);
				if (priceElement != null) {
					productPrice = priceElement.text();
				}
				Element productNameElement = product.select(".product-name").get(0);
				if (productNameElement != null) {
					productName = productNameElement.text();
				}
				Element productHrefElement = product.select(".thumb-link").get(0);
				if (productHrefElement != null) {
					productHref = productHrefElement.attr("href").trim();
				}

				Elements outOfStockElements = product.select(".out-of-stock");
				if (outOfStockElements.isEmpty()) {
					productInStock = true;
				}
			}
			result.add(template(productName, productPrice, productHref, productInStock));

		});
		return result;
	}

	private String template(String productName, String productPrice, String productHref, boolean productInStock) {
		return String.format("*Product Price:* %s\n *Product In Stock:* %s \n *Product Url:* %s", productPrice, productInStock ? "*YES*" : "NO", productHref);

	}

	@Override
	public String getUrl() {
		return "https://www.primeabgb.com/processor-series/ryzen-5000-series/?pa-brand=amd";
	}
}
