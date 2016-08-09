package kr.posco.erp.poscofms.login;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.posco.erp.poscofms.login.service.LoginService;
import kr.posco.erp.poscofms.util.StringUtil;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService = null; 
	

	//@Autowired
	//MainService mainService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private int inteval = 30 * 60;			// 세션 만료될 시간 (30분)
	
	/**
	 * loginPage
	 * 사이트 초기 진입 시 로그인 페이지로 포워딩한다.
	 * @param none
	 * @return indexPage 경로
	 */
	@RequestMapping(value = "/login.posco", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Locale locale, Model model, String social, String email, String name)
	throws UnsupportedEncodingException
	{
		logger.info("Welcome poscoFMS! The client locale is {}.", locale);
		
		return "main/login";
	}
	
}
