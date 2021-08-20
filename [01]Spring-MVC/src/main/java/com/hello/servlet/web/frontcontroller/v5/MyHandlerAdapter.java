package com.hello.servlet.web.frontcontroller.v5;

import com.hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	53.
 */
public interface MyHandlerAdapter {

	// 컨트롤러가 넘어왔을 때 지원할 수 있는지 여부 파악
	boolean supports(Object handler);

	ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;



}
