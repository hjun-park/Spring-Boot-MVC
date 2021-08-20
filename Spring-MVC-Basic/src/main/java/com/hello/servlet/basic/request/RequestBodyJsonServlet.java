package com.hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.servlet.basic.HelloData;
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
	13. HTTP API (rest api) 방식
		-> post 이용하여 테스트할 수 있다.
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

	/*
		14. API 메시지 바디 - JSON
		 - 마찬가지로 postman의 Body에 JSON 형식 실어서 테스트 해보기
		 - {"username": "park", "age": 20}
 	*/

	// Spring boot에서 기본적으로 제공하는 JSON 변환하는 JACKSON 라이브러리
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 11번 초반과 유사, ByteCode -> String 변환
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		System.out.println("messageBody = " + messageBody);

		// String 형식으로 변환한 데이터에 클래스로 만들었던 JSON 변환 할 객체 담기
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

		System.out.println("helloData.username = " + helloData.getUsername());
		System.out.println("helloData.age = " + helloData.getAge());

		response.getWriter().write("ok");
	}
}
