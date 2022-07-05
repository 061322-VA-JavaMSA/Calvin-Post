package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		List<User> users = us.getUsers();
		List<UserDTO> userDtos = new ArrayList<>();
		users.forEach(user -> userDtos.add(new UserDTO(user)));
		pw.write(om.writeValueAsString(userDtos));
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
	}

}
