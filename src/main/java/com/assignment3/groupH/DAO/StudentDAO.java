package com.assignment3.groupH.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment3.groupH.*;

/**
 * 
 * @author Group H
 * 
 * @data 29/06/2021
 * 
 * @description DAO for Student table. Does all the communication with database
 *              and helps in achieving abstraction.
 */

public class StudentDAO {
	
	// select all the students and return as a list
	public static List<Student> getStudentList() {

		List<Student> studentList = new ArrayList<>();
		Connection con = DAO.getConnection();

		try {
			String query = "select StudentID, StudentName, Age, Gender, CourseName, CourseID, CourseScore from assignment3.StudentInfo s inner join assignment3.CourseInfo c on s.CID = c.CourseID;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student s = new Student();
				s.setName(rs.getString("StudentName"));
				s.setId(rs.getInt("StudentID"));
				s.setAge(rs.getInt("Age"));
				s.setGender(rs.getString("Gender"));
				s.setCourseName(rs.getString("CourseName"));
				s.setCourseId(rs.getInt("CourseID"));
				s.setCourseScore(rs.getFloat("CourseScore"));
				studentList.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;

	}

	// get a student for specifi id
	public static Student getStudent(int id) {

		Connection con = DAO.getConnection();

		try {
			String query = "select StudentID, StudentName, Age, Gender, CourseName, CourseID, CourseScore from assignment3.StudentInfo s inner join assignment3.CourseInfo c on s.CID = c.CourseID WHERE StudentID=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Student s = new Student();
				s.setName(rs.getString("StudentName"));
				s.setId(rs.getInt("StudentID"));
				s.setAge(rs.getInt("Age"));
				s.setGender(rs.getString("Gender"));
				s.setCourseName(rs.getString("CourseName"));
				s.setCourseId(rs.getInt("CourseID"));
				s.setCourseScore(rs.getFloat("CourseScore"));

				return s;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	// Inser a student
	public static boolean addStudent(Student s) {

		Connection con = DAO.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES (?, ?, ?, ?, ?);");

			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getGender());
			ps.setInt(4, s.getCourseId());
			ps.setFloat(5, s.getCourseScore());

			if (ps.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// Update existing student
	public static int updateStudent(Student s) {

		Connection con = DAO.getConnection();

		try {

			if (getStudent(s.getId()) == null)
				return DAO.NOT_EXISTS;

			PreparedStatement ps = con.prepareStatement(
					"UPDATE assignment3.StudentInfo SET StudentName=?, Age=?, Gender=?, CID=?, CourseScore=? WHERE StudentID=?");

			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getGender());
			ps.setInt(4, s.getCourseId());
			ps.setFloat(5, s.getCourseScore());
			ps.setInt(6, s.getId());

			if (ps.executeUpdate() > 0)
				return DAO.SUCCESS;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return DAO.FAIL;

	}

	// Delete existing student
	public static boolean deleteStudent(int id) {

		Connection con = DAO.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement("DELETE FROM assignment3.StudentInfo WHERE StudentID=?");
			ps.setInt(1, id);
			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
