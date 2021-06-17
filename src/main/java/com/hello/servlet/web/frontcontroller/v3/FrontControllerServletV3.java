package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	45. 프론트 컨트롤러 도입 (V3)
	  - V2를 그대로 가져와 복붙 후 수정
 */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")	// * 부분은 모든 하위매핑
public class FrontControllerServletV3 extends HttpServlet {

	// url 매핑 정보
	private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

	// 생성자 구현
	public FrontControllerServletV3() {
		controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청이 들어왔을 때 로직 설계 ( 컨트롤러 실행하고 JSP 응답 )
		String requestURI = request.getRequestURI();	// 요청한 URI가 그대로 들어옴

		ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

		if (controllerV3 == null) {	// 페이지가 없을 때
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// Ctrl + Alt + M으로 따로 메소드 뽑아 줌
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controllerV3.process(paramMap);// V2에는 request, response가 들어갔지만 여기는 paramMap

		String viewName = mv.getViewName(); // 논리이름밖에 없음 ( 예) new-form )

		// Ctrl + Alt + M ( 메소드로 만들기 )
		MyView view = viewResolver(viewName);// 전체 이름으로 매핑 되도록 설정

		view.render(mv.getModel(), request, response);	// 전체 이름으로 렌더링
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		// paramMap
		Map<String, String> paramMap = new HashMap<>();	// paramName을 만들어주고
		// getParameterNames()로 모든 파라미터 이름을 다 가져오고 ,
		// asIterator로 모든 이름을 돌면서
		// key변수명인 paramName 을 이용하여 request.getParameter(paramName) 으로 모든 파라미터 다 꺼내온다.
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
