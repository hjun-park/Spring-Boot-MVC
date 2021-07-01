package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

	// 13. HTTP 요청 - 기본, 헤더 조회
	//   - 애노테이션 기반은 다양한 파라미터를 받아들일 수 있다.
	/**
	 *
	 * @param request : 서블릿 request와 똑같음
	 * @param response : 서블릿 response와 똑같음
	 * @param httpMethod : 요청 메소드
	 * @param locale	: 가장 우선순위 높은 언어
	 * @param headerMap	: 헤더에 대한 정보를 키-값 형식으로 저장
	 * @param host	: 요청 host
	 * @param cookie	: 쿠키 정보
	 */

	// MultiValueMap은 하나의 키에 여러 Value를 허용한다.
	@RequestMapping("/headers")
	public String headers(HttpServletRequest request,
						  HttpServletResponse response,
						  HttpMethod httpMethod,
						  Locale locale, // 언어 정보
						  // 헤더를 모두 받음
						  @RequestHeader MultiValueMap<String, String> headerMap,
						  // 특정 헤더를 받음
						  @RequestHeader ("host") String host,
						  // 쿠키 정보를 받음 ( false는 없어도 됨 )
						  @CookieValue(value = "myCookie", required = false) String cookie
						  ) {

		log.info("request={}", request);
		log.info("response={}", response);
		log.info("httpMethod={}", httpMethod);
		log.info("locale={}", locale);
		log.info("headerMap={}", headerMap);
		log.info("header host={}", host);
		log.info("myCookie={}", cookie);

		return "ok";

	}



}
