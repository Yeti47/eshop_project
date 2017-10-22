package de.profil;

import javax.servlet.http.HttpServletRequest;

public abstract class WebUtility {
	
	public static String getNonNullParam(HttpServletRequest request, String paramName) {
		
		if(request == null || paramName == null)
			return "";
		
		String param = request.getParameter(paramName);
		
		return param == null ? "" : param.trim();
		
	}
	
	public static String escapeHtmlChars(String str) {
		
		if(str == null)
			return null;
		
		String result = str.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("'", "&apos;");
		result = result.replaceAll("&", "&amp;");
		
		return result;
		
	}

}
