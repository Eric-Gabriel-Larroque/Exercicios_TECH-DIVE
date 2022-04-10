package projeto.service;

import projeto.business.TurmaBusiness;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.repository.TurmaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TurmaService {

    @Inject
    private TurmaBusiness turmaBusiness;

    @Inject
    private TurmaRepository turmaRepository;

    public void cadastrar(TurmaDTO turmaDTO) throws BusinessException {
        turmaBusiness.cadastrar(turmaDTO);
    }

    public TurmaDTO consultarDadosTurma(Long idTurma) throws BusinessException {
        return turmaBusiness.consultarDadosTurma(idTurma);
    }

    public List<TurmaDTO> consultarTurmas() {
        return turmaRepository.consultarTurmas();
    }

    public List<EstudanteDTO> consultarEstudantesSemTurmas() {
        return turmaRepository.consultarEstudantesSemTurmas();
    }

}
