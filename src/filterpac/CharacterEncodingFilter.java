package filterpac;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Title:
 * @Description: 
 * @Create on: 2009-9-3 下午10:41:33
 * @Author:LJY
 * @Version:1.0
 */
public class CharacterEncodingFilter implements Filter{


		public void destroy() {
		}

		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			request.setCharacterEncoding("GBK");
			  
			
			chain.doFilter(request, response);
			
		}

		public void init(FilterConfig arg0) throws ServletException {
		}


}
