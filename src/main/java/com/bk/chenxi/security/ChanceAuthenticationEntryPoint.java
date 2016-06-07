package com.bk.chenxi.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.model.ResultModel;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 当权限校验失败时调用该类的commence方法。继承的目的是为了扩展ajax的情况，当遇到url中带有‘ajax’字符串的时候，返回json信息 。
 * 
 * @author biki
 * 
 */
public class ChanceAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory
			.getLogger(ChanceAuthenticationEntryPoint.class);

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Deprecated
	public ChanceAuthenticationEntryPoint() {

	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = request;
		HttpServletResponse httpResponse = response;

		String redirectUrl = null;

		String url = request.getRequestURI();

		if (logger.isDebugEnabled()) {
			logger.debug("url:" + url);
		}

		// 非ajax请求
		if (url.indexOf("ajax") == -1) {

			if (this.isUseForward()) {

				if (this.isForceHttps() && "http".equals(request.getScheme())) {
					// First redirect the current request to HTTPS.
					// When that request is received, the forward to the login
					// page will be used.
					redirectUrl = buildHttpsRedirectUrlForRequest(httpRequest);
				}

				if (redirectUrl == null) {
					String loginForm = determineUrlToUseForThisRequest(
							httpRequest, httpResponse, authException);

					if (logger.isDebugEnabled()) {
						logger.debug("Server side forward to: " + loginForm);
					}

					RequestDispatcher dispatcher = httpRequest
							.getRequestDispatcher(loginForm);

					dispatcher.forward(request, response);

					return;
				}
			} else {
				// redirect to login page. Use https if forceHttps true

				redirectUrl = buildRedirectUrlToLoginPage(httpRequest,
						httpResponse, authException);

			}

			redirectStrategy.sendRedirect(httpRequest, httpResponse,
					redirectUrl);
		} else {
			// ajax请求，返回json，替代redirect到login page
			if (logger.isDebugEnabled()) {
				logger.debug("ajax request or post");
			}

			ObjectMapper objectMapper = new ObjectMapper();
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
					.createJsonGenerator(response.getOutputStream(),
							JsonEncoding.UTF8);
			try {
				ResultModel<String> rs = new ResultModel<String>();
				rs.setReturnValue("a");
				objectMapper.writeValue(jsonGenerator, rs);
			} catch (JsonProcessingException ex) {
				throw new HttpMessageNotWritableException(
						"Could not write JSON: " + ex.getMessage(), ex);
			}
		}
	}

}
