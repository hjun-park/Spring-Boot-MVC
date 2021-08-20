package com.hello.servlet.web.frontcontroller.v5.adapter;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;
import com.hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.naming.ldap.Control;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	54. ControllerV3를 지원해 주는 핸들러 어댑터
 */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

	// handler가 넘어오는데 지원할 수 있어 없어를 확인함
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV3);	// V3 컨트롤러의 인스턴스인가 ?
	}

	// 핸들러 처리 ( supports를 통해 걸러진 것들만 처리 )
	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		// 굉장히 유연하게 하기 위해 handler가 Object이고 그냥 받아오면 딱히 할 게 없다.
		// 그래서 type casting을 해준다.
		ControllerV3 controller = (ControllerV3) handler;

		// 요청에 대한 Map을 알아서 파싱 해줌
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);

		// 핸들에서는 그대로 model view만 반환하면 된다.
		return mv;
	}

	// FrontControllerServletV3에서 가져옴
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
