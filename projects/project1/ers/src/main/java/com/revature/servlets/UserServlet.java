package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");

		/*
		 * Extra path information associated with the URL the client sent when it made
		 * this request - ie: - "/1" if /users/1 - null if /users
		 */
		String pathInfo = req.getPathInfo();

		if (pathInfo == null) {
			HttpSession session = req.getSession();

//				if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(')) {
			if (true) {
				List<User> users = us.getUsers();
				List<UserDTO> usersDTO = new ArrayList<>();

				users.forEach(u -> usersDTO.add(new UserDTO(u)));

				try (PrintWriter pw = res.getWriter();) {
					pw.write(om.writeValueAsString(users));
				}

			} else {
				res.sendError(401, "Unauthorized request.");
			}

		} else {
			int id = Integer.parseInt(pathInfo.substring(1));

			try (PrintWriter pw = res.getWriter()) {
				User u = us.getUserById(id);
				UserDTO uDTO = new UserDTO(u);

				pw.write(om.writeValueAsString(uDTO));

				res.setStatus(200);
			} catch (NotFoundException e) {
				res.setStatus(404);
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}
}
