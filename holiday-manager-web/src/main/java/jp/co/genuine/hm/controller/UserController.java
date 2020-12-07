package jp.co.genuine.hm.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.genuine.hm.model.user.UserStatus;

@Controller
public class UserController {

	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList(Model model) {
        String url = "http://localhost:8082/user";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

           //optional default is GET
            con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.print(responseCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "user_list";
	}

	@RequestMapping(value="/userregister", method=RequestMethod.GET)
	public String userRegister(Model model) {
		// TODO:APIに変える
		List<UserStatus> statusList = new ArrayList<UserStatus>();
		UserStatus active = new UserStatus();
		active.setStatus("ACTIVE");
		statusList.add(active);
		UserStatus leave = new UserStatus();
		leave.setStatus("LEAVE");
		statusList.add(leave);
		UserStatus retired = new UserStatus();
		retired.setStatus("RETIRED");
		statusList.add(retired);
        model.addAttribute("statusList", statusList);
		return "user_register";
	}
}
