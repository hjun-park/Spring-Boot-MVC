package com.hello.servlet.web.frontcontroller.v4;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	51. 프론트 컨트롤러 도입 (V4)
	  - V3를 그대로 가져와 복붙 후 수정
 */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")	// * 부분은 모든 하위매핑
public class FrontControllerServletV4 extends HttpServlet {

	// url 매핑 정보
	private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

	// 생성자 구현
	public FrontControllerServletV4() {
		controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청이 들어왔을 때 로직 설계 ( 컨트롤러 실행하고 JSP 응답 )
		String requestURI = request.getRequestURI();	// 요청한 URI가 그대로 들어옴

		ControllerV4 controllerV4 = controllerV4Map.get(requestURI);

		if (controllerV4 == null) {	// 페이지가 없을 때
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// Ctrl + Alt + M으로 따로 메소드 뽑아 줌
		// V4에서는 paramMap만 넘어가는 것이 아니라 model도 함께 인자로 넘어감
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<>();
//		ModelView mv = controllerV3.process(paramMap);// V2에는 request, response가 들어갔지만 V3 paramMap
		String viewName = controllerV4.process(paramMap, model);// V4에는 model도 함께 넘겨줌

		// model 넘어갔기 때문에 view name을 받을 필요가 없음
//		String viewName = mv.getViewName(); // 논리이름밖에 없음 ( 예) new-form )

		MyView view = viewResolver(viewName);// 전체 이름으로 매핑 되도록 설정

//		view.render(mv.getModel(), request, response);	// 전체 이름으로 렌더링
		view.render(model, request, response);	// V3에서는 모델 이름을 꺼냈지만 이제는 꺼낼 필요는 없음

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

// 52. FrontControllerServlet V1 ~ V4로 오면서 내용은 복잡해졌지만,
// 공통 기능이 수고로워야 개별부분 (컨트롤러) 이 편해진다.
// 지금까지 만들어진 컨트롤러는 단점이 있다.
// V4가 아니라 V1도 같이 쓰고 싶다할 때에 지금은 그게 되지 않는다.
// 왜냐하면 30번째 라인에 볼 때 이미 Map에 V4가 제네릭 들어갔기 때문에 변경이 힘들다.
// 그래서 V5부터는 어떤 컨트롤러든 사용할 수 있는 어댑터 패턴을 이용한 유용한 컨트롤러를 만들어볼 예정

