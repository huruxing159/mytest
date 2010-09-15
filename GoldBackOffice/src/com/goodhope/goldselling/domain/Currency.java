package com.goodhope.goldselling.domain;

public class Currency {
	private long id;
	private String label;
	private String symbol;
	private float scaling_factor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setScaling_factor(float scaling_factor) {
		this.scaling_factor = scaling_factor;
	}

	public float getScaling_factor() {
		return scaling_factor;
	}

}
