package com.hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
	60. 회원 등록 코드
 */
@Controller //	스프링이 자동으로 스프링 빈으로 등록 (Component 스캔 대상)
			// 애노테이션 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {

	// 해당 url이 매핑되면 아래 메소드가 호출됨
	// 핸들러 잡고 어댑터 처리 두 가지를 아래 어노테이션이 해 줌
	@RequestMapping("/springmvc/v1/members/new-form")
	public ModelAndView process() {

		// 모델과 뷰 정보를 담아서 반환
		return new ModelAndView("new-form");
	}
}
