package com.hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
	// 요청 중 POST
	08. 파라미터 전송 기능 ( 첫 번째 방식: GET )
	  - http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	// 서비스라고만 쳐도 됨 (service)
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[전체 파라미터 조회] - start");

		// 파라미터 이름 + 파라미터의 값 출력
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
		System.out.println("[전체 파라미터 조회] - end");
		System.out.println();

		System.out.println("[단일 파라미터 조회]");
		String username = request.getParameter("username");
		String age = request.getParameter("age");

		System.out.println("username = " + username);
		System.out.println("age = " + age);
		System.out.println();

		// 파라미터 이름이 중복되는 게 있을 경우
		// 아래와 같이 전부 꺼낼 수 있다.
		// 사실 중복은 잘 쓰지는 않고 이렇게 중복일 때에는 맨 앞에 값만 반환하도록 한다.
		// http://localhost:8080/request-param?username=hello&username=hello2
		System.out.println("[이름이 같은 복수 파라미터 조회]");
		String[] usernames = request.getParameterValues("username");
		for (String name : usernames) {
			System.out.println("name = " + name);
		}

		response.getWriter().write("ok");

	}
}
