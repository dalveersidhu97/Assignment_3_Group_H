
<%@ page
	import="com.assignment3.groupH.*, java.util.List, java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/head.html"%>

<body>

	<div class="container">


		<%@ include file="/header.html"%>

		<main class="main">

			<section>

				<%
				if (request.getAttribute("msg") != null) {
					out.print("<p style='margin-top:20px;text-align:center;font-weight:bold;'>" + request.getAttribute("msg") + "</p>");
				}
				List<Course> courseList = (ArrayList<Course>) request.getAttribute("courseList");
				if (courseList != null) {
				%>

				<header class="header">
					<h2>Course List</h2>
				</header>
				<table class="table-columns-4">
					<tr>
						<th>Course ID</th>
						<th>Course Name</th>
						<th>Number of Students</th>
						<th>Average Score</th>
					</tr>

					<%
					String output = "";

					for (Course c : courseList) {
						output += "<tr>";
						output += "<td>" + c.getId() + "</td>" + "<td>" + c.getName() + "</td>" + "<td>" + c.getNumberOfStudents() + "</td>"
								+"<td>" + c.getAverageScore() + "</td>"
						+ "<td><a href='updatecourse?id=" + c.getId() + "'>update</a></td>" + "<td><a href='deletecourse?deleteid="
						+ c.getId() + "'>delete</a></td>";
						output += "</tr>";
					}

					out.print(output);
					}
					%>

				</table>
			</section>

		</main>

		<%@ include file="/footer.html"%>

	</div>

</body>
</html>