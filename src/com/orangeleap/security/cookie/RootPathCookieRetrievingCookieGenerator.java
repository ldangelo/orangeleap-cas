package com.orangeleap.security.cookie;

public class RootPathCookieRetrievingCookieGenerator extends org.jasig.cas.web.support.CookieRetrievingCookieGenerator {
	
	@Override
	public void setCookiePath(String s) {
		// Dont allow InitialFlowSetupAction to set cookie path to CAS context path
		super.setCookiePath("/");
	}
}
