package com.assignment3_groupH.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.assignment3.groupH.*;
import com.assignment3.groupH.DAO.DAO;
import com.assignment3.groupH.DAO.StudentDAO;

/**
 * 
 * @author Group H
 * @data 29/06/2021
 * @description this servlet controls all get, post, update and delete request
 *              for the student routes. It does all the validation and provide
 *              approvide information or error message.
 */

@WebServlet("/lambtoncollege/students")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		List<Student> studentList;

		try {

			// delete student
			if (request.getParameter("deleteid") != null) {

				StudentDAO.deleteStudent(Integer.parseInt(request.getParameter("deleteid")));
				response.sendRedirect("students?deleted=true");
				return;
			} else {
				// select all students and provide to jsp file for rendering
				studentList = StudentDAO.getStudentList();
				if (studentList != null && studentList.size() > 0) {

					for (Student s : studentList) {
						out.print(s.getName() + "<br>");
					}
					request.setAttribute("studentList", studentList);
					request.getRequestDispatcher("../studentlist.jsp").forward(request, response);
				} else {
					respond(request, response, "../studentlist.jsp", "No student!");
				}
			}

		} catch (NumberFormatException e) {
			out.print("Wrong student ID must be an integer");
		} catch (Exception e) {
			out.print("Oops! something went very wrong!");
			e.printStackTrace();
		}

		// Finally
		DAO.closeConnection();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Student s = null;

		try {
			if (request.getParameter("name") == null || request.getParameter("age") == null
					|| request.getParameter("gender") == null || request.getParameter("courseid") == null|| request.getParameter("coursescore") == null) {
				// no data go back
				sendBack(request, response);

			} else {
				// got the data
				String name = request.getParameter("name");
				String age = request.getParameter("age");
				String gender = request.getParameter("gender");
				String id = request.getParameter("id");
				String courseId = request.getParameter("courseid");
				String courseScore = request.getParameter("coursescore");
				String requestType = request.getParameter("requestType");

				s = new Student();

				s.setName(name);
				s.setAge(Integer.parseInt(age));
				s.setGender(gender);
				s.setCourseId(Integer.parseInt(courseId));
				s.setCourseScore(Float.parseFloat(request.getParameter("coursescore")));

				if (requestType.equals("put") && id != null && !courseId.equals("") && !id.equals("") && !courseScore.equals("")) {
					// update request

					s.setId(Integer.parseInt(id));
					int status = StudentDAO.updateStudent(s);

					if (status == DAO.SUCCESS) {
						respond(request, response, "updatestudent", "Student updated!");
					} else if (status == DAO.NOT_EXISTS) {
						respond(request, response, "updatestudent", "Oops! no student exists with this ID!");
					} else {
						respond(request, response, "updatestudent", "Oops! someting went very wrong!");
					}

				} else if (requestType.equals("delete") && id != null && !id.equals("")) {

					// delete student

					StudentDAO.deleteStudent(Integer.parseInt(id));

				} else {
					// insert request

					if (StudentDAO.addStudent(s)) {
						respond(request, response, "registerstudent", "Student added!");
					} else {
						respond(request, response, "registerstudent", "Oops! someting went very wrong!");
					}
				}
			}
		} catch (NumberFormatException e) {
			sendBack(request, response);
		}
		// Finally
		DAO.closeConnection();
		out.close();
	}

	public static void respond(HttpServletRequest request, HttpServletResponse response, String file, String msg)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(file).forward(request, response);
	}

	public static void sendBack(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestType = request.getParameter("requestType");

		if (requestType.equals("post"))
			respond(request, response, "registerstudent", "All fields are required!");
		else if (requestType.equals("put"))
			respond(request, response, "updatestudent", "All fields are required!");
	}
}
