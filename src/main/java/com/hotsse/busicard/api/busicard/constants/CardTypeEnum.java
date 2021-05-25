package com.hotsse.busicard.api.busicard.constants;

public enum CardTypeEnum {

	KO("ko"),
	EN("en");
	
	private String code;
	
	CardTypeEnum(String code) {
		this.code = code;
	}
	
	public static CardTypeEnum findBy(String code) {
		
		for(CardTypeEnum cardType : values()) {
			if(code.equalsIgnoreCase(cardType.code)) {
				return cardType;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
}
