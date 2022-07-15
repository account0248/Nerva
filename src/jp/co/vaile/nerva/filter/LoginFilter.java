package jp.co.vaile.nerva.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	String[] excludes;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String thisURL = ((HttpServletRequest) request).getRequestURI();
		if (((HttpServletRequest) request).getSession().getAttribute("loginCheck") == null) {
			if (!isExcludePath(thisURL)) {
				((HttpServletResponse) response).sendRedirect("/Nerva/jsp/error.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isExcludePath(String thisURL) {
		String[] excludes = this.excludes;
		for (String path : excludes) {
			if (thisURL.matches(path)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String exclude = filterConfig.getInitParameter("exclude");
		if (exclude == null)
			return;
		this.excludes = exclude.split(",");
	}

}
