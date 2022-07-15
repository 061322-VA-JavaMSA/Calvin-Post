package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;
import com.revature.services.RoleService;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		User principal = om.readValue(session.getAttribute("principal").toString(), User.class);
		String pathInfo = req.getPathInfo();

		if (pathInfo == null && principal.getRole().getRole().equals("MANAGER")) {
			RoleService rs = new RoleService();
			List<User> users;
			try (PrintWriter pw = res.getWriter()) {
				users = us.getUsersByRole(rs.getRole("EMPLOYEE"));
				List<UserDTO> usersDTO = new ArrayList<>();

				users.forEach(u -> usersDTO.add(new UserDTO(u)));

				pw.write(om.writeValueAsString(usersDTO));
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				res.sendError(404, "Not found.");
			}

		} else if (principal.getRole().getRole().equals("EMPLOYEE")) {
			pathInfo = pathInfo.substring(1);
			if (Integer.parseInt(pathInfo) == principal.getId()) {
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(principal));
			} else {
				int id = Integer.parseInt(pathInfo.substring(1));

				try (PrintWriter pw = res.getWriter()) {
					User u = us.getUserById(id);
					UserDTO uDTO = new UserDTO(u);

					pw.write(om.writeValueAsString(uDTO));

					res.setStatus(200);
				} catch (NotFoundException e) {
					log.error(e.getMessage());
					res.setStatus(404);
				}
			}
		} else {
			log.info("Sent error, 401 unauthorized.");
			res.sendError(401, "Unauthorized request.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		User principal = om.readValue(session.getAttribute("principal").toString(), User.class);
		String pathInfo = req.getPathInfo();
		System.out.println(pathInfo);

		if (pathInfo != null) {
			pathInfo = pathInfo.substring(1);
			if (Integer.parseInt(pathInfo) == principal.getId()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				String[] values = br.readLine().split("&");
				br.close();
				for (String val : values) {
					String[] v = val.split("=");
					if (v[0].equals("password")) {
						principal.setPassword(v[1]);
					}
					if (v[0].equals("firstName")) {
						principal.setFirstName(v[1]);
					}
					if (v[0].equals("lastName")) {
						principal.setLastName(v[1]);
					}
					if (v[0].equals("email")) {
						principal.setEmail(v[1].replace("%40", "@"));
					}
				}

				principal = us.updateUser(principal);
				session.setAttribute("principal", om.writeValueAsString(principal));

				String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
				res.setHeader("Set-Cookie", cookie);

				UserDTO principalDTO = new UserDTO(principal);
				try (PrintWriter pw = res.getWriter()) {
					pw.write(om.writeValueAsString(principalDTO));
					res.setStatus(200);
				}

			} else {
				log.info("Sent error, 401 unauthorized.");
				res.sendError(401, "Unauthorized.");
			}

		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
