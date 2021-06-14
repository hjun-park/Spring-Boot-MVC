package com.hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	33. View 분리
	 - 기존 컨트롤러에서 했던 viewPath 포워드 로직을 여기서 하게 됨
 */
public class MyView {
	private String viewPath;	// "/WEB-INF/views/new-form.jsp"

	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}

	// 실제 뷰가 렌더링 되도록 동작하는 파트
	// 공통된 부분을 여기로 가져옴
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
