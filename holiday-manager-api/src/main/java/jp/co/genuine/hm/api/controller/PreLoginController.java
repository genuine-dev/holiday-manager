package jp.co.genuine.hm.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "prelogin")
public class PreLoginController {

	@GetMapping
	public String preLogin(HttpServletRequest request) {
		DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
		if(token == null) {
			throw new RuntimeException("could not get a token.");
		}
		return token.getToken();
	}
}
