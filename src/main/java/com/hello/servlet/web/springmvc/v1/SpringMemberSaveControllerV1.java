package com.hello.servlet.web.springmvc.v1;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*
	61. 회원 저장 컨트롤러
	 - MemberSaveControllerV3와 유사
 */
@Controller
public class SpringMemberSaveControllerV1 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("/springmvc/v1/members/save")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		// 요청을 넘겨주기 때문에 단순히 꺼내서 쓰면 된다.
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		ModelAndView mv = new ModelAndView("save-result");

		// 자동으로 model이 들어감
		mv.addObject("member", member);
		return mv;
	}

}
