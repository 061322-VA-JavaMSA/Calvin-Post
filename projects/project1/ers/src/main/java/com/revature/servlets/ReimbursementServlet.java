package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ReimbursementDTO;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.StatusService;
import com.revature.services.TypeService;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class ReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReimbursementService rs = new ReimbursementService();
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(ReimbursementServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		User principal = om.readValue(session.getAttribute("principal").toString(), User.class);
		System.out.println(principal);
		String pathInfo = req.getPathInfo();

		if (pathInfo == null && principal.getRole().getRole().equals("MANAGER")) {

			
			try (PrintWriter pw = res.getWriter()) {
			List<Reimbursement> reimbs = rs.getReimbursements();;
			List<ReimbursementDTO> reimbsDTO = new ArrayList<>();
			reimbs.forEach(r -> reimbsDTO.add(new ReimbursementDTO(r)));
			pw.write(om.writeValueAsString(reimbsDTO));
			} catch (NotFoundException e) {
				log.error(e.getMessage());
			}
		} else if (principal.getRole().getRole().equals("MANAGER") || principal.getId() == Integer.parseInt(pathInfo.substring(1))) {
			User requested = null;
			try (PrintWriter pw = res.getWriter()) {
				requested = us.getUserById(Integer.parseInt(pathInfo.substring(1)));
			
			List<Reimbursement> reimbs = rs.getReimbursementsByAuthor(requested);
			List<ReimbursementDTO> reimbsDTO = new ArrayList<>();
			reimbs.forEach(r -> reimbsDTO.add(new ReimbursementDTO(r)));
			
			pw.write(om.writeValueAsString(reimbsDTO));
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
				res.setStatus(400);
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				res.setStatus(404);
			}
		} else {
			log.info("Sent error, 401 unauthorized.");
			res.sendError(401, "Unauthorized.");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		User principal = om.readValue(session.getAttribute("principal").toString(), User.class);
		System.out.println(principal);
		String pathInfo = req.getPathInfo();

		// insert new reimbursement --employees only
		if (pathInfo == null && principal != null && principal.getRole().getRole().equals("EMPLOYEE")) {
			StatusService ss = new StatusService();
			TypeService ts = new TypeService();
			try {
				ReimbStatus status = ss.getStatus("PENDING");
				ReimbType type = ts.getType(req.getParameter("type"));
				Timestamp submitted = new Timestamp(System.currentTimeMillis());
//				Blob receipt = om.writeValueAsBytes(req.getParameter("receipt"));
//				int author = (int) session.getAttribute("userId");
				double amount = Double.parseDouble(req.getParameter("amount"));
				String description = req.getParameter("description");
				Reimbursement reimbToAdd = new Reimbursement();
				reimbToAdd.setAmount(amount);
				reimbToAdd.setReceipt(null);
				reimbToAdd.setAuthor(principal);
				reimbToAdd.setDescription(description);
				reimbToAdd.setSubmitted(submitted);
				reimbToAdd.setReimbType(type);
				reimbToAdd.setReimbStatus(status);
				try {
					rs.createReimbursement(reimbToAdd);
					res.setStatus(201);
				} catch (NotCreatedException e) {
					log.error(e.getMessage());
					res.setStatus(400);
				}
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				res.setStatus(404);
			}
		} else {
			log.info("Sent error, 401 unauthorized.");
			res.sendError(401, "Unauthorized.");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		HttpSession session = req.getSession();
		User principal = om.readValue(session.getAttribute("principal").toString(), User.class);
		System.out.println(principal);
		String pathInfo = req.getPathInfo();

		// insert new reimbursement --employees only
		if (pathInfo == null && principal != null && principal.getRole().getRole().equals("MANAGER")) {
			StatusService ss = new StatusService();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				Map<String, String> params = new HashMap<>();
				for (String s : br.readLine().split("&")) {
					String[] p = s.split("=");
					params.put(p[0], p[1]);
				}
				br.close();
				ReimbStatus status = ss.getStatus(params.get("status"));
				Timestamp resolved = new Timestamp(System.currentTimeMillis());
				Reimbursement reimbToUpdate = rs.getReimbursementById(Integer.parseInt(params.get("id")));
				reimbToUpdate.setResolver(principal);
				reimbToUpdate.setResolved(resolved);
				reimbToUpdate.setReimbStatus(status);

				rs.updateReimbursement(reimbToUpdate);
				res.setStatus(200);
			} catch (NotFoundException e) {
				res.sendError(404);
			}
		} else {
			log.info("Sent error, 401 unauthorized.");
			res.sendError(401, "Unauthorized.");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
