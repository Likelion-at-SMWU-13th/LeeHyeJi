package com.likelion.seminar.student.controller;

import com.likelion.seminar.student.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    // 실제 데이터를 저장할 메모리 기반 리스트 (DB 없이 사용)
    private final List<StudentDTO> studentDTOList;

    // 새로운 학생 등록
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO studentDTO) {
        boolean exists = studentDTOList.stream()
                .anyMatch(s -> s.getStudentID().equals(studentDTO.getStudentID()));

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 존재하는 studentID 입니다.");
        }

        studentDTOList.add(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("학생이 추가되었습니다.");
    }

    // 전체 학생 리스트 조회
    @GetMapping()
    public List<StudentDTO> getAllStudents(){
        System.out.println("전체 학생 리스트 조회");
        return this.studentDTOList;
    }

    // 단일 학생 조회
    @GetMapping("/{studentID}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentID) {
        Optional<StudentDTO> student = studentDTOList.stream()
                .filter(s -> s.getStudentID().equals(studentID))
                .findFirst();

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get()); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당 studentID를 가진 학생을 찾을 수 없습니다."); // 404 Not Found
        }
    }

    // 학생 정보 수정
    @PutMapping("/{studentID}")
    public ResponseEntity<String> updateStudent(@PathVariable Long studentID, @RequestBody StudentDTO updatedStudent) {
        for (int i = 0; i < studentDTOList.size(); i++) {
            StudentDTO current = studentDTOList.get(i);

            if (current.getStudentID().equals(studentID)) {
                // 변경할 필드만 null 체크해서 덮어쓰기
                String name = updatedStudent.getName() != null ? updatedStudent.getName() : current.getName();
                LocalDate dateOfBirth = updatedStudent.getDateOfBirth() != null ? updatedStudent.getDateOfBirth() : current.getDateOfBirth();

                StudentDTO newStudent = new StudentDTO(studentID, name, dateOfBirth);
                studentDTOList.set(i, newStudent);

                return ResponseEntity.ok("학생 정보가 업데이트되었습니다.");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("해당 studentID를 가진 학생을 찾을 수 없습니다.");
    }

    // 학생 삭제
    @DeleteMapping("/{studentID}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentID) {
        Optional<StudentDTO> target = studentDTOList.stream()
                .filter(s -> s.getStudentID().equals(studentID))
                .findFirst();

        if (target.isPresent()) {
            studentDTOList.remove(target.get());
            return ResponseEntity.ok("학생이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당 studentID를 가진 학생을 찾을 수 없습니다.");
        }
    }

}
