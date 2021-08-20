package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// 14. HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
@Slf4j
@Controller
public class RequestParamController {

	// 15. 반환형이 void이면서 서블릿 response로 getWriter.write하면 화면에 쓸 수 있다.
	// 	GET 요청도 받고, POST 요청도 받는다.
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
			int age = Integer.parseInt(request.getParameter("age"));

		log.info("username = {}, age = {}", username, age);

		response.getWriter().write("ok");
	}

	// 16. HTTP 요청 파라미터 - @RequestParam
	//  - String이 반환이기 때문에 View를 반환이 된다.
	// 	- 그걸 막기 위해 @ResponseBody를 쓰게 되면 @RestController 쓴 것처럼 사용 가능
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
			@RequestParam("username") String memberName,
	 		@RequestParam("age") int memberAge) {

		log.info("username = {}, age = {}", memberName, memberAge);
		return "ok";
	}


	// 17. 인자 부분에 RequestParam()을 생략한 예제
	//		인자 변수 이름과 파라미터명이 같으면 생략 가능
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(
			 @RequestParam String username,
			 @RequestParam int age) {

		log.info("username = {}, age = {}", username, age);
		return "ok";
	}

	// 18. 심지어 완전히 짧게 줄여 쓸 수 있다.
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) {
		log.info("username = {}, age = {}", username, age);
		return "ok";
	}


	// 19. 해당 요청 파라미터는 꼭 필수로 오지 않아도 돼
	//     라고 지정할 수 있다. ( required = false ) 붙이면 됨
	//		하지만 int의 경우 객체가 아니기 때문에 null이 들어갈 수 없다.
	//		false로 하고 요청값이 아무것도 오지 않으면 기본값을 뭔가
	//		집어넣어줘야하는데 Integer 객체를 이용하면 null을 넣을 수 있다.
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
			@RequestParam(required = true) String username,
			@RequestParam(required = false) Integer age) {

		log.info("username = {}, age = {}", username, age);
		return "ok";
	}

	// 20. (19)의 개선 방안, 만약 값이 넘어오지 않을 때에는
	//		defaultValue를 이용하여 기본값을 지정해준다.
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
		@RequestParam(required = true, defaultValue = "guest") String username,
		@RequestParam(required = false, defaultValue = "-1") int age) {

		log.info("username = {}, age = {}", username, age);
		return "ok";
	}

	// 21. 인자를 Map 형식으로 받아서 처리할 수도 있다.
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamDefault(@RequestParam Map<String, Object> paramMap) {
		log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
		return "ok";
	}

	// 23. HTTP 요청 파라미터 - @ModelAttribute
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	// 24. 원래 방식
	//public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
	// 25. 변경된 방식 ( HelloData 부분 주석도 가능 )
	// HelloData 객체도 알아서 생성 해주고 파라미터의 값도 모두 들어가 있다.
	//  ( 객체 생성 -> 요청파라미터 이름으로 HelloData 프로퍼티(username, age)를 찾음 )
	//  ( 그래서 setter을 호출하고 각 프로퍼티를 바인딩해주며 값을 set )
	public String modelAttributeV1(@ModelAttribute HelloData helloData) {

//		HelloData helloData = new HelloData();
//		helloData.setUsername(username);
//		helloData.setAge(age);

		log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

		return "ok";
	}

	// 26. ModelAttribute는 생략이 가능하다.
	//  - 개인적으로 적어주는게 좋을 것 같기도 하고 ..
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

		return "ok";
	}



}
