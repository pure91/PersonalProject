package kr.co.khm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.khm.service.UsersService;
import kr.co.khm.vo.UsersVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 회원가입, 회원 로그인 컨트롤러
 * @since 2024.01.30
 */

@Slf4j
@RequestMapping("/users")
@Controller
public class UsersController {

	@Autowired
	UsersService userService;

	/* 로그인 폼 이동 */
	// jsp만 있으면 됨
	@GetMapping("/login")
	public String loginFrom() {
		log.info("login 폼으로 이동");
		return "users/login";
	}

	/* 로그인 처리 */
	// usersVO라는 데이터가 필요하고, 로그인하려면 session이 필요함(이 세션에 담아놓으면 내가 원하는걸 달라고 할 수 있음)
	@PostMapping("/login")
	public String login(UsersVO usersVO, HttpSession session) {

		log.info("로그인 처리 -> usersVO : " + usersVO);

		// login인 데이터 db에서 가져옴
		session.setAttribute("login", userService.login(usersVO));

		// 당장 메인화면 없으니 일단 자유게시판 목록으로 보내겠음
		return "redirect:/board/free/list";
	}

	/* 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		// 그냥 세션에서 속성 지우면 로그아웃 처리 되는거임
		session.removeAttribute("login");

		log.info("로그아웃 처리됨");

		// 얘도 당장 메인화면 없으니 일단 자유게시판 목록으로 보내겠음
		return "redirect:/board/free/list";
	}

	/* 회원가입 폼 이동 */
	@GetMapping("/join")
	public String joinForm() {
		return "users/join";
	}

	/* 회원가입 처리 */
	@PostMapping("/join")
	public String join(UsersVO usersVO) {
		
		// DB에서 넘어오는 데이터로 처리할거임
		userService.join(usersVO);
		
		// 얘는 로그인으로 보내고
		return "redirect:/board/free/login";
	}
}
