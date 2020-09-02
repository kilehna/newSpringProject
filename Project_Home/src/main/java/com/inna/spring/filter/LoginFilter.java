package com.inna.spring.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//web.xml 에 필터 정의와 필터 맵핑을 어노테이션을 이용해서 할수 있다.
@WebFilter({"/users/private/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		if(id != null) {
			chain.doFilter(request, response);
		}else {
			String url=req.getRequestURI();
			String query=req.getQueryString();
			String encodedUrl=null;
			if(query==null) {
				encodedUrl=URLEncoder.encode(url);
			}else {
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			HttpServletResponse res=(HttpServletResponse)response;
			String cPath=req.getContextPath();
			res.sendRedirect(cPath+"/users/loginform.do?url="+encodedUrl);
			
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}