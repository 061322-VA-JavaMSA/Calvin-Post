package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.util.CorsFix;

public class ReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReimbursementService rs = new ReimbursementService();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		String pathInfo = req.getPathInfo();
		if (session.getAttribute("userRole").equals("MANAGER")) {
			if (pathArgs[0].equals("author")) {
				List<Reimbursement> reimbs = rs.getReimbursementsByAuthorId(Integer.parseInt(pathArgs[1]));
			}
		} else if (session.getAttribute("userRole").equals("EMPLOYEE")) {
			if (pathInfo.su)
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
