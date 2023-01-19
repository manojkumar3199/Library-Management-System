package com.manoj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticConfig implements WebMvcConfigurer {

	private String uploadImagesDir;

	public StaticConfig(@Value("${project.upload.images}") String uploadImagesDir) {
		super();
		this.uploadImagesDir = uploadImagesDir;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + uploadImagesDir + "/");
	}

}
