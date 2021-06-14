package com.hello.servlet.web.servlet;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
	25. 서블릿으로 회원 관리 웹 애플리케이션 만들기
 */
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberSaveServlet.service");

		// get 파라미터 온 걸 꺼내고
		String username = request.getParameter("username");
		// 나이의 경우 숫자로 변환
		int age = Integer.parseInt(request.getParameter("age"));

		// 비즈니스 로직을 거치고
		Member member = new Member(username, age);
		memberRepository.save(member);

		// 응답을 통해 내용 확인
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter w = response.getWriter();
		w.write("<html>\n" +
			"<head>\n" +
			" <meta charset=\"UTF-8\">\n" +
			"</head>\n" +
			"<body>\n" +
			"성공\n" +
			"<ul>\n" +
			" <li>id="+member.getId()+"</li>\n" +
			" <li>username="+member.getUsername()+"</li>\n" +
			" <li>age="+member.getAge()+"</li>\n" +
			"</ul>\n" +
			"<a href=\"/index.html\">메인</a>\n" +
			"</body>\n" +
			"</html>");
	}

}
