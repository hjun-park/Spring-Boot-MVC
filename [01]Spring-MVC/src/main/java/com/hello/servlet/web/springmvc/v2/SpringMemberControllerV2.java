package com.hello.servlet.web.springmvc.v2;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
	63. 기존 springmvc-v1의 내용 3개를 하나로 합침
	 - requestMapping은 메소드 단위로 되기 때문에 원하는 만큼 제작 가능
 */
@Controller
@RequestMapping("/springmvc/v2/members")
// 클래스 차제에서 RequestMapping 파라미터를 정해주면
// 아래 메소드에서 중복되는 부분 없이 파라미터 지정이 가능함
public class SpringMemberControllerV2 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("/new-form")
	public ModelAndView newForm() {

		// 모델과 뷰 정보를 담아서 반환
		return new ModelAndView("new-form");
	}


	// /springmvc/v2/members/members 로 되기 때문에 얘는 uri 제거
	@RequestMapping
	public ModelAndView members() {
		List<Member> members = memberRepository.findAll();
		ModelAndView mv = new ModelAndView("members");

		// 간편하게 모델을 자동 등록되게 할 수 있음
//		mv.getModel().put("members", members);
		mv.addObject("members", members);

		return mv;
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
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
