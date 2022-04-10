package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.entity.Estudante;
import projeto.entity.Turma;
import projeto.exception.BusinessException;
import projeto.repository.TurmaRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TurmaBusiness {

    @Inject
    TurmaRepository turmaRepository;

    public void cadastrar(TurmaDTO turmaDTO)throws BusinessException {
        validarCadastro(turmaDTO);
        cadastrarTurmaNoBanco(turmaDTO);
    }

    private void cadastrarTurmaNoBanco(TurmaDTO turmaDTO) throws BusinessException {
        Turma turma;
        if(turmaDTO.getIdTurma() != null ) {
            turma = turmaRepository.find(Turma.class,turmaDTO.getIdTurma());
            if(turma == null) throw new BusinessException("Turma não encontrada");
        }else {
            turma = new Turma();
        }

        turma.setNome(turmaDTO.getNome());
        turma.setDataInicio(turmaDTO.getDataInicio());
        turma.setDataTermino(turmaDTO.getDataTermino());

        for(Estudante estudante: turma.getEstudantes()) {
            if(turma.getEstudantes()
                    .stream()
                    .noneMatch(e->e.getIdEstudante().equals(estudante.getIdEstudante()))) {
                estudante.setTurma(null);
            }
        }

        for(EstudanteDTO estudanteDTO: turmaDTO.getEstudantes()) {
            if(turma.getEstudantes()
                    .stream()
                    .noneMatch(e->e.getIdEstudante().equals(estudanteDTO.getIdEstudante()))){
                Estudante estudante = turmaRepository.find(Estudante.class,estudanteDTO.getIdEstudante());

                if(estudante == null) throw new BusinessException("Estudante não encontrado");

                estudante.setTurma(turma);
            }
        }

        if(turma.getIdTurma() != null) {
            turmaRepository.merge(turma);
        }else {
            turmaRepository.persist(turma);
            turmaDTO.setIdTurma(turma.getIdTurma());
        }
    }

    private void validarCadastro(TurmaDTO turmaDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if(StringUtils.isBlank(turmaDTO.getNome())) {
            erros.add("O nome da turma é inválido");
        }

        if(turmaDTO.getDataInicio() == null) {
            erros.add("A data de início da turma é inválida");
        }

        if(turmaDTO.getDataTermino() == null) {
            erros.add("A data de término da turma é inválida");
        }

        if(!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public TurmaDTO consultarDadosTurma(Long idTurma) throws BusinessException {
        Turma turma = turmaRepository.find(Turma.class, idTurma);
        if(turma == null ) throw new BusinessException("Turma não encontrada");

        return new TurmaDTO(turma);
    }

}
