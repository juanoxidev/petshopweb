package com.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

	public String handleException(Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}
