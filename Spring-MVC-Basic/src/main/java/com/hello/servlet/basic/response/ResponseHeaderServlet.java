package com.hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

	/*
		15. HTTPServletResponse - 기본 사용법
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [status-line]
		response.setStatus(HttpServletResponse.SC_OK);	// 200 OK
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);	// 400 ERROR

		// [response-headers]
//		response.setHeader("Content-Type", "text/plain;charset=utf-8");	// Content 편의 메소드로 해결
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	// Cookie 편의 메소드로 해결
		response.setHeader("Pragma", "no-cache");
		response.setHeader("my-header", "hello");

		// [Content 편의 메소드]
		content(response);

		// [Cookie 편의 메소드]
		cookie(response);

		// [Redirect 편의 메소드]
		redirect(response);

		PrintWriter writer = response.getWriter();
		writer.println("ok 안녕하세요.");

	}

	/*
		16. Content 편의 메소드
 	*/
	private void content(HttpServletResponse response) {
		//Content-Type: text/plain;charset=utf-8
		//Content-Length: 2
		//response.setHeader("Content-Type", "text/plain;charset=utf-8");
		// -> 위 코드를 아래같이 함수로 적으면 알아서 들어감
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		//response.setContentLength(2); //(생략시 자동 생성)
	}

	/*
		17. Cookie 편의 메소드
 	*/
	private void cookie(HttpServletResponse response) {
		//Set-Cookie: myCookie=good; Max-Age=600;
		//response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
		// -> 위 코드를 아래같이 함수로 적으면 더 보기좋음
		Cookie cookie = new Cookie("myCookie", "good");
		cookie.setMaxAge(600); //600초
		response.addCookie(cookie);
	}

	/*
		18. Redirect 편의 메소드
	 */
	private void redirect(HttpServletResponse response) throws IOException {
		//Status Code 302
		//Location: /basic/hello-form.html
		//response.setStatus(HttpServletResponse.SC_FOUND); //302
		// -> 302코드 redirect를 주면서 /basic/hello-form.html로 리다이렉트 시키고자 함

		//response.setHeader("Location", "/basic/hello-form.html");
		response.sendRedirect("/basic/hello-form.html");
	}


}
