package com.ssafy.happyhouse.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String IDCHECK = "idcheck";
	private static final String ADMIN = "admin";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "회원가입 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/regist")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) throws SQLIntegrityConstraintViolationException {
		logger.debug("writeQna - 호출");
		MemberDto idCheck = memberService.idCheck(memberDto);
		if (idCheck != null) {
			logger.debug("중복 확인");
			return new ResponseEntity<String>(IDCHECK, HttpStatus.OK);
		}
		
		if (memberService.regist(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("user_id", loginUser.getUser_id(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{user_id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("user_id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String user_id,
			HttpServletRequest request) {
//		logger.debug("user_id : {} ", user_id);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		logger.debug("user_id : {} ", request.getHeader("access-token"));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto isAdmin = memberService.isAdmin(user_id);
				MemberDto memberDto = memberService.userInfo(user_id);
				resultMap.put("userInfo", memberDto);
				if (isAdmin != null) {
					resultMap.put("message", ADMIN);
				} else {
					resultMap.put("message", SUCCESS);
				}
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "ID와 일치하는 회원정보 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/modify/{user_id}")
	public ResponseEntity<String> updateUser(@RequestBody MemberDto memberDto) {
		logger.debug("updateUser - 호출");
		logger.debug("" + memberDto);
		
		if (memberService.updateUser(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "ID와 일치하는 회원정보 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/mypage/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable String user_id) {
		logger.debug("deleteUser - 호출");
		if (memberService.deleteUser(user_id)) {
			System.out.println("deleteUser 조건 성립");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "모든 회원 정보를 반환한다.", response = List.class)
   	@GetMapping("/list")
   	public ResponseEntity<List<MemberDto>> retrieveUser() throws Exception {
   		logger.debug("retrieveUser - 호출");
   		return new ResponseEntity<List<MemberDto>>(memberService.retrieveUser(), HttpStatus.OK);
   	}
    
    @ApiOperation(value = "ID와 일치하는 회원정보 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/list/{user_id}")
	public ResponseEntity<String> listdeleteUser(@PathVariable String user_id) {
		logger.debug("deleteUser - 호출");
		if (memberService.deleteUser(user_id)) {
			System.out.println("deleteUser 조건 성립");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}
