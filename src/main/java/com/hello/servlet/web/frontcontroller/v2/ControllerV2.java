package com.hello.servlet.web.frontcontroller.v2;

import com.hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	34. v1의 ControllerV1과 똑같은데, MyView를 반환한다는게 다름
 */
public interface ControllerV2 {

	// 기존 v1에서는 void 형식이었지만 ( 컨트롤러에서 뷰로 넘겨줌 )
	// 여기서는 MyView 뷰 객체 반환
	MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
