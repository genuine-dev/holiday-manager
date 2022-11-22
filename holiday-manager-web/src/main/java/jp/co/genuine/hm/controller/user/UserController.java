package jp.co.genuine.hm.controller.user;

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

@Controller
@RequestMapping("user")
public class UserController {
	public UserController(UserService service) {
		this.service = service;
	}

	private final UserService service;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		UserList result = service.getUserList();
		model.addAttribute("userList", result.getUserList());
		return "user/list/user_list";
	}

	@RequestMapping(value="register", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		model.addAttribute("statusList", UserStatusEnum.statusMap());
		return "user/register/user_register";
	}

	@RequestMapping(value="register/confirm", method=RequestMethod.POST)
	public String userRegisterConfirm(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		model.addAttribute("statusList", UserStatusEnum.statusMap());
		return "user/register/user_register_confirm";
	}

	@RequestMapping(value="register/complete", method=RequestMethod.POST)
	public String userRegisterComplete(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		ResponseEntity<Void> response = service.postUser(userViewModel);
		checkResponseError(model, response);
		return "user/register/user_register_complete";
	}

	@RequestMapping(value="update/{userId}", method=RequestMethod.GET)
	public String userUpdate(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		checkPermit(userId);

		refresh(userViewModel, new UserId(userId));

		model.addAttribute("statusList", UserStatusEnum.statusMap());
		model.addAttribute("userId", userId);
		return "user/update/user_update";
	}

	@RequestMapping(value="update/{userId}/confirm", method=RequestMethod.POST)
	public String userUpdateConfirm(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		checkPermit(userId);

		model.addAttribute("statusList", UserStatusEnum.statusMap());
		model.addAttribute("userId", userId);

		checkValidationError(userViewModel, model);
		return "user/update/user_update_confirm";
	}

	@RequestMapping(value="update/{userId}/complete", method=RequestMethod.POST)
	public String userUpdateComplete(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		checkPermit(userId);

		ResponseEntity<Void> response = service.putUser(new UserId(userId), userViewModel);
		checkResponseError(model, response);
		return "user/update/user_update_complete";
	}

	@RequestMapping(value="delete/{userId}", method=RequestMethod.GET)
	public String userDelete(@PathVariable("userId") Integer userId, Model model) throws IOException {
		checkAdmin();

		ResponseEntity<Void> response = service.deleteUser(new UserId(userId));
		checkResponseError(model, response);
		return "user/delete/user_delete_complete";
	}

	private void checkPermit(Integer userId) {
		checkAdmin();

		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int loginId = user.getAccount().getUserId();
		if(loginId != userId) {
			throw new AccessDeniedException("loginID and targetID dont match.");
		}
	}

	private void checkAdmin() {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!user.getAccount().isAdminFlg()) {
			throw new AccessDeniedException("dont ADMIN.");
		}
	}

	private void checkResponseError(Model model, ResponseEntity<Void> response) {
		if(response.getStatusCode() != HttpStatus.OK) {
			model.addAttribute("isError", true);
			return;
		}

		model.addAttribute("isError", false);
	}

	private static void checkValidationError(UserViewModel userViewModel, Model model) {
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
	}

	private void refresh(UserViewModel userViewModel, UserId userId) {
		User user = service.getUser(userId);
		userViewModel.setUserName(user.getUserName().getValue());
		userViewModel.setMailAddress(user.getMailAddress().getValue());
		userViewModel.setStatus(user.getUserStatus().toString());
		userViewModel.setPassword(user.getPassword().getValue());

		userViewModel.setHireDate(user.getHireDate().getValue());
		userViewModel.setLeftoverHoliday(user.getLeftoverHoliday().getValue().toString());
	}
}
