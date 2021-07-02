package com.assignment3_groupH.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.assignment3.groupH.Course;
import com.assignment3.groupH.DAO.DAO;
import com.assignment3.groupH.DAO.StudentDAO;
import com.assignment3.groupH.DAO.CourseDAO;

import jakarta.servlet.annotation.WebServlet;

/*
 * @author: Group H
 * @date: 29/06/2021
 * description: This Servlet handles GET, POST, UPDATE and DELETE requests for course routes. It does all the validation and provide appropriate response and handles exceptions
 */

@WebServlet("/lambtoncollege/courses")

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		List<Course> courseList;

		try {

			// delete course
			if (request.getParameter("deleteid") != null) {

				CourseDAO.deleteCourse(Integer.parseInt(request.getParameter("deleteid")));
				response.sendRedirect("courses");
				return;
			} else {
				// get all courses and provide to jsp file for rendering
				courseList = CourseDAO.getCourseList();
				if (courseList != null && courseList.size() > 0) {
					
					request.setAttribute("courseList", courseList);
					request.getRequestDispatcher("../courselist.jsp").forward(request, response);
				} else {
					respond(request, response, "../courselist.jsp", "No Course!");
				}
			}

		} catch (Exception e) {
			out.print("Something went very wrong!");
			e.printStackTrace();
		}

		// Finally
		DAO.closeConnection();
		out.close();

	}

	// handle create and update requests

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String requestType = request.getParameter("requestType");

		Course c = null;

		try {
			if (name == null) {
				// wrong request go back with error message

				sendBack(request, response);
				return;

			} else {
				// got data
				c = new Course();
				c.setName(name);
			}

			// use data
			if (requestType.equals("put") && id != null) {
				// update request
				c.setId(Integer.parseInt(id));
				int status = CourseDAO.updateCourse(c);

				if (status == DAO.SUCCESS) {
					respond(request, response, "addcourse", "Course updated!");
				} else if (status == DAO.ALREADY_EXISTS) {
					respond(request, response, "addcourse", "Oops! Course already exists!");
				} else if (status == DAO.NOT_EXISTS) {
					respond(request, response, "addcourse", "Oops! no Course exists with this ID!");
				} else {
					respond(request, response, "addcourse", "Oops! failed to update the course!");
				}

			} else if (requestType.equals("delete")) {
				// delete Course
				CourseDAO.deleteCourse(Integer.parseInt(request.getParameter("deleteid")));
				response.sendRedirect("courses");
			} else {
				// insert request
				int status = CourseDAO.addCourse(c);
				if (status == DAO.SUCCESS) {
					request.setAttribute("course", c);
					respond(request, response, "addcourse", "Course added!");
				} else if (status == DAO.ALREADY_EXISTS) {
					request.setAttribute("course", c);
					respond(request, response, "addcourse", "Oops! this course already exists!");

				} else {
					respond(request, response, "addcourse", "Oops! someting went very wrong!");

				}
			}

		} catch (NumberFormatException e) {
			out.print("Oops! Wrong Input!");
		}

		// The connection was opened by singleton DAO class once so that we do not need
		// to get connection again.
		DAO.closeConnection();
		out.close();
	}

	// this function is to forward request with error message
	public static void sendBack(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestType = request.getParameter("requestType");

		if (requestType.equals("post"))
			respond(request, response, "addcourse", "All fields are required!");
		else if (requestType.equals("put"))
			respond(request, response, "updatecourse", "All fields are required!");
	}

	// this is too
	public static void respond(HttpServletRequest request, HttpServletResponse response, String file, String msg)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(file).forward(request, response);
	}

}
