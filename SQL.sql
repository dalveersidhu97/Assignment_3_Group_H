create schema if not exists assignment3;

CREATE TABLE if not exists assignment3.CourseInfo (
    CourseID int primary key auto_increment,
    CourseName varchar(255) not null unique
);

CREATE TABLE if not exists assignment3.StudentInfo (
    StudentID int primary key auto_increment,
    StudentName varchar(30) not null,
    Age int check(Age > 15 AND Age <=100),
    CID int,
    CourseScore float default 0 check(CourseScore<=100 AND CourseScore>=0),
    Gender varchar(20) check(Gender IN('Male', 'Female', 'Other') ),
    FOREIGN KEY (CID) REFERENCES assignment3.CourseInfo(CourseID)
);

INSERT INTO assignment3.CourseInfo (CourseName) VALUES ('Computer Software');
INSERT INTO assignment3.CourseInfo (CourseName) VALUES ('Mobile Development');
INSERT INTO assignment3.CourseInfo (CourseName) VALUES ('Business Management');
INSERT INTO assignment3.CourseInfo (CourseName) VALUES ('Project Management');

INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES ('Dalveer Singh', 24, 'Male', 1, 98);
INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES ('Parneet Kaur', 24, 'Female', 1, 97);
INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES ('Ravneet Kaur', 28, 'Female', 1, 96);
INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES ('Amandeep Kaur', 28, 'Female', 1, 95);
INSERT INTO assignment3.StudentInfo (StudentName, Age, Gender, CID, CourseScore) VALUES ('Laddi Sidhu', 24, 'Male', 2, 94);

UPDATE assignment3.StudentInfo SET StudentName="Dalveer" WHERE StudentID=6;

select * from assignment3.StudentInfo;
select * from assignment3.CourseInfo;

select StudentID, StudentName, Age, Gender, CourseName from assignment3.StudentInfo s inner join assignment3.CourseInfo c on s.CID = c.CourseID;
select c.CourseID, c.CourseName, count(s.StudentID) as 'Number of Students' from assignment3.StudentInfo s inner join assignment3.CourseInfo c on s.CID = c.CourseID group by c.CourseID;

select CourseID, CourseName, avg(CourseScore) as 'Average Course Score' from assignment3.CourseInfo c inner join assignment3.StudentInfo s on s.CID = c.CourseID group by c.CourseID;

Drop Table assignment3.StudentInfo;
Drop Table assignment3.CourseInfo;


