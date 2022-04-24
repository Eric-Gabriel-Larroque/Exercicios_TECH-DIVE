package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.entity.Estudante;
import projeto.entity.Turma;
import projeto.exception.BusinessException;
import projeto.repository.EstudanteRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EstudanteBusiness {

    @Inject
    private EstudanteRepository estudanteRepository;

    public void cadastrar(EstudanteDTO estudanteDTO) throws BusinessException {
        validarCadastrar(estudanteDTO);
        cadastrarEstudanteNoBanco(estudanteDTO);
    }

    private void cadastrarEstudanteNoBanco(EstudanteDTO estudanteDTO) throws BusinessException {
        Estudante estudante;
        if (estudanteDTO.getIdEstudante() != null) {
            estudante = estudanteRepository.find(Estudante.class, estudanteDTO.getIdEstudante());
            if (estudante == null) {
                throw new BusinessException("Estudante não encontrado.");
            }
        } else {
            estudante = new Estudante();
        }

        estudante.setNome(estudanteDTO.getNome());
        estudante.setEmail(estudanteDTO.getEmail());
        estudante.setDataNascimento(estudanteDTO.getDataNascimento());

        Turma turma = estudanteRepository.find(Turma.class, estudanteDTO.getIdTurma());

        if (turma == null) {
            throw new BusinessException("Turma não encontrada");
        }

        estudante.setTurma(turma);

        if (estudante.getIdEstudante() != null) {
            estudanteRepository.merge(estudante);
        } else {
            estudanteRepository.persist(estudante);
            estudanteDTO.setIdEstudante(estudante.getIdEstudante());
        }
    }

    private void validarCadastrar(EstudanteDTO estudanteDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if (StringUtils.isBlank(estudanteDTO.getNome())) {
            erros.add("O nome do estudante é inválido.");
        }

        if (estudanteDTO.getDataNascimento() == null) {
            erros.add("A data de nascimento é inválida.");
        }

        if (estudanteDTO.getIdTurma() == null) {
            erros.add("A turma é inválida blablabla.");
        }

        if (StringUtils.isBlank(estudanteDTO.getEmail())) {
            erros.add("O e-mail do estudante é inválido.");
        }

        if (!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public EstudanteDTO consultarDadosEstudante(Long idEstudante) throws BusinessException {
        Estudante estudante = estudanteRepository.find(Estudante.class, idEstudante);
        if (estudante == null) {
            throw new BusinessException("Estudante não encontrado através do ID " + idEstudante + ".");
        }

        return new EstudanteDTO(estudante);
    }
}
