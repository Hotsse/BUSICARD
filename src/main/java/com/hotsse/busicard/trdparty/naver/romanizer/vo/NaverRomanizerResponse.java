package com.hotsse.busicard.trdparty.naver.romanizer.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverRomanizerResponse {

	@JsonAlias(value = "aResult")
	private List<NaverRomanizerResult> results;
}
