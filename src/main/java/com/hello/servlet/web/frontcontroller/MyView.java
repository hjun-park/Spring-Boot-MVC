package com.hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

	/*
		46. 렌더라는 함수를 따로 만들어준다.
			- 오버로딩
	 */
	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 다 꺼내서 key, value를 가지고 loop를 돌리는 lambda문
		// request.setAttribute 이용해서 request 값을 다 담아둔다.
		// Ctrl + Alt + M 이용하여 메소드로 생성
		modelToRequestAttribute(model, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		// model에 있는걸 다 꺼내서 request에 있는걸 넣어준다.
		model.forEach((key, value) -> request.setAttribute(key, value));
	}
}
