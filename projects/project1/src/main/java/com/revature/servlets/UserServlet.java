package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {

	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		try {
			PrintWriter pw = res.getWriter();
			List<User> users = us.getUsers();
			List<UserDTO> userDTOs = new ArrayList<>();
			users.forEach(u -> userDTOs.add(new UserDTO(u)));
			pw.write(om.writeValueAsString(userDTOs));
			res.addHeader("Content-Type", "application/json");
			pw.close();
		} catch (SQLException e) {
			res.setStatus(400);
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
