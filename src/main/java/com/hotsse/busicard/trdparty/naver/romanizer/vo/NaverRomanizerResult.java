package com.hotsse.busicard.trdparty.naver.romanizer.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverRomanizerResult {

	@JsonAlias(value = "aItems")
	private List<NaverRomanizerItem> items;
}
