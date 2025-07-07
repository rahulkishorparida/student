package com.sms.demo.exception;

public class StudentNotSaved extends RuntimeException {
	public StudentNotSaved (String message) {
		super(message);
	}
}