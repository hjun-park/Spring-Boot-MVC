package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

// 27. HTTP 요청 메시지 - 단순 텍스트
//  - HTTP Body에 일반 텍스트를 실어서 반환하는 예제
@Slf4j
@Controller
public class RequestBodyStringController {

	// 일반적으로 body에 단순 텍스트를 실어 나르는 예제
	@PostMapping("/request-body-string-v1")
	public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody={}", messageBody);

		response.getWriter().write("ok");
	}


	// 지금 HttpServerlet을 다 쓸 필요가 없음 -> InputStream 으로 교체
	// response 부분은 Writer로 변경
	// 그러면 깔끔한 코드가 완성
	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);
		responseWriter.write("ok");
	}

	// 위 방법도 별로인 듯 하다, 귀찮다 알아서해주라, 이 방법이 좋다.
	// HttpMessageConverter 를 사용하게 되는데 이는 나중에 설명
	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

		String messageBody = httpEntity.getBody();
		log.info("messageBody={}", messageBody);

		return new HttpEntity<>("ok");
	}

	// HttpEntity의 getBody 쓰는 것도 귀찮다, 해서 나온 마지막 방법
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

		log.info("messageBody={}", messageBody);
		return "ok";
	}


}
