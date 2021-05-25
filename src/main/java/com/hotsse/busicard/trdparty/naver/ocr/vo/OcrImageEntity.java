package com.hotsse.busicard.trdparty.naver.ocr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OcrImageEntity {
	
	private String name;
	private String inferText;
	private double inferConfidence;
}
