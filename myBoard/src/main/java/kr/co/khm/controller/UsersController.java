package kr.co.khm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.velocity.texen.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String loginForm() {
		log.info("login 폼으로 이동");
		return "users/login";
	}

	/* 로그인 처리 */
	// usersVO라는 데이터가 필요하고, 로그인하려면 session이 필요함(이 세션에 담아놓으면 내가 원하는걸 달라고 할 수 있음)
	
	// 왜 실패메시지를 위해서 model을 쓰냐??
	// 로그인은 일반적으로 redirect를 때리기 때문에 성공하면 세션에 담기니까 성공 메시지는 세션에서 볼수있는데
	// 로그인에 실패하면 현재 view에서 에러 메시지를 보내줘야하니까 model을 사용함
	@PostMapping("/login")
	public String login(UsersVO usersVO, HttpSession session, Model model) {
		log.info("로그인 처리 -> usersVO : " + usersVO);
		
		// 로그인 결과 DB에서 데이터 가져오자
		UsersVO loggedInUser = userService.login(usersVO);
		log.info("로그인 결과 가져오기 -> loggedInUser : " + loggedInUser);

		if (loggedInUser != null) {
			session.setAttribute("login", loggedInUser);
			return "redirect:/main/main";
		} else {
			model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
	        return "users/login"; // 로그인 실패 시 로그인 화면에 그대로 유지
		}
	}

	/* 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		// 그냥 세션에서 속성 지우면 로그아웃 처리 되는거임
		//	session.removeAttribute("login");
		// 모든 속성을 다 지우려면 세션 무효화를 시켜야함
		session.invalidate();

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
	// 여기 Map쓴 이유는 중복체크하면서 아이디, 비밀번호, 이름 등 여러가지 추가적인 메시지를 줘야함
	// login의 자료형이 String인 이유는 그냥 일반적으로 간단한 메시지 처리일땐 String을 쓴다고함.
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
