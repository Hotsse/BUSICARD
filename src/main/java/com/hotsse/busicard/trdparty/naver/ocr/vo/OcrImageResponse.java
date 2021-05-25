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
public class OcrImageResponse {

	private String uid;
	private String name;
	private String inferResult;
	private String message;
	private List<OcrImageEntity> fields;
	private Object validationResult;
}
