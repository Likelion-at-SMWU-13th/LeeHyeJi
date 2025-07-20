package com.likelion.seminar.student.controller;

import com.likelion.seminar.student.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    // 실제 데이터를 저장할 메모리 기반 리스트 (DB 없이 사용)
    private final List<StudentDTO> studentDTOList;

    // 새로운 학생 등록
    @PostMapping()
    public String createStudent(@RequestBody StudentDTO studentDTO){
        // 이미 같은 studentID를 가진 학생이 있는지 확인
        Optional<StudentDTO> existing = studentDTOList.stream()
                .filter(s -> s.getStudentID().equals(studentDTO.getStudentID()))
                .findFirst();

        // 중복 studentID가 있으면 등록 불가
        if(existing.isPresent()){
            return "이미 존재하는 studentID 입니다.";
        }

        // 중복이 아니라면 리스트에 추가
        studentDTOList.add(studentDTO);
        return "학생이 추가되었습니다.";
    }

}
