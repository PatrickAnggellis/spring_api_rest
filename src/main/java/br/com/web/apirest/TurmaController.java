package br.com.web.apirest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/turmas")
@RestController
public class TurmaController {

    private List<Turma> turmas = new ArrayList<>();

    public TurmaController(){
        turmas.addAll(List.of(
            new Turma("IHC"),
            new Turma("WEB")
        ));
    }
    
    @GetMapping
    public Iterable<Turma> getTurmas(){
        return turmas;
    }

    @GetMapping("/{id}")
    public Optional<Turma> getTurmaById(PathVariable String id){
        for(Turma t: turmas){
            if(t.getId().equals(id)){
                return Optional.of(t);
            }
        }
        return Optional.empty();
        
    }

    @PostMapping("/turmas")
    public Turma postTurma(@RequestBody Turma turma){
        turmas.add(turma);
        return turma;
    }

    @PutMapping("/tumas/{id}")
    public Turma putTurma(@PathVariable String id, @RequestBody Turma turma){
        int turmaIndex = -1;

        for(Turma t : turmas){
            if(t.getId().equals(id)){
                turmaIndex = turmas.indexOf(t);
                turmas.set(turmaIndex, turma);
            }
        }
        return (turmaIndex == -1) ? postTurma(turma): turma;
    }
}
