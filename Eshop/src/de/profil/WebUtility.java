package de.profil;

import javax.servlet.http.HttpServletRequest;

public abstract class WebUtility {
	
	public static String getNonNullParam(HttpServletRequest request, String paramName) {
		
		if(request == null || paramName == null)
			return "";
		
		String param = request.getParameter(paramName);
		
		return param == null ? "" : param.trim();
		
	}

}
