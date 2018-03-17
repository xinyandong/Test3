package com.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用Action
 * 
 * @author dougang
 * 
 */
@SuppressWarnings("all")
public class GenericController extends ActionSupport {

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

}
