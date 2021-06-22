package com.hello.servlet.web.springmvc.v3;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
	64. ModelAndView를 반환하는 불편한 부분을 제거하고자 함
		- V2의 내용을 복사해 옴
		- 정말 간단함
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	// 65. 아래 주석처리 된 부분을 보면 알겠지만,
	//		반환 타입을 String으로 변경하고
	//		뷰 이름으로 반환하면 인터페이스이기 때문에 알아서 다 해준다.
	// 		method를 넣어서 제약을 걸어야 좋은 설계
//	@RequestMapping(value = "/new-form", method = RequestMethod.GET)
	@GetMapping("/new-form")	// 67. RequestMethod.GET도 너무 길다 해서 나온 어노테이션
	public String newForm() {
		return "new-form";
	}

//	@RequestMapping("/new-form")
//	public ModelAndView newForm() {
//
//		// 모델과 뷰 정보를 담아서 반환
//		return new ModelAndView("new-form");
//	}


	// /springmvc/v2/members/members 로 되기 때문에 얘는 uri 제거
//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public String members(Model model) {

		List<Member> members = memberRepository.findAll();
//		ModelAndView mv = new ModelAndView("members");

//		mv.addObject("members", members);
		model.addAttribute("members", members);

		return "members";
	}


	@PostMapping("/save")
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
	public String save(@RequestParam("username") String username,
							 @RequestParam("age") int age,
							 Model model) {
		// int age도 알아서 타입 변환해주기 때문에 주석처리
//		String username = request.getParameter("username");
//		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

//		ModelAndView mv = new ModelAndView("save-result");

		// 자동으로 model이 들어감
//		mv.addObject("member", member);

		// 66. 위에는 주석 아래에는 파라미터로 넘어온 model에 attrib 추가
		model.addAttribute("member", member);
		return "save-result";
	}
}
