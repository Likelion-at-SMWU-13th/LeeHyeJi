package com.likelion.seminar.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class StudentDTO {
    private Long studentID; // 학번
    private String name; // 이름
    private LocalDate dateOfBirth; // 생년월일
}
