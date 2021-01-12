package jp.co.genuine.hm.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.genuine.hm.model.user.RegisterUserRequest;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.service.user.UserService;
import jp.co.genuine.hm.service.user.UserServiceImpl;

@Controller
public class UserController {
	CloseableHttpClient client = HttpClients.createDefault();
	UserService service = new UserServiceImpl();

	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		UserList userList = service.getUserList();
		return "user_list";
	}

	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("registerUserRequest") RegisterUserRequest registerUserRequest, Model model) {
		// TODO:APIに変える
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user_register";
	}

	@RequestMapping(value="/user/register/confirm", method=RequestMethod.POST)
	public String userRegisterConfirm(@ModelAttribute("registerUserRequest") RegisterUserRequest registerUserRequest, Model model) {
		// TODO:APIに変える
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("ACTIVE", "在籍中");
		statusList.put("LEAVE", "休職中");
		statusList.put("RETIRED", "退職");
		model.addAttribute("statusList", statusList);
		return "user_register_confirm";
	}

	@RequestMapping(value="/user/register/complete", method=RequestMethod.POST)
	public String userRegisterCompllete(@ModelAttribute("registerUserRequest") RegisterUserRequest registerUserRequest, Model model) {
		// TODO:APIに変える
		service.postUser(registerUserRequest);
		return "user_register_complete";
	}
}
