package com.siakad.service;

import com.siakad.model.CourseGrade;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    // ============================================================
    // 1. calculateGPA
    // ============================================================

    @Test
    void testCalculateGpaNormalCase() {
        // A=4.0(3 SKS) + B=3.0(4 SKS) + C=2.0(2 SKS) = (12 + 12 + 4)/9 = 3.111...
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("CS101", 3, 4.0),
                new CourseGrade("MA101", 4, 3.0),
                new CourseGrade("PH101", 2, 2.0)
        );

        double gpa = calculator.calculateGPA(grades);
        assertEquals(3.11, gpa, 0.01, "GPA seharusnya 3.11");
    }

    @Test
    void testCalculateGpaNullOrEmpty() {
        assertEquals(0.0, calculator.calculateGPA(null), "Null list harus 0.0");
        assertEquals(0.0, calculator.calculateGPA(Collections.emptyList()), "Empty list harus 0.0");
    }

    @Test
    void testCalculateGpaZeroCredits() {
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("CS101", 0, 4.0),
                new CourseGrade("MA101", 0, 3.0)
        );
        assertEquals(0.0, calculator.calculateGPA(grades), "Total SKS = 0 → GPA = 0");
    }

    @Test
    void testCalculateGpt() {
        // GradePoint > 4.0
        List<CourseGrade> invalidHigh = Collections.singletonList(new CourseGrade("CS", 3, 4.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(invalidHigh));

        // GradePoint < 0.0
        List<CourseGrade> invalidLow = Collections.singletonList(new CourseGrade("CS", 3, -0.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(invalidLow));
    }

    // ============================================================
    // 2. determineAcademicStatus
    // ============================================================

    @Test
    void testDetermineStatusSemester1To2() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.0, 1));
        assertEquals("PROBATION", calculator.determineAcademicStatus(1.9, 2));
    }

    @Test
    void testDetermineStatusSemester3To4() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.25, 3));
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.1, 4));
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.9, 3));
    }

    @Test
    void testDetermineStatusSemester5Plus() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 5));
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.3, 7));
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.9, 10));
    }

    @Test
    void testDetermineStatusInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(4.5, 1));
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(-0.1, 1));
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(3.0, 0));
    }

    // ============================================================
    // 3. calculateMaxCredits
    // ============================================================

    @Test
    void testCalculateMaxCreditsAllBranches() {
        assertEquals(24, calculator.calculateMaxCredits(3.5));
        assertEquals(21, calculator.calculateMaxCredits(2.8));
        assertEquals(18, calculator.calculateMaxCredits(2.3));
        assertEquals(15, calculator.calculateMaxCredits(1.9));
    }

    @Test
    void testCalculateMaxCreditsInvalidGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(4.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(-0.2));
    }
}
