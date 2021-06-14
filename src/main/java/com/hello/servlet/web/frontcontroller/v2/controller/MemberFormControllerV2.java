package com.hello.servlet.web.frontcontroller.v2.controller;

import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기존 dispatch 파트가 사라지고 MyView 객체만 반환하여 깔끔한 구조를 띔
		return new MyView("/WEB-INF/views/new-form.jsp");

	}
}
