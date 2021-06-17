package com.hello.servlet.web.frontcontroller.v4.controller;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/*
	49. V3 내용 가져옴
	 - 비즈니스 로직 실행 후 모델에 값을 넣어주고
	 - return 값으로 논리 view 이름 반환
 */
public class MemberSaveControllerV4 implements ControllerV4 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		// 요청을 넘겨주기 때문에 단순히 꺼내서 쓰면 된다.
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));

		// 비즈니스 로직
		Member member = new Member(username, age);
		memberRepository.save(member);

		model.put("member", member);
		return "save-result";
//		ModelView mv = new ModelView("save-result");
//		mv.getModel().put("member", member);
//		return mv;
	}
}
