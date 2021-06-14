package com.hello.servlet.web.frontcontroller.v1.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	31. 회원 저장 컨트롤러
	  - servletmvc/MvcMemberSaveServlet 내용과 똑같이 가져옴
 */
public class MemberSaveControllerV1 implements ControllerV1 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		// Model 에 데이터를 보관한다.
		// 밑에 JSP 뷰가 나오는데,
		// request에 map 과 같은 저장소가 있는데 거기에 저장됨
		request.setAttribute("member", member);

		String viewPath = "/WEB-INF/views/save-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
