package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {

    @Test
    void testCourseFullExceptionMessage() {
        CourseFullException ex = new CourseFullException("Course full");
        assertEquals("Course full", ex.getMessage());
    }

    @Test
    void testCourseFullExceptionWithCause() {
        Throwable cause = new RuntimeException("Root cause");
        CourseFullException ex = new CourseFullException("Course full", cause);
        assertEquals("Course full", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    void testCourseNotFoundExceptionMessage() {
        CourseNotFoundException ex = new CourseNotFoundException("Not found");
        assertEquals("Not found", ex.getMessage());
    }

    @Test
    void testCourseNotFoundExceptionWithCause() {
        Throwable cause = new RuntimeException("Missing");
        CourseNotFoundException ex = new CourseNotFoundException("Not found", cause);
        assertEquals("Not found", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    void testEnrollmentExceptionMessage() {
        EnrollmentException ex = new EnrollmentException("Enrollment error");
        assertEquals("Enrollment error", ex.getMessage());
    }

    @Test
    void testEnrollmentExceptionWithCause() {
        Throwable cause = new Exception("Invalid data");
        EnrollmentException ex = new EnrollmentException("Enrollment error", cause);
        assertEquals("Enrollment error", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    void testPrerequisiteNotMetExceptionMessage() {
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Prerequisite missing");
        assertEquals("Prerequisite missing", ex.getMessage());
    }

    @Test
    void testPrerequisiteNotMetExceptionWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid prerequisite");
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Prerequisite missing", cause);
        assertEquals("Prerequisite missing", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    void testStudentNotFoundExceptionMessage() {
        StudentNotFoundException ex = new StudentNotFoundException("Student not found");
        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    void testStudentNotFoundExceptionWithCause() {
        Throwable cause = new NullPointerException("No student");
        StudentNotFoundException ex = new StudentNotFoundException("Student not found", cause);
        assertEquals("Student not found", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
