package br.com.web.apirest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping("/students")
    public Iterable<Student> getStudent(){
        return students;
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable Integer id){

        int studentIndex = -1;
        for (Student st: students){
            if (st.getId().equals(id)){
                return Optional.of(st);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/students")
    Student postStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}