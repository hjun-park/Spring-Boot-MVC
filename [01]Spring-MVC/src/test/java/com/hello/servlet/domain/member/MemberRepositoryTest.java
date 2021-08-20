package com.hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/*
	23. 레포지토리에 대한 테스트
	  - 테스트는 순서보장이 되지 않으므로 afterEach를 정의해 준다.
 */
class MemberRepositoryTest {	// junit 5부터는 public 필요없음

	// 사실 스프링 자체는 싱글톤으로 동작되기 때문에 굳이 이렇게 싱글톤 패턴 사용 필요 없음
	MemberRepository memberRepository = MemberRepository.getInstance();

	@AfterEach	// 테스트 끝
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	public void save() throws Exception {
	    //given
		Member member = new Member("hello", 20);

	    //when
		Member savedMember = memberRepository.save(member);

	    //then
		Member findMember = memberRepository.findById(savedMember.getId());
		assertThat(findMember).isEqualTo(savedMember);
	}

	@Test
	public void findAll() throws Exception {
	    //given
		Member member1 = new Member("member1", 20);
		Member member2 = new Member("member2", 30);

		memberRepository.save(member1);
		memberRepository.save(member2);

		//when
		List<Member> result = memberRepository.findAll();

	    //then
		assertThat(result.size()).isEqualTo(2);			// 리스트 사이즈가 2인지
		assertThat(result).contains(member1, member2);	// result에 mem1, 2가 있는지
	}

}
