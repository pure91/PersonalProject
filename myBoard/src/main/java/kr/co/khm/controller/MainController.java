package kr.co.khm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 김형민
 * @category 메인화면 컨트롤러
 * @since 2024.02.01
 */

@RequestMapping("/main")
@Controller
public class MainController {

	// 큰 의미 없음, 그냥 메인화면 분기처리하려고..
	@GetMapping("/main")
	public String MainPage() {
		return "main/main";
	}
}
