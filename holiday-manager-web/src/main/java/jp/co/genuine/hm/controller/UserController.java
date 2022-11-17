package jp.co.genuine.hm.controller;

import jp.co.genuine.hm.model.user.*;
import jp.co.genuine.hm.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UserController {
	public UserController(UserService service) {
		this.service = service;
	}

	private final UserService service;

	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		UserList result = service.getUserList();
		model.addAttribute("userList", result.getUserList());
		return "user/user_list";
	}

	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user/user_register";
	}

	@RequestMapping(value="/user/register/confirm", method=RequestMethod.POST)
	public String userRegisterConfirm(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user/user_register_confirm";
	}

	@RequestMapping(value="/user/register/complete", method=RequestMethod.POST)
	public String userRegisterComplete(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		ResponseEntity<Void> response = service.postUser(userViewModel);
		if(response.getStatusCode() != HttpStatus.OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user/user_register_complete";
	}

	@RequestMapping(value="/user/update/{userId}", method=RequestMethod.GET)
	public String userUpdate(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int loginId = user.getAccount().getUserId();
		if(!user.getAccount().isAdminFlg() && loginId != userId) {
			throw new AccessDeniedException("loginID and targetID dont match.");
		}

		UserId userIdModel = new UserId();
		userIdModel.setValue(userId);
		User result = service.getUser(userIdModel);
		userViewModel.setUserName(result.getUserName().getValue());
		userViewModel.setMailAddress(result.getMailAddress().getValue());
		userViewModel.setStatus(result.getUserStatus().toString());
		userViewModel.setPassword(result.getPassword().getValue());

		userViewModel.setHireDate(result.getHireDate().getValue());
		userViewModel.setLeftoverHoliday(result.getLeftoverHoliday().getValue().toString());

		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		model.addAttribute("userId", userId);
		return "user/user_update";
	}

	@RequestMapping(value="/user/update/{userId}/confirm", method=RequestMethod.POST)
	public String userUpdateConfirm(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int loginId = user.getAccount().getUserId();
		if(!user.getAccount().isAdminFlg() && loginId != userId) {
			throw new AccessDeniedException("loginID and targetID dont match.");
		}

		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		model.addAttribute("userId", userId);

		if(!StringUtils.isEmpty(userViewModel.getPassword())) {
			int passwordLength = userViewModel.getPassword().length();
			if(6 > passwordLength || passwordLength >= 21) {
				model.addAttribute("validationError", true);
			} else {
				model.addAttribute("validationError", false);
			}
		} else {
			model.addAttribute("validationError", false);
		}
		return "user/user_update_confirm";
	}

	@RequestMapping(value="/user/update/{userId}/complete", method=RequestMethod.POST)
	public String userUpdateComplete(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int loginId = user.getAccount().getUserId();
		if(!user.getAccount().isAdminFlg() && loginId != userId) {
			throw new AccessDeniedException("loginID and targetID dont match.");
		}

		UserId userIdModel = new UserId();
		userIdModel.setValue(userId);
		ResponseEntity<Void> response = service.putUser(userIdModel, userViewModel);
		if(response.getStatusCode() != HttpStatus.OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user/user_update_complete";
	}

	@RequestMapping(value="/user/delete/{userId}", method=RequestMethod.GET)
	public String userDelete(@PathVariable("userId") Integer userId, Model model) throws IOException {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!user.getAccount().isAdminFlg()) {
			throw new AccessDeniedException("dont ADMIN.");
		}

		UserId userIdModel = new UserId();
		userIdModel.setValue(userId);
		ResponseEntity<Void> response = service.deleteUser(userIdModel);
		if(response.getStatusCode() != HttpStatus.OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user/user_delete_complete";
	}
}
