
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
				// there is an message form Servlet
				if (request.getAttribute("msg") != null) {
					out.print("<p style='margin-top:20px;text-align:center;font-weight:bold;'>" + request.getAttribute("msg") + "</p>");
				}
				// was previus request a delte request
				if (request.getParameter("deleted") != null) {
					if (request.getParameter("deleted").equals("true")) {
						out.print("<p class='infobox'>Student deleted!</p>");
					}
				}

				List<Student> studentList = (ArrayList<Student>) request.getAttribute("studentList");
				if (studentList != null) {
				%>

				<header class="header">
					<h2>Students List</h2>
				</header>
				<table class="table-columns-4">
					<tr>
						<th>Student ID</th>
						<th>Student name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Course name</th>
						<th>Course score</th>
					</tr>

					<%
					String output = "";
					request.setAttribute("error", "there is an error");

					for (Student s : studentList) {
						output += "<tr>";
						output += "<td>" + s.getId() + "</td>" + "<td>" + s.getName() + "</td>" + "<td>" + s.getAge() + "</td>" + "<td>"
						+ s.getGender() + "</td>" + "<td>" + s.getCourseName() + "<td>" + s.getCourseScore() +"%</td>" + "<td><a href='studentdetails?id="
						+ s.getId() + "'>details</a></td>" + "<td><a href='updatestudent?id=" + s.getId() + "'>update</a></td>"
						+ "<td><a href='deletestudent?deleteid=" + s.getId() + "'>delete</a></td>";
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