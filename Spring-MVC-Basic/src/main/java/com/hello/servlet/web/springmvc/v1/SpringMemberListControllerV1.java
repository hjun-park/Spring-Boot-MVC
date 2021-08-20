package com.hello.servlet.web.springmvc.v1;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/*
	62. 회원 리스트
 */
@Controller
public class SpringMemberListControllerV1 {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("/springmvc/v1/members")
	public ModelAndView process() {
		List<Member> members = memberRepository.findAll();
		ModelAndView mv = new ModelAndView("members");

		// 간편하게 모델을 자동 등록되게 할 수 있음
//		mv.getModel().put("members", members);
		mv.addObject("members", members);

		return mv;
	}
}
