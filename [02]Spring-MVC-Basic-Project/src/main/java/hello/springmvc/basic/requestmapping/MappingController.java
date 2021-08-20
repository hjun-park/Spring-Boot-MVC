package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// 05. 여러가지 요청 매핑
@RestController
public class MappingController {

	private Logger log = LoggerFactory.getLogger(getClass());

//	// 05. 가장 간단한 요청 매핑
//	@RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
//	public String helloBasicV1() {
//		log.info("helloBasic");
//		return "ok";
//	}

	// 06. V1과 똑같은 기능을 함 ( 어노테이션 변경 )
	@GetMapping("/hello-basic")
	public String helloBasicV2() {
		log.info("helloBasic");
		return "ok";
	}

	// 07. PathVariable 사용 (경로변수)
	// 		- 변수명이 같으면 생략 가능
	//		 => @PathVariable("userId") String userID -> @PathVariable userId
	//		- PostMan에서는 /mapping/userA 입력해서 테스트해보기
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String data) {
		log.info("mappingPath userId = {}", data);
		return "ok";
	}

	// 08. PathVariable 사용 다중
	//  - Postman 테스트: "/mapping/users/userA/orders/100"
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
		log.info("mappingPath userId={}, orderId={}", userId, orderId);
		return "ok";
	}

	/*
		09. 파라미터로 추가 매핑 (잘 사용하지 않음)
		   >---- 여러 표현들 ----<
			params = "mode"
			params = "!mode"
			params = "mode = debug"
			params = "mode != debug"
			params = {"mode=debug", "data=good"}
	*/
	// Postman 테스트: "/mapping-param?mode=debug"
	// 	 ?를 이용하여 특정 파라미터를 매칭시켜줘야 아래 내용이 실행된다.
	@GetMapping(value = "/mapping-param", params = "mode=debug")
	public String mappingParam() {
		log.info("mappingParam");
		return "ok";
	}

	/*
		10. 특정 헤더로 추가 매핑
			>---- 여러 표현들 ----<
			headers = "mode"
			headers = "!mode"
			headers = "mode=debug"
			headers = "mode!=debug"
	 */
	// Postman 테스트: /mapping-header 요청을 하되, ( 헤더 값이 필요하다 )
	//		Headers에 key는 "mode", value는 "debug"를 넣어서 실행
	@GetMapping(value = "/mapping-header", params = "mode=debug")
	public String mappingHeader() {
		log.info("mappingHeader");
		return "ok";
	}

	/*
		11. 미디어 타입 조건 매핑 (Content-Type에 따른 처리)
			>---- 여러 표현들 ---<
			consumes="application/json"
			consumes="!application/json"
			consumes="application/*"
			consumes="*\/*"
			MediaType.APPLICATION_JSON_VALUE
	 */
	// Postman 테스트 : "/mapping-consume" 설정 후, Body에서 Json으로 전송
	// param이 아니라 consumes로 들어간다는 장점
	@GetMapping(value = "/mapping-consume", consumes = "application/json")
	public String mappingConsumes() {
		log.info("mappingConsumes");
		return "ok";
	}

}
