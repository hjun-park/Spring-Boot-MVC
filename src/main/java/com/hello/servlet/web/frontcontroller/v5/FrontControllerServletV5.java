package com.hello.servlet.web.frontcontroller.v5;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	55. ControllerV*HandlerAdapter 라는 어댑터만 추가하면 깔끔하게 추가 가능
	 	메인코드 거의 건드리지 않고 편리하게 확장이 가능하다.
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

	// 기존에는 컨트롤러맵이고 이걸 handlerMappingMap으로 변경
	// handler가 mapping된 handlermappingmap이 존재
	// 기존 것 (주석처리 된 부분)는 ControllerV4가 들어갔는데
	// 지금은 그 자리에 Object가 오면서 ControllerV1 - V4 모두 지원하게 된다.
//	private Map<String, ControllerV4> controllerMap = new HashMap<>();
	private final Map<String, Object> handlerMappingMap = new HashMap<>();

	// Object에 여러 어댑터가 담겨 있고 그 중에 하나 찾아서 써야 하니까 작성
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

	// 모든 객체의 최상위 부모는 Object이고 이를 상속 받기 때문에 어떤 객체든지 담을 수 있다.
	public FrontControllerServletV5() {
		initHandlerMappingMap();
		initHandlerAdapters();
	}

	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
		handlerAdapters.add(new ControllerV4HandlerAdapter());	// V4 어댑터 추가
	}

	private void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

		// V4 추가
		handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// a. 클라이언트 요청이 들어왔을 때 요청에 대해 처리할 수 있는 핸들러를 찾음
		Object handler = getHandler(request);

		if (handler == null) {	// 페이지가 없을 때 404 에러 처리
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// b. 핸들러를 찾으면 그걸 이용하여 핸들러 어댑터를 가져옴
		MyHandlerAdapter adapter = getHandlerAdapter(handler);

		// c. 요청 응답, 그리고 핸들러를 인자로 넣어주면 거기에 대한 모델 뷰 반환
		// 핸들러 어댑터의해 핸들러 실행(실제 컨트롤러) 후 모델 뷰 반환
		ModelView mv = adapter.handle(request, response, handler);

		// d. 뷰 이름을 가져오고 논리이름을 전체이름으로 매핑
		String viewName = mv.getViewName(); // 논리이름밖에 없음 ( 예) new-form )
		MyView view = viewResolver(viewName);// 전체 이름으로 매핑 되도록 설정

		// e. 렌더링
		view.render(mv.getModel(), request, response);	// 전체 이름으로 렌더링

	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for (MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) {	// 지원하는 어댑터면 선택
				return adapter;
			}
		}
		// 맞는 핸들러가 없을 시
		throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();	// 요청한 URI가 그대로 들어옴
		return handlerMappingMap.get(requestURI);	// 요청 URI에 따른 핸들러를 찾음
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}
