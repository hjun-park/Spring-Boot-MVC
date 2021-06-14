package com.hello.servlet.basic.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

	/*
		19. 단순텍스트, HTML 형식으로 응답
		 - 서블릿으로 HTML 응답하려면 직접 코드를 적어주어야 한다.
 	*/
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Content-Type: text/html;charset=utf-8
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		writer.println("	<div>안녕?</div>");
		writer.println("</body>");
		writer.println("</html>");

	}
}
