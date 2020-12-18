package br.com.web.apirest.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.apirest.model.Professor;
import br.com.web.apirest.model.Projeto;
import br.com.web.apirest.repository.ProfessorRepository;


@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getProfessores(){
        return professorRepository.findAll();
    }

    public Professor postProfessor(Professor professor){
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long idProfessor){
        professorRepository.deleteById(idProfessor);
    }

    public Professor putProfessor(Long idProfessor, Professor professor) throws Exception {
        Optional<Professor> professorSalvo = professorRepository.findById(idProfessor);

            if(!professorSalvo.isPresent()){
                throw new Exception("O professor não existe no banco!");
            }
            BeanUtils.copyProperties(professor, professorSalvo.get());
            return professorRepository.save(professorSalvo.get());
    }

    public Professor getProfessorById(Long idProfessor) throws Exception {
        Optional<Professor> professorSalvo = professorRepository.findById(idProfessor);
        if(!professorSalvo.isPresent()){
            throw new Exception("O professor não existe no banco!");
        }
        return professorSalvo.get();
        
    }

    // public Professor createProjeto(Long idProfessor, Professor professor){
        
    //     Optional<Professor> professorSalvo = professorRepository.findById(idProfessor);
    //     if(!professorSalvo.)
    //     return null;
    
    // }
        
        
        
    
}
