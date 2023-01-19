package com.manoj.util;

public enum GenderCode {
	M("male"), F("female"), O("others");

	private String gender;

	private GenderCode(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
}
