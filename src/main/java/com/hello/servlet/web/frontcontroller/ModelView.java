package com.hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

/*
	40. model 에다가 원하는 데이터를 넣으면 jsp에서 꺼내 쓸 수 있도록 함
 */
public class ModelView {

	private String viewName;
	private Map<String, Object> model = new HashMap<>();

	public ModelView(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
}
