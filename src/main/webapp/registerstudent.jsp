
<%@ page
	import="com.assignment3.groupH.*, com.assignment3.groupH.DAO.*, java.util.List, java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/head.html"%>

<body>

	<div class="container">

		<%@ include file="/header.html"%>

		<main class="main">

			<section style="margin: 20px auto;">
				<header class="header">
					<h2>Register student</h2>
				</header>
				<form class="form" method="post" action="./students">

					<div>
						<label>Student name</label> <input type="text" name="name"
							placeholder="Enter student name" required>
					</div>
					<div>
						<label>Age</label> <input type="number" name="age"
							placeholder="Enter age" required>
					</div>
					<div class="radio-group">
						<label>Male</label> <input type="radio" name="gender" value="Male">
						<label>Female</label> <input type="radio" name="gender"
							value="Female">
					</div>
					<div>
						<input type="hidden" name="requestType" value="post" />
					</div>
					<div>
						<select name="courseid">
							<option value="">Select a course -</option>
							<%
							for (Course c : CourseDAO.getCourseList()) {
								c.getId();
								out.print("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
							}
							%>
						</select>
					</div>
					<div>
						<label>Course score</label> <input type="number" name="coursescore"
							placeholder="Enter course percentage" required>
					</div>
					<div class="center">
						<input type="submit" value="Register">
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