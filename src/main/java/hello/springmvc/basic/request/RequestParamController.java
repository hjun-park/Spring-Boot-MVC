package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
