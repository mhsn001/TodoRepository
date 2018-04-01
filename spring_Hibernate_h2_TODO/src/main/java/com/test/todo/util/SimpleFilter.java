package com.test.todo.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.todo.HomeController;

@Component
public class SimpleFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

@Override
public void init(FilterConfig arg0) throws ServletException {}

@Override
public void doFilter(ServletRequest req, ServletResponse resp,
                     FilterChain chain) throws IOException, ServletException {
	
	logger.info("####################### Setting CORS ");

    HttpServletResponse response=(HttpServletResponse) resp;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

    chain.doFilter(req, resp);
}

@Override
public void destroy() {}

}