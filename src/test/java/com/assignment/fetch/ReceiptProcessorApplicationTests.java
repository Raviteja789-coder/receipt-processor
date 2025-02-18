package com.assignment.fetch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReceiptProcessorApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAddReceipt() throws Exception {
		String receiptJson = "{\"retailer\": \"Target\", \"purchaseDate\": \"2022-01-01\", \"purchaseTime\": \"13:01\", \"items\": [ {\"shortDescription\": \"Mountain Dew 12PK\", \"price\": \"6.49\"}, { \"shortDescription\": \"Emils Cheese Pizza\", \"price\": \"12.25\"}, {\"shortDescription\": \"Knorr Creamy Chicken\", \"price\": \"1.26\"}, {\"shortDescription\": \"Doritos Nacho Cheese\", \"price\": \"3.35\"}, {\"shortDescription\": \"   Klarbrunn 12-PK 12 FL OZ  \", \"price\": \"12.00\"}], \"total\": \"35.35\"}";

		ResultActions result = mvc.perform(post("/receipts/process")
				.contentType(MediaType.APPLICATION_JSON)
				.content(receiptJson));
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void testGetReceiptPoints_1() throws Exception {
		String receiptJson = "{\"retailer\": \"Target\", \"purchaseDate\": \"2022-01-01\", \"purchaseTime\": \"13:01\", \"items\": [ {\"shortDescription\": \"Mountain Dew 12PK\", \"price\": \"6.49\"}, { \"shortDescription\": \"Emils Cheese Pizza\", \"price\": \"12.25\"}, {\"shortDescription\": \"Knorr Creamy Chicken\", \"price\": \"1.26\"}, {\"shortDescription\": \"Doritos Nacho Cheese\", \"price\": \"3.35\"}, {\"shortDescription\": \"   Klarbrunn 12-PK 12 FL OZ  \", \"price\": \"12.00\"}], \"total\": \"35.35\"}";

		mvc.perform(post("/receipts/process")
				.contentType(MediaType.APPLICATION_JSON)
				.content(receiptJson));

		ResultActions result = mvc.perform(get("/receipts/2/points"));
		result.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.points").value(28));
	}

	@Test
	public void testGetReceiptPoints_2() throws Exception {
		String receiptJson = "{\"retailer\": \"M&M Corner Market\", \"purchaseDate\": \"2022-03-20\", \"purchaseTime\": \"14:33\", \"items\": [ {\"shortDescription\": \"Gatorade\", \"price\": \"2.25\"}, {\"shortDescription\": \"Gatorade\", \"price\": \"2.25\"}, {\"shortDescription\": \"Gatorade\", \"price\": \"2.25\"}, {\"shortDescription\": \"Gatorade\", \"price\": \"2.25\"}], \"total\": \"9.00\"}";

		mvc.perform(post("/receipts/process")
				.contentType(MediaType.APPLICATION_JSON)
				.content(receiptJson));

		ResultActions result = mvc.perform(get("/receipts/3/points"));
		result.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.points").value(109));
	}

}
