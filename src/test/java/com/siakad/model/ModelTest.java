package com.siakad.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ModelFullCoverageTest {

    // ================== Course Tests ==================
    @Test
    void testCourseDefaultConstructor() {
        Course course = new Course();
        assertNull(course.getCourseCode());
        assertNull(course.getCourseName());
        assertEquals(0, course.getCredits());
        assertEquals(0, course.getCapacity());
        assertEquals(0, course.getEnrolledCount());
        assertNull(course.getLecturer());
        assertNotNull(course.getPrerequisites());
        assertTrue(course.getPrerequisites().isEmpty());
        assertNotNull(course.toString());
        course.hashCode();
    }

    @Test
    void testCourseParameterizedConstructorAndSetters() {
        Course course = new Course("C001", "Matematika", 3, 50, 0, "Dr. Smith");
        assertEquals("C001", course.getCourseCode());
        assertEquals("Matematika", course.getCourseName());
        assertEquals(3, course.getCredits());
        assertEquals(50, course.getCapacity());
        assertEquals(0, course.getEnrolledCount());
        assertEquals("Dr. Smith", course.getLecturer());
        assertNotNull(course.toString());
        course.hashCode();

        course.setCourseCode("C002");
        course.setCourseName("Fisika");
        course.setCredits(4);
        course.setCapacity(40);
        course.setEnrolledCount(10);
        course.setLecturer("Dr. John");
        course.setPrerequisites(Arrays.asList("C101", "C102"));

        assertEquals("C002", course.getCourseCode());
        assertEquals("Fisika", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertEquals(40, course.getCapacity());
        assertEquals(10, course.getEnrolledCount());
        assertEquals("Dr. John", course.getLecturer());
        assertEquals(2, course.getPrerequisites().size());
    }

    @Test
    void testCourseAddPrerequisiteBranches() {
        Course course = new Course();

        // branch 1: prerequisites == null
        course.setPrerequisites(null);
        course.addPrerequisite("C101");
        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("C101"));

        // branch 2: prerequisites != null
        course.addPrerequisite("C102");
        course.addPrerequisite("C103");
        assertTrue(course.getPrerequisites().containsAll(Arrays.asList("C101", "C102", "C103")));
        assertNotNull(course.toString());
        course.hashCode();
    }

    // ================== CourseGrade Tests ==================
    @Test
    void testCourseGradeDefaultConstructorAndSetters() {
        CourseGrade grade = new CourseGrade();
        assertNull(grade.getCourseCode());
        assertEquals(0, grade.getCredits());
        assertEquals(0.0, grade.getGradePoint());

        grade.setCourseCode("C001");
        grade.setCredits(3);
        grade.setGradePoint(4.0);

        assertEquals("C001", grade.getCourseCode());
        assertEquals(3, grade.getCredits());
        assertEquals(4.0, grade.getGradePoint());
        assertNotNull(grade.toString());
        grade.hashCode();
    }

    @Test
    void testCourseGradeParameterizedConstructor() {
        CourseGrade grade = new CourseGrade("C002", 4, 3.5);
        assertEquals("C002", grade.getCourseCode());
        assertEquals(4, grade.getCredits());
        assertEquals(3.5, grade.getGradePoint());
        assertNotNull(grade.toString());
        grade.hashCode();
    }

    // ================== Enrollment Tests ==================
    @Test
    void testEnrollmentDefaultConstructorAndSetters() {
        Enrollment e = new Enrollment();
        assertNull(e.getEnrollmentId());
        assertNull(e.getStudentId());
        assertNull(e.getCourseCode());
        assertNull(e.getEnrollmentDate());
        assertNull(e.getStatus());

        LocalDateTime now = LocalDateTime.now();
        e.setEnrollmentId("E001");
        e.setStudentId("S001");
        e.setCourseCode("C001");
        e.setEnrollmentDate(now);
        e.setStatus("APPROVED");

        assertEquals("E001", e.getEnrollmentId());
        assertEquals("S001", e.getStudentId());
        assertEquals("C001", e.getCourseCode());
        assertEquals(now, e.getEnrollmentDate());
        assertEquals("APPROVED", e.getStatus());
        assertNotNull(e.toString());
        e.hashCode();
    }

    @Test
    void testEnrollmentParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Enrollment e = new Enrollment("E002", "S002", "C002", now, "PENDING");

        assertEquals("E002", e.getEnrollmentId());
        assertEquals("S002", e.getStudentId());
        assertEquals("C002", e.getCourseCode());
        assertEquals(now, e.getEnrollmentDate());
        assertEquals("PENDING", e.getStatus());
        assertNotNull(e.toString());
        e.hashCode();
    }

    // ================== Student Tests ==================
    @Test
    void testStudentDefaultConstructorAndSetters() {
        Student s = new Student();
        assertNull(s.getStudentId());
        assertNull(s.getName());
        assertNull(s.getEmail());
        assertNull(s.getMajor());
        assertEquals(0, s.getSemester());
        assertEquals(0.0, s.getGpa());
        assertNull(s.getAcademicStatus());

        s.setStudentId("S001");
        s.setName("Zidan");
        s.setEmail("zidan@example.com");
        s.setMajor("Rekayasa Keamanan Siber");
        s.setSemester(5);
        s.setGpa(4.0);
        s.setAcademicStatus("ACTIVE");

        assertEquals("S001", s.getStudentId());
        assertEquals("Zidan", s.getName());
        assertEquals("zidan@example.com", s.getEmail());
        assertEquals("Rekayasa Keamanan Siber", s.getMajor());
        assertEquals(5, s.getSemester());
        assertEquals(4.0, s.getGpa());
        assertEquals("ACTIVE", s.getAcademicStatus());
        assertNotNull(s.toString());
        s.hashCode();
    }

    @Test
    void testStudentParameterizedConstructor() {
        Student s = new Student("S002", "Zidan Naufal", "zidan@yb.com",
                "Keamanan Siber", 6, 3.8, "ACTIVE");

        assertEquals("S002", s.getStudentId());
        assertEquals("Zidan Naufal", s.getName());
        assertEquals("zidan@yb.com", s.getEmail());
        assertEquals("Keamanan Siber", s.getMajor());
        assertEquals(6, s.getSemester());
        assertEquals(3.8, s.getGpa());
        assertEquals("ACTIVE", s.getAcademicStatus());
        assertNotNull(s.toString());
        s.hashCode();
    }
}
