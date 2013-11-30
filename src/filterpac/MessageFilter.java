package filterpac;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 为了信息在redirect时不会消失,使用session暂存message．
 * 而此filter负责把消息 从session 搬回到 request．
 * 
 * @see com.sinosoft.common.core.web.action.BaseAction#saveMessage2Session(javax.servlet.http.HttpServletRequest, String)
 */
public class MessageFilter implements Filter {

	public void destroy() {

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		List customMessages = (List) request.getSession().getAttribute("customMessages");
		
		if (customMessages != null) {
			request.setAttribute("customMessages", customMessages);
			request.getSession().removeAttribute("customMessages");
		}
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
