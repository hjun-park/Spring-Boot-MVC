package com.hello.servlet.web.frontcontroller.v1.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
	31. 마찬가지로 servletmvc/MvcMemberListServlet 내용을 가져옴
		- 다른 점은 이전에는 HttpServerlet을 상속 받았지만
		- 이번에는 프론트 컨트롤러(ControllerV1)을 상속 받아서 구현함
 */
public class MemberListControllerV1 implements ControllerV1 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Member> members = memberRepository.findAll();

			request.setAttribute("members", members);

			String viewPath = "/WEB-INF/views/members.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
			dispatcher.forward(request, response);
		}
}
