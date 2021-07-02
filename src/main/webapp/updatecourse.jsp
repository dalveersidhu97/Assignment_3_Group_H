
<%@ page
	import="com.assignment3.groupH.*, com.assignment3.groupH.DAO.*, java.util.List, java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/head.html"%>

<body>

	<div class="container">

		<%@ include file="/header.html"%>

		<%
		String name = "";
		int id = 0;

		if (request.getParameter("id") != null) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				Course c = CourseDAO.getCourse(id);
				name = c.getName();
				id = c.getId();
			} catch (Exception e) {
				//nothing					
			}

		}
		%>

		<main class="main">

			<section style="margin: 20px auto;">
				<header class="header">
					<h2>Update course</h2>
				</header>
				<form class="form" method="post" action="./courses">

					<div>
						<label>Name</label> <input type="text" name="name"
							value="<%=name%>" required> <input type="hidden"
							name="id" value="<%=id%>"> <input type="hidden"
							name="requestType" value="put" />
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