package com.hello.servlet.web.frontcontroller.v3.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/*
	43.
 */
public class MemberSaveControllerV3 implements ControllerV3 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public ModelView process(Map<String, String> paramMap) {
		// 요청을 넘겨주기 때문에 단순히 꺼내서 쓰면 된다.
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		ModelView mv = new ModelView("save-result");
		mv.getModel().put("member", member);
		return mv;
	}
}
