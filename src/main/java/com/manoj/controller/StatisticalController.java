package com.manoj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.payload.StatsDto;
import com.manoj.service.StatisticalService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatisticalController {
	private StatisticalService statisticalService;

	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public StatsDto getStats() {
		return statisticalService.getStats();
	}
}