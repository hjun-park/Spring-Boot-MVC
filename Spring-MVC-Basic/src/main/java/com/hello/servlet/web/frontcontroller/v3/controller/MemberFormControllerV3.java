package com.hello.servlet.web.frontcontroller.v3.controller;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/*
	42.
 */
public class MemberFormControllerV3 implements ControllerV3 {

	@Override
	public ModelView process(Map<String, String> paramMap) {
		// V2는 View의 경로를 표시했지만 V3는 논리적인 이름으로 반환한다.
		return new ModelView("new-form");
	}
}
