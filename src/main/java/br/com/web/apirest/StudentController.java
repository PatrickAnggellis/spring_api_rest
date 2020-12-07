package br.com.web.apirest;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController(){
        students.addAll(List.of(
            new Student("Patrick"),
            new Student("Ivandro"),
            new Student("Luiz")
        ));
    }

   @GetMapping
    public Iterable<Student> getStudents(){
        return students;
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable String id){
        try {
            for(Student t: students){
                if(t.getId().equals(id)){
                    return Optional.of(t);
                }
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return null;
    }
    @PostMapping
    public Student postStudent(@RequestBody Student student){
        try {
            students.add(student);
            System.out.println("Estudante adicionado com sucesso!!");  
        } catch (Exception e) {
            System.out.println("Falha ao adicionar estudante!!");
        }
        return student;
    }

    @PutMapping("/{id}")
    public Student putStudent(@PathVariable String id, @RequestBody Student student){
        int studentIndex = -1;

        try {
            for(Student t : students){
                if(t.getId().equals(id)){
                    studentIndex = students.indexOf(t);
                    students.set(studentIndex, student);
                }
                return (studentIndex == -1) ? postStudent(student) : student;
                        
            }
        } catch (Exception e) {
            System.out.println("Erro" + e);
        }
        return student;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent (@PathVariable String id) {
        students.removeIf(t -> t.getId().equals(id));
    }

}