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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/turmas")
@Api(value = "Turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmarepository;

    @PostMapping
    @ApiOperation(value = "Cadastra uma nova turma")
    public ResponseEntity<Turma> postTurma(@RequestBody Turma turma){
        try {
            return ResponseEntity.ok(turmarepository.save(turma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Recupera todas as turmas cadastradas")
    public Iterable<Turma> getTurmas(){
        return turmarepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Recupera uma turma por seu id")
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
    @ApiOperation(value = "Deleta uma turma com base em seu id")
    public ResponseEntity<Turma> deleteTurma (@PathVariable Long id) {
        try {
            turmarepository.delete(turmarepository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }    

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita uma turma")
    public ResponseEntity<Turma> putTurma(@PathVariable String id, @RequestBody Turma turma){
        try {
            return ResponseEntity.ok(turmarepository.save(turma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }  
    }  
}
