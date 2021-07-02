
<%@ page
	import="com.assignment3.groupH.*, com.assignment3.groupH.DAO.*, java.util.List, java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/head.html"%>

<body>

	<div class="container">

		<%@ include file="/header.html"%>

		<%
		// if id does not exits empty fields will be shown
		String name = "", course = "Select a course -", gender = "";
		int id = 0, age = 0, courseId = 0;
		float courseScore = 0;

		if (request.getParameter("id") != null) {
			//get student data
			try {
				id = Integer.parseInt(request.getParameter("id"));
				Student s = StudentDAO.getStudent(id);
				name = s.getName();
				age = s.getAge();
				course = s.getCourseName();
				courseId = s.getCourseId();
				gender = s.getGender();
				courseScore = s.getCourseScore();
			} catch (Exception e) {
				//nothing					
			}

		}
		%>

		<main class="main">

			<section style="margin: 20px auto;">
				<header class="header">
					<h2>Update student</h2>
				</header>
				<form class="form" method="post" action="./students">

					<div>
						<label>Student name</label> <input type="text" name="name"
							value="<%=name%>" required>
					</div>
					<div>
						<label>Age</label> <input type="number" name="age"
							value="<%=age%>" required>
					</div>
					<div class="radio-group">
						<label>Male</label> <input type="radio" name="gender" value="Male"
							<%if (gender.equals("Male"))
	out.print("Checked");%>> <label>Female</label>
						<input type="radio" name="gender" value="Female"
							<%if (gender.equals("Female"))
	out.print("Checked");%>> <input
							type="hidden" name="requestType" value="put" /> <input
							type="hidden" name="id" value="<%=id%>" />
					</div>

					<div>
						<select name="courseid">
							<%
							out.print("<option selected value='" + courseId + "'>" + course + "</option>");
							for (Course c : CourseDAO.getCourseList()) {
								c.getId();
								out.print("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
							}
							%>
						</select>
					</div>
					<div>
						<label>Course score</label> <input type="number" name="coursescore"
							placeholder="Enter course percentage" value="<%=courseScore %>" required>
					</div>
					<div class="center">
						<input type="submit" value="Update">
					</div>

					<%
					if (request.getAttribute("msg") != null) {
						out.print("<p style='margin-top:20px;text-align:center;font-weight:bold;'>" + request.getAttribute("msg") + "</p>");
					}
					%>
				</form>



			</section>

			<div style="display: block; clear: both;"></div>

		</main>

		<%@ include file="/footer.html"%>

	</div>

</body>
</html>