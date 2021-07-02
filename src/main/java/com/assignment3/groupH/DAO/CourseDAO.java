package com.assignment3.groupH.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.assignment3.groupH.Course;

/**
 * 
 * @author Group H
 * @data 29/06/2021
 * @description This class provide all the functionality for interacting with
 *              database for selecting , updating or deleting etc course data
 *              and it separates the business logic.
 */

public class CourseDAO {

	// Select all the courses and return as a list
	public static List<Course> getCourseList() {

		List<Course> courseList = new ArrayList<>();
		Connection con = DAO.getConnection();

		try {
			final String query = "select c.CourseID, c.CourseName, count(s.StudentID) as 'Number of Students', avg(CourseScore) as 'Average Course Score' from assignment3.StudentInfo s right join assignment3.CourseInfo c on s.CID = c.CourseID group by c.CourseID;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Course c = new Course();
				c.setName(rs.getString("CourseName"));
				c.setId(rs.getInt("CourseID"));
				c.setNumberOfStudents(rs.getInt("Number of Students"));
				c.setAverageScore(rs.getFloat("Average Course Score"));
				courseList.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courseList;

	}

	// return course by for specific id
	public static Course getCourse(int id) {

		Connection con = DAO.getConnection();

		try {
			final String query = "select c.CourseID, c.CourseName, count(s.StudentID) as 'Number of Students' from assignment3.StudentInfo s right join assignment3.CourseInfo c on s.CID = c.CourseID WHERE CourseID=? group by c.CourseID;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Course c = new Course();
				c.setName(rs.getString("CourseName"));
				c.setId(rs.getInt("CourseID"));
				c.setNumberOfStudents(rs.getInt("Number of Students"));

				return c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	// Inser a course
	public static int addCourse(Course c) {

		Connection con = DAO.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement("INSERT INTO assignment3.CourseInfo (CourseName) VALUES (?);");

			ps.setString(1, c.getName());

			if (ps.executeUpdate() > 0)
				return DAO.SUCCESS;

		} catch (SQLIntegrityConstraintViolationException e) {
			return DAO.ALREADY_EXISTS;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return DAO.FAIL;

	}

	// UPDATE
	public static int updateCourse(Course c) {

		Connection con = DAO.getConnection();

		try {

			if (getCourse(c.getId()) == null)
				return DAO.NOT_EXISTS;

			PreparedStatement ps = con
					.prepareStatement("UPDATE assignment3.CourseInfo SET CourseName=? WHERE CourseID=?");

			ps.setString(1, c.getName());
			ps.setInt(2, c.getId());

			if (ps.executeUpdate() > 0)
				return DAO.SUCCESS;

		} catch (SQLIntegrityConstraintViolationException e) {
			return DAO.ALREADY_EXISTS;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return DAO.FAIL;

	}

	// DELETE Existing Course
	public static boolean deleteCourse(int id) {

		Connection con = DAO.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement("DELETE FROM assignment3.CourseInfo WHERE CourseID=?");
			ps.setInt(1, id);
			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
