package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, Student student){

        Optional<Student> studentOptional = studentRepository
                .findStudentById(studentId);
        if(studentOptional.isPresent()){
            Student student1 = studentOptional.get();
            student1.setName(student.getName());
            student1.setEmail(student.getEmail());
            student1.setDob(student.getDob());
            studentRepository.save(student1);
        }else{
            throw new IllegalStateException("No such ID");
        }
    }
}
