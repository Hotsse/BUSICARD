package com.hotsse.busicard.trdparty.naver.romanizer.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotsse.busicard.trdparty.naver.romanizer.vo.NaverRomanizerCredentials;
import com.hotsse.busicard.trdparty.naver.romanizer.vo.NaverRomanizerItem;
import com.hotsse.busicard.trdparty.naver.romanizer.vo.NaverRomanizerResponse;

@Service
public class NaverRomanizerService {
	
	private final String ROMANIZER_URL = "https://naveropenapi.apigw.ntruss.com";
	private final String CREDENTIAL_PATH = "C:/storages/BUSICARD/credentials/RomanizerCredentials.json";

	/**
	 * from 한글명 to 영문명 변환
	 * 
	 * @param name 한글명
	 * @return
	 * @throws Exception
	 */
	public List<NaverRomanizerItem> getItems(String name) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		NaverRomanizerCredentials credential = null;
		try {
			String json = FileUtils.readFileToString(new File(CREDENTIAL_PATH), StandardCharsets.UTF_8);
			credential = mapper.readValue(json, NaverRomanizerCredentials.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		WebClient webClient = WebClient.builder()
				.baseUrl(ROMANIZER_URL)
				.defaultHeader("X-NCP-APIGW-API-KEY-ID", credential.getClientId())
				.defaultHeader("X-NCP-APIGW-API-KEY", credential.getClientSecret())
				.build();
		
		String responseJson = webClient.get()
			.uri(uriBuilder->uriBuilder
				.path("/krdict/v1/romanization")
				.queryParam("query", name)
				.build())
			.retrieve()
			.bodyToMono(String.class)
	        .block();
		
		List<NaverRomanizerItem> items = null;
		try {
			NaverRomanizerResponse response = mapper.readValue(responseJson, NaverRomanizerResponse.class);
			if(response.getResults() == null || response.getResults().size() == 0) {
				throw new Exception("유효하지 않은 한글 이름");
			}
			items = response.getResults().get(0).getItems();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
}
