package com.hello.servlet.web.frontcontroller.v1;

import com.hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	32. 프론트 컨트롤러 도입
	  - /front-controller/v1 하위로 들어오는 애들은 이 부분을 거쳐간다.
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")	// * 부분은 모든 하위매핑
public class FrontControllerServletV1 extends HttpServlet {

	// url 매핑 정보
	private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

	// 생성자 구현
	// 서블릿이 생성될 때 아래 패턴을 참고하여 각각 객체 정보가 controllerV1Map에 저장이 된다.
	public FrontControllerServletV1() {
		controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
		controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
		controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV1.service");

		// 요청이 들어왔을 때 로직 설계 ( 컨트롤러 실행하고 JSP 응답 )
		String requestURI = request.getRequestURI();	// 요청한 URI가 그대로 들어옴

		/* 다형성 이용, 사실은 아래 코드는 이것과 같다.부모가 자식을 담은 것,
		  - requestURI 키에 해당하는 값인 MemberListControllerV1()를
		  - controllerV1에 집어넣는다.
		*/
		ControllerV1 controllerV1 = controllerV1Map.get(requestURI);

		if (controllerV1 == null) {	// 페이지가 없을 때
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 페이지가 있는 경우 해당 파트로 요청, 응답
		// 다형성을 이용한 파트
		controllerV1.process(request, response);
	}
}
