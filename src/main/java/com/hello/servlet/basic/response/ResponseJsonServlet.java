package com.hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

	/*
		20. HTTP 응답 데이터 - API JSON
	 	- 서블릿으로 HTML 응답하려면 직접 코드를 적어주어야 한다.
 	*/
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Content-Type: application/json
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		HelloData helloData = new HelloData();
		helloData.setUsername("park");
		helloData.setAge(20);

		// 위의 객체를 json으로 변환
		// Object Mapper가 필요하다 ( 위 코드 )
		String s = objectMapper.writeValueAsString(helloData);
		response.getWriter().write(s);
	}
}
