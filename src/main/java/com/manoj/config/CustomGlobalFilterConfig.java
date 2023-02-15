package com.manoj.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class CustomGlobalFilterConfig {

	public CustomGlobalFilterConfig(ObjectMapper objectMapper) {
		SimpleFilterProvider globalFilter = new SimpleFilterProvider().setFailOnUnknownId(false);
		objectMapper.setFilterProvider(globalFilter);
	}

}
