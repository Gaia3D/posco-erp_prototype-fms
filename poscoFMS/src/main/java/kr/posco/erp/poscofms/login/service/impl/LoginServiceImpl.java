package kr.posco.erp.poscofms.login.service.impl;

import org.springframework.stereotype.Service;

import kr.posco.erp.poscofms.login.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService{
	
	//@Autowired
	//private LoginDao loginDao;
	
	@Override
	public String makeLoginValidationUrl(String snsType) {
		return "/loginCallback.ngii";
	}

}
