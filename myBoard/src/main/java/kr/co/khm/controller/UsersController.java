package kr.co.khm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

		return "redirect:/main/main";
	}

	/* 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		// 그냥 세션에서 속성 지우면 로그아웃 처리 되는거임
		session.removeAttribute("login");

		log.info("로그아웃 처리됨");

		return "redirect:/main/main";
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
		
		return "redirect:/main/main";
	}
	
	/* 회원 가입시 ID 중복체크 처리 */
	@ResponseBody
	@PostMapping("/idCheck")
	public Map<String, String> idCheck(@RequestBody UsersVO usersVO){
		log.info("usersId : " + usersVO.getUsersId());
		
		Map<String, String> result = new HashMap<>();
		
		if(!Pattern.matches("^[a-zA-Z0-9]*$", usersVO.getUsersId())) {
			result.put("rst", "fail");
			result.put("msg", "ID에 한글&특수문자 사용은 불가능 합니다.");
			return result;
		}
		
		if(!Pattern.matches("^(?=.*[0-9])[a-zA-Z0-9]{4,20}$", usersVO.getUsersId())) {
			result.put("rst", "fail");
			result.put("msg", "ID는 알파벳과 숫자를 포함하여 4~20글자 입니다.");
			return result;
		} else {
			int cnt = userService.duplicateIdCheck(usersVO);
			if(cnt > 0) {
				result.put("rst", "fail");
				result.put("msg", "이미 사용 중인 아이디입니다.");
			} else {
				result.put("rst", "ok");
				result.put("msg", "사용 가능한 아이디입니다.");
			}
			return result;
		}
	}
}
