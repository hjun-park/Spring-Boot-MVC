package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/*
	41.
 */
public interface ControllerV3 {

	// v2와 다르게 Request, Response가 없음 서블릿을 사용하지 않음
	ModelView process(Map<String, String> paramMap);

}
