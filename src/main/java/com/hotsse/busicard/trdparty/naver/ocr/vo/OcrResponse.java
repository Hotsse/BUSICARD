package com.hotsse.busicard.trdparty.naver.ocr.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OcrResponse {
	
	private String version;
	private String requestId;
	private String timestamp;
	private List<OcrImageResponse> images;
}
