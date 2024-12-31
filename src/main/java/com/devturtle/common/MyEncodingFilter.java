package com.devturtle.common;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


/**
 * -------------------------------------------------------------------------------------------------------------- 
 * Spring Security Filter
 *  - 일반적인 HTTP 필터(jakarta.servlet.Filter 인터페이스 구현)
 *  - doFilter() 오버라이딩
 *  
 *  - public interface Filter
 *  java.lang.Object
 *		javax.servlet.GenericFilter
 *			javax.servlet.http.HttpFilter
 *          public abstract class HttpFilter extends GenericFilter implement Serializable, Filter, FilterConfig
 * --------------------------------------------------------------------------------------------------------------
 *                              ┌─> SecurityContext	<─┐  
 *  request    ---->   		    │  					  │ 
 *  response   <---  AuthenticationFilter ---> AuthorizationFilter --> Controller
 * -------------------------------------------------------------------------------------------------------------- 
 */
// 필터의 URL 패턴을 지정 (모든 요청에 대해 필터 적용)
@WebFilter("/*")
public class MyEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 작업 (필요하면 구현)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	
    	// HttpServletRequest로 형변환하여 URI를 확인
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		// 정적 리소스 요청은 필터 제외
		if (uri.startsWith(contextPath + "/css/") 		|| 
			uri.startsWith(contextPath + "/js/")  		||
			uri.startsWith(contextPath + "/assets/")  	||
			uri.startsWith(contextPath + "/ddd/vendor/")  	||
			uri.startsWith(contextPath + "/ddd/css/")  	||
			uri.startsWith(contextPath + "/images/")
		) {
			chain.doFilter(request, response);
			return;
		}

		
        // 요청과 응답에 UTF-8 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 필터 체인으로 요청을 다음 단계로 전달
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 필터 종료 작업 (필요하면 구현)
    }
}

/* web.xml에 필터 등록 설정  
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>com.example.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
 
*/



