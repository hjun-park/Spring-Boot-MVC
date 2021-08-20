package com.hello.servlet.web.frontcontroller.v5.adapter;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v4.ControllerV4;
import com.hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
	56. V3 어댑터 추가 후 V4 어댑터 추가 시 간편하게 추가 가능
 */
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handler;

		// 이 코드는 FrontControllerServletV4 코드와 유사
		Map<String, String> paramMap = createParamMap(request);
		HashMap<String, Object> model = new HashMap<>();

		// viewName을 그냥 return 하기에는 문제가 있음
		String viewName = controller.process(paramMap, model);

		// 어댑터가 여기서 역할을 함 ( 110V -> 220V 콘센트 )
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);

		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		// paramMap
		Map<String, String> paramMap = new HashMap<>();    // paramName을 만들어주고
		// getParameterNames()로 모든 파라미터 이름을 다 가져오고 ,
		// asIterator로 모든 이름을 돌면서
		// key변수명인 paramName 을 이용하여 request.getParameter(paramName) 으로 모든 파라미터 다 꺼내온다.
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
