package com.fullcourse;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor // @autowired 선언을 생략하고 전역변수에대해서 자동DI를 해준다.
public class FullcourseMvcConfig implements WebMvcConfigurer {

	private final FullcourseInterceptor fullcourseInterceptor;
	private final FullcourseRestInterceptor fullcourseRestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// interceptor를 여러개 지정할 수 있다. 메소드 순서대로 실행
		registry.addInterceptor(fullcourseInterceptor).addPathPatterns("/*");
		registry.addInterceptor(fullcourseRestInterceptor).addPathPatterns("/*");
	}
}
