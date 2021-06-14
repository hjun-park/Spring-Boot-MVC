package com.hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	29. 회원 리스트, 저장, 폼 하는 컨트롤러 다 구현
		-> 웹 요청이 프론트 컨트롤러로 와서 통해서 매핑이 됨
*/
public interface ControllerV1 {

	void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


}
