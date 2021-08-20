package com.hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {	// 기본적으로 상속받고 시작

	/*
		01. 서블릿 요청 응답
	 */
	// Ctrl + O 눌러서 키 모양의 service 선택. ( 키 = protected )
	// 웹 홈페이지로 /hello 접속하면 아무것도 나오진 않고 콘솔창에 출력됨
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Servlet HTTP 요청이 오면 WAS의 서블릿 컨테이너가 resquest, response 객체 만들어서
		// 서블릿에 던져준다, 그럼 /hello 호출하면 요청에 따른 응답을 해주게 된다.
		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		// 요청메시지 파싱
		// 쿼리 파라미터의 usernamem 파트를 읽어서 출력
		// /hello?username=park
		String username = request.getParameter("username");
		System.out.println("username = " + username);

		// 응답메시지 전달
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");	// http header : content type
		response.getWriter().write("hello " + username);	// http body

	}

	
}
