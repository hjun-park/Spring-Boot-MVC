package com.hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	58.
 */

// a. HandlerMapping 순서를 bean name으로 handler를 찾는다.
//    => MyHttpRequestHandler가 나옴
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

	// b. handle 요청이 잡힘
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyHttpRequestHandler.handleRequest");
	}
}
