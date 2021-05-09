package jp.co.genuine.hm.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.model.user.UserId;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.model.user.UserViewModel;
import jp.co.genuine.hm.service.user.UserService;
import jp.co.genuine.hm.service.user.UserServiceImpl;

@Controller
public class UserController {
	ObjectMapper mapper = new ObjectMapper();
	CloseableHttpClient client = HttpClients.createDefault();
	UserService service = new UserServiceImpl();

	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		UserList result = service.getUserList();
		model.addAttribute("userList", result.getUserList());
		return "user_list";
	}

	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user_register";
	}

	@RequestMapping(value="/user/register/confirm", method=RequestMethod.POST)
	public String userRegisterConfirm(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user_register_confirm";
	}

	@RequestMapping(value="/user/register/complete", method=RequestMethod.POST)
	public String userRegisterCompllete(@ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		CloseableHttpResponse response = service.postUser(userViewModel);
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user_register_complete";
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

		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		model.addAttribute("userId", userId);
		return "user_update";
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
		return "user_update_confirm";
	}

	@RequestMapping(value="/user/update/{userId}/complete", method=RequestMethod.POST)
	public String userUpdateCompllete(@PathVariable("userId") Integer userId, @ModelAttribute("userViewModel") UserViewModel userViewModel, Model model) throws IOException {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int loginId = user.getAccount().getUserId();
		if(!user.getAccount().isAdminFlg() && loginId != userId) {
			throw new AccessDeniedException("loginID and targetID dont match.");
		}

		UserId userIdModel = new UserId();
		userIdModel.setValue(userId);
		CloseableHttpResponse response = service.putUser(userIdModel, userViewModel);
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user_update_complete";
	}

	@RequestMapping(value="/user/delete/{userId}", method=RequestMethod.GET)
	public String userDelete(@PathVariable("userId") Integer userId, Model model) throws IOException {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!user.getAccount().isAdminFlg()) {
			throw new AccessDeniedException("dont ADMIN.");
		}

		UserId userIdModel = new UserId();
		userIdModel.setValue(userId);
		CloseableHttpResponse response = service.deleteUser(userIdModel);
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}
		return "user_delete_complete";
	}
}
