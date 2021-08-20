package com.hello.servlet.web.frontcontroller.v1.controller;

import com.hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	30. ControllerV1을 구현한 회원 가입 폼
 */
public class MemberFormControllerV1 implements ControllerV1 {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기존 MVC 서블릿 만든거에서 가져옴
		// 컨트롤러에서 뷰로 이동할 때 사용 ( 요 경로로 이동할거야 )
		String viewPath = "/WEB-INF/views/new-form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
