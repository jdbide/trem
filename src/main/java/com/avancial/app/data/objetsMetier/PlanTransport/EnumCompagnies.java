package com.avancial.app.data.objetsMetier.PlanTransport;

public enum EnumCompagnies {

    ES("ES"),
    TH("TH");
	
	private String value;
	
	EnumCompagnies(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
