package com.hello.servlet.web.frontcontroller.v4.controller;

import com.hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

	/*
		48. 만들 필요 없이 뷰 논리 이름 자체를 반환함
	 */
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "new-form";
	}
}
