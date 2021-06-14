package com.hello.servlet.web.frontcontroller.v2.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	35. View 객체 분리 - 회원 저장
 */
public class MemberSaveControllerV2 implements ControllerV2 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		// Model 에 데이터를 보관한다.
		// 밑에 JSP 뷰가 나오는데,
		// request에 map 과 같은 저장소가 있는데 거기에 저장됨
		request.setAttribute("member", member);


		// 아래 부분 필요 없이 MyView 객체를 반환하면 된다.
//		String viewPath = "/WEB-INF/views/save-result.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//		dispatcher.forward(request, response);

		return new MyView("/WEB-INF/views/save-result.jsp");

	}
}
