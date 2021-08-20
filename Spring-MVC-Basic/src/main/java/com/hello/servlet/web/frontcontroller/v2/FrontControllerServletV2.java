package com.hello.servlet.web.frontcontroller.v2;

import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	39. 프론트 컨트롤러 도입 (V2)
	  - V1을 그대로 가져와 복붙 후 수정
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")	// * 부분은 모든 하위매핑
public class FrontControllerServletV2 extends HttpServlet {

	// url 매핑 정보
	private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

	// 생성자 구현
	public FrontControllerServletV2() {
		controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
		controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
		controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청이 들어왔을 때 로직 설계 ( 컨트롤러 실행하고 JSP 응답 )
		String requestURI = request.getRequestURI();	// 요청한 URI가 그대로 들어옴

		ControllerV2 controllerV2 = controllerV2Map.get(requestURI);

		if (controllerV2 == null) {	// 페이지가 없을 때
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 38. front 컨트롤러 V2에서는 렌더를 위한 MyView를 반환함
		MyView view = controllerV2.process(request, response);
		view.render(request, response);
	}
}
