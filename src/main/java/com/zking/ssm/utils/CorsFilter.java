package com.zking.ssm.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置tomcat允许跨域访问
 * 
 * @author Administrator
 *
 */
public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	// @Override
	// public void doFilter(ServletRequest servletRequest, ServletResponse
	// servletResponse, FilterChain filterChain)
	// throws IOException, ServletException {
	// HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
	//
	// // Access-Control-Allow-Origin就是我们需要设置的域名
	// // Access-Control-Allow-Headers跨域允许包含的头。
	// // Access-Control-Allow-Methods是允许的请求方式
	// httpResponse.addHeader("Access-Control-Allow-Origin", "*");// *,任何域名
	// httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,
	// DELETE");
	// // httpResponse.setHeader("Access-Control-Allow-Headers", "Origin,
	// // X-Requested-With, Content-Type, Accept");
	//
	// // 允许请求头Token
	// httpResponse.setHeader("Access-Control-Allow-Headers",
	// "Origin,X-Requested-With, Content-Type, Accept, Token");
	// HttpServletRequest req = (HttpServletRequest) servletRequest;
	// System.out.println("Token=" + req.getHeader("Token"));
	// if("OPTIONS".equals(req.getMethod())) {
	// return;
	// }
	//
	//
	// filterChain.doFilter(servletRequest, servletResponse);
	// }

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		HttpServletRequest req = (HttpServletRequest) servletRequest;

		// Access-Control-Allow-Origin就是我们需要设置的域名
		// Access-Control-Allow-Headers跨域允许包含的头。
		// Access-Control-Allow-Methods是允许的请求方式
		resp.setHeader("Access-Control-Allow-Origin", "*");// *,任何域名
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With, Content-Type, Accept,jwt");
        
		// 允许请求头Token
		resp.setHeader("Access-Control-Expose-Headers","jwt");
		
		
		
		// httpResponse.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,
		// Content-Type, Accept, Token");
		// System.out.println("Token=" + req.getHeader("Token"));

		if ("OPTIONS".equals(req.getMethod())) {// axios的ajax会发两次请求，第一次提交方式为：option，直接返回即可
			return;
		}
		System.out.println("跨域了，走你");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}