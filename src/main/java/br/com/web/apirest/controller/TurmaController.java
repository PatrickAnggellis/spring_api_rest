package br.com.web.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.apirest.model.Turma;
import br.com.web.apirest.repository.TurmaRepository;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmarepository;

    @PostMapping
    public ResponseEntity<?> postTurma(@RequestBody Turma turma){
        try {
            return ResponseEntity.ok(turmarepository.save(turma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public Iterable<Turma> getTurmas(){
        return turmarepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getTurmaById(@PathVariable Long id) {
       Optional<Turma> turma;
       try {
           turma = turmarepository.findById(id);
           return new ResponseEntity<Optional<Turma>>(turma, HttpStatus.OK);
       } catch (Exception e) {
            return new ResponseEntity<Optional<Turma>>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurma (@PathVariable Long id) {
        try {
            turmarepository.delete(turmarepository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }    

    @PutMapping("/{id}")
    public ResponseEntity<?> putTurma(@PathVariable String id, @RequestBody Turma turma){
        try {
            return ResponseEntity.ok(turmarepository.save(turma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }  
    }  
}
