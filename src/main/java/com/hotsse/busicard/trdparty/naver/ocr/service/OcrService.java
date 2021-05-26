package com.hotsse.busicard.trdparty.naver.ocr.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotsse.busicard.trdparty.naver.ocr.vo.OcrCredentials;
import com.hotsse.busicard.trdparty.naver.ocr.vo.OcrImageEntity;
import com.hotsse.busicard.trdparty.naver.ocr.vo.OcrImageResponse;
import com.hotsse.busicard.trdparty.naver.ocr.vo.OcrResponse;

@Service(value = "NaverOcrService")
public class OcrService {
	
	final String CREDENTIALS_PATH = "C:/storages/BUSICARD/credentials/OcrCredentials.json";

	public Map<String, Object> parseBusicard(MultipartFile file) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		OcrCredentials ocrCredentials = null;
		try {
			String json = FileUtils.readFileToString(new File(CREDENTIALS_PATH), StandardCharsets.UTF_8);
			ocrCredentials = mapper.readValue(json, OcrCredentials.class);			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		WebClient webClient = WebClient.builder()
				.baseUrl(ocrCredentials.getBaseUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
				.defaultHeader("X-OCR-SECRET", ocrCredentials.getSecretKey())
				.build();
		
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		
		Map<String, Object> messageMap = new HashMap<String, Object>();
		List<Map<String, String>> imageMaps = new ArrayList<Map<String,String>>();
		Map<String, String> imageMap = new HashMap<String, String>();
		imageMap.put("format", "jpg");
		imageMap.put("name", "tempimg");
		imageMaps.add(imageMap);
		
		messageMap.put("version", "V2");
		messageMap.put("requestId", ocrCredentials.getSecretKey());
		messageMap.put("timestamp", "1584062336793");
		messageMap.put("lang", "ko");
		messageMap.put("images", imageMaps);
		
		String message = "";
		try {			
			message = mapper.writeValueAsString(messageMap);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		bodyBuilder.part("message", message);
		bodyBuilder.part("file", new InputStreamResource(file.getInputStream()))
			.header("Content-Disposition",
	            String.format("form-data; name=file; filename=%s", file.getOriginalFilename()));
		
		String result = webClient
				.post()
				.uri(ocrCredentials.getUri())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(bodyBuilder.build()))
				.retrieve()
		        .bodyToMono(String.class)
		        .block();		
		try {
			
			OcrResponse ocrResponse = mapper.readValue(result, OcrResponse.class);
			List<OcrImageResponse> ocrImageResponses = ocrResponse.getImages();
			for(OcrImageResponse ocrImageResponse : ocrImageResponses) {
				List<OcrImageEntity> ocrImageEntities = ocrImageResponse.getFields();
				for(OcrImageEntity ocrImageEntity : ocrImageEntities) {
					resultMap.put(ocrImageEntity.getName(), ocrImageEntity.getInferText());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
}
