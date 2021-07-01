package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
	02. 로그 관련 클래스
 */

// 뷰를 반환하는 것이 아니라 HTTP 메시지 바디에 바로 데이터를 입력하여 응답을 주는 것
@RestController
@Slf4j
public class LogTestController {

	// 03. 꼭 slf4j 라이브러리 사용할 것
	// 아래처럼 쓰는게 너무 귀찮다 ==> @Slf4j 어노테이션 사용
	// private final Logger log = LoggerFactory.getLogger(LogTestController.class);
	// private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest() {

		String name = "Spring";
		String name2 = "boot";

		// 과거 방식
		System.out.println("name = " + name);

		// 로그 사용 ( 시관과 로그레벨 어디서 호출했는지 나온다 )
		// application.properties 를 참고하면 출력 범위 지정 가능하다.
		log.trace("trace log = {}, {}", name, name2);
		log.debug("debug log = {}", name);

		// 실무에서는 사실 info부터를 찍는다.
		log.info("info log = {}", name);
		log.warn("warn log = {}", name);
		log.error("error log = {}", name);

		// Controller는 "ok"라는 View이름을 반환할 것이다.
		// RestController는 return값이 문자라면 "ok"라는 json데이터 반환
		return "ok";
	}
}
