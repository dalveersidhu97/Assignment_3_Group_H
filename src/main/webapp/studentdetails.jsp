
<%@ page
	import="com.assignment3.groupH.*, com.assignment3.groupH.DAO.*, java.util.List, java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%@ include file="head.html"%>

<body>

	<div class="container">


		<%@ include file="/header.html"%>

		<%
		String name = "", course = "", gender = "";
		int id = 0, age = 0, courseId = 0;
		float courseScore = 0;

		if (request.getParameter("id") != null) {
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

			<section>
				<header class="header">
					<h2>Students Details</h2>
				</header>
				<table class="student-detail-table">
					<tr>
						<th>Student ID</th>
						<td><%=id%></td>
					</tr>
					<tr>
						<th>Student name</th>
						<td><%=name%></td>
					</tr>
					<tr>
						<th>Age</th>
						<td><%=age%> years</td>
					</tr>
					<tr>
						<th>Gender</th>
						<td><%=gender%></td>
					</tr>
					<tr>
						<th>Course name</th>
						<td><%=course%></td>
					</tr>
					<tr>
						<th>Course score</th>
						<td><%=courseScore%>%</td>
					</tr>

				</table>
				<p style="text-align: center; margin-top: 15px;">
					<a href="updatestudent?id=<%=id%>">Update</a>
				</p>
			</section>

		</main>

		<%@ include file="/footer.html"%>

	</div>

</body>
</html>