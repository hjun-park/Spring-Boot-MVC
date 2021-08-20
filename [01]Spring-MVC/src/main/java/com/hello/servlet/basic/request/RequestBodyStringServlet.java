package com.hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
	09. HTTP API (rest api) 방식
		-> post 이용하여 테스트할 수 있다.
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

	/*
		11. API 메시지 바디 - String
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메시지 Body 내용을 Bytecode로 얻을 수 있음
		ServletInputStream inputStream = request.getInputStream();

		// byte to string, 인자는 string인데 어떤 타입의 string인지 ?
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		System.out.println("messageBody = " + messageBody);

		response.getWriter().write("ok");
	}
}
