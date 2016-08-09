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
	 * indexPage
	 * 사이트 초기 진입 시 지정된 페이지로 포워딩한다.
	 * @param none
	 * @return indexPage 경로
	 */
	@RequestMapping(value = "/login.ngii", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Locale locale, Model model, String social, String email, String name)
	throws UnsupportedEncodingException
	{
		logger.info("Welcome smdc! The client locale is {}.", locale);
		
		// TODO - khj : 임시로 소셜 로그인을 막는다. 나중에 소셜 로그인 기능을 구현할거면 제대로 구현 된상태서 막은걸 풀어야 한다.
		social = "";

		String url = loginService.makeLoginValidationUrl(social);
		
		if(StringUtil.isEmpty(email))
		{
			if(!url.substring(0, 4).equals("http"))
				model.addAttribute("error", "empty email");
		}
		else
		{
			model.addAttribute("email", URLDecoder.decode(email, "UTF-8"));
		}
	
		return "redirect:" + url;
	}
	
}
