package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
// 30. HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
public class ResponseBodyController {

	@GetMapping("/response-body-string-v1")
	public void responseBodyV1(HttpServletResponse response) throws IOException {
		response.getWriter().write("ok");
	}

	@GetMapping("/response-body-string-v2")
	public ResponseEntity<String> responseBodyV2() {
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	// responseBody를 이용해서 http 메시지를 직접 입력할 수 있다.
//	@ResponseBody
	@GetMapping("/response-body-string-v3")
	public String responseBodyV3() {
		return "ok";
	}


	// ----

	@GetMapping("/response-body-json-v1")
	public ResponseEntity<HelloData> responseBodyJsonV1() {
		HelloData helloData = new HelloData();
		helloData.setUsername("userA");
		helloData.setAge(20);

		return new ResponseEntity<>(helloData, HttpStatus.OK);
	}

	// 객체를 JSON 형식으로 반환할 수 있고 상태코드도 또한 전달이 가능하다.
	// 동적으로 응답코드를 변경해야 한다면 위의 v1 방식을 써야 한다.
	@ResponseStatus(HttpStatus.OK)
//	@ResponseBody
	@GetMapping("/response-body-json-v2")
	public HelloData responseBodyJsonV2() {
		HelloData helloData = new HelloData();
		helloData.setUsername("userA");
		helloData.setAge(20);

		return helloData;
	}
	
}
