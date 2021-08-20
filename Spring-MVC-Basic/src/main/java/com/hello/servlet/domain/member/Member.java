package com.hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;


/*
	21. 회원 관리 웹 애플리케이션 만들기
	  - 저장 기능
	  - 조회 기능
 */
@Getter @Setter
public class Member {

	private Long id;
	private String username;
	private int age;

	public Member() {

	}

	public Member(String username, int age) {
		this.username = username;
		this.age = age;
	}
}
