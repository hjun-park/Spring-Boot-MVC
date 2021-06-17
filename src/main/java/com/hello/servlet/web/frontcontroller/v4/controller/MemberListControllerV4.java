package com.hello.servlet.web.frontcontroller.v4.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;
import com.hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

/*
	50. V3 내용 수정 ( 아래 주석은 기존 V3 내용 )
 */
public class MemberListControllerV4 implements ControllerV4 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		List<Member> members = memberRepository.findAll();

		model.put("members", members);
		return "members";
//		ModelView mv = new ModelView("members");
//		mv.getModel().put("members", members);
//		return mv;
	}
}
