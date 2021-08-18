package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 29. 응답 - 정적 리소스, 뷰 템플릿
public class ResponseViewController {

	// static/response/hello.html을 참조함
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav = new ModelAndView("response/hello")
			.addObject("data", "hello!");

		return mav;
	}

	// 모델이 데이터를 실어서 반환
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "hello!");
		return "response/hello";
	}



}
