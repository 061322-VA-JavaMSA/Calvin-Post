package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.CorsFix;

public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AuthService as = new AuthService();
	private ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(AuthServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User principal = as.login(username, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("userId", principal.getId());
			session.setAttribute("userRole", principal.getRole().getRole());
			session.setAttribute("principal", om.writeValueAsString(principal));
			
			// To make Chrome work with our cookie
			String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
			res.setHeader("Set-Cookie", cookie);
			
			UserDTO principalDTO = new UserDTO(principal);
			try (PrintWriter pw = res.getWriter()) {
				pw.write(om.writeValueAsString(principalDTO));
				res.setStatus(200);
			}
			
		} catch (AuthException | NotFoundException e) {
			log.error(e.getMessage());
			res.sendError(400, "Unable to login.");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		HttpSession session = req.getSession();
		
		session.invalidate();
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
