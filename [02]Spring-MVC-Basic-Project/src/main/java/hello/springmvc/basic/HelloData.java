package hello.springmvc.basic;

import lombok.Data;

// 22. HTTP 요청 파라미터 - @ModelAttribute
//	@Data : Getter, Setter, ToString, RequiredArgsConstructor 자동 적용
@Data
public class HelloData {

	private String username;
	private int age;
}
