package com.fullcourse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// application.properties 변수(file.dir)를 DI
	@Value("${file.dir}")
	private String realPath;

	private String connectPath = "/uploadimgPath/**";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info(realPath);
		registry.addResourceHandler(connectPath).addResourceLocations("file:///" + realPath);
	}

}
