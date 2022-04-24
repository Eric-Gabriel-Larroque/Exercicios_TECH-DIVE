package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;
import projeto.dto.FiltroTurmaDTO;
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
    private TurmaRepository turmaRepository;

    public void cadastrar(TurmaDTO turmaDTO) throws BusinessException {
        validarCadastrar(turmaDTO);
        cadastrarTurmaNoBanco(turmaDTO);
    }

    private void cadastrarTurmaNoBanco(TurmaDTO turmaDTO) throws BusinessException {
        Turma turma;
        if (turmaDTO.getIdTurma() != null) {
            turma = turmaRepository.find(Turma.class, turmaDTO.getIdTurma());
            if (turma == null) {
                throw new BusinessException("Turma não encontrada.");
            }
        } else {
            turma = new Turma();
        }

        turma.setNome(turmaDTO.getNome());
        turma.setDataInicio(turmaDTO.getDataInicio());
        turma.setDataTermino(turmaDTO.getDataTermino());

        for (Estudante estudante : turma.getEstudantes()) {
            if (turmaDTO.getEstudantes()
                    .stream()
                    .noneMatch(estudanteDTO -> estudanteDTO.getIdEstudante().equals(estudante.getIdEstudante()))) {
                estudante.setTurma(null);
            }
        }

        for (EstudanteDTO estudanteDTO : turmaDTO.getEstudantes()) {
            if (turma.getEstudantes()
                    .stream()
                    .noneMatch(estudante -> estudante.getIdEstudante().equals(estudanteDTO.getIdEstudante()))) {
                Estudante estudante = turmaRepository.find(Estudante.class, estudanteDTO.getIdEstudante());
                if (estudante == null) {
                    throw new BusinessException("Estudante não encontrado.");
                }
                estudante.setTurma(turma);
            }
        }

        if (turma.getIdTurma() != null) {
            turmaRepository.merge(turma);
        } else {
            turmaRepository.persist(turma);
            turmaDTO.setIdTurma(turma.getIdTurma());
        }
    }

    private void validarCadastrar(TurmaDTO turmaDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if (StringUtils.isBlank(turmaDTO.getNome())) {
            erros.add("O nome da turma é inválido.");
        }

        if (turmaDTO.getDataInicio() == null) {
            erros.add("A data de início da turma é inválida.");
        }

        if (turmaDTO.getDataTermino() == null) {
            erros.add("A data de término da turma é inválida.");
        }

        if (!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public TurmaDTO consultarDadosTurma(Long idTurma) throws BusinessException {
        Turma turma = turmaRepository.find(Turma.class, idTurma);
        if (turma == null) {
            throw new BusinessException("Turma não encontrada através do ID " + idTurma + ".");
        }

        return new TurmaDTO(turma);
    }

    public List<TurmaDTO> buscar(FiltroTurmaDTO filtro) throws BusinessException {
        validarCamposNulos(filtro);
        return turmaRepository.buscar(filtro);
    }

    private void validarCamposNulos(FiltroTurmaDTO filtro) throws BusinessException {
        if (filtro.getIdTurma() == null
                && StringUtils.isBlank(filtro.getNome())
                && filtro.getIdEstudante() == null
                && filtro.getDataInicio() == null
                && filtro.getDataTermino() == null) {
            throw new BusinessException("Insira ao menos um filtro para realizar a busca.");
        }
    }
}
