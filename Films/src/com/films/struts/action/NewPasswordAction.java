/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.films.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.films.domain.Users;
import com.films.service.inter.IUserService;
import com.films.struts.form.NewPasswordForm;
import com.films.utils.MyTools;

/** 
 * MyEclipse Struts
 * Creation date: 10-16-2012
 * 
 * XDoclet definition:
 * @struts.action parameter="flag"
 */
public class NewPasswordAction extends DispatchAction {
	
	Users user = null;
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/** 
	 * Method checkPassword
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward checkPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//get old password and check is corret?
		String oldPwd = request.getParameter("oldPassword");
		user = (Users) request.getSession().getAttribute("loginUser");
		if(MyTools.MD5(oldPwd).equals(user.getUpassword())){
			out.println("1");
		}else{
			out.println("0");
		}
		return null;
	}
	
	//commit new password 
	public ActionForward setPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		NewPasswordForm newPasswordForm = (NewPasswordForm) form;
		String newPwd = newPasswordForm.getNewPassWord();
		user.setUpassword(MyTools.MD5(newPwd));
		userService.update(user);
		request.getSession().invalidate();
		//window.history.back(-1);
		out.print("<script language='javascript'>alert('Change password success!Please relogin!');window.history.back(-1);</script>");
		return null;
	}
}