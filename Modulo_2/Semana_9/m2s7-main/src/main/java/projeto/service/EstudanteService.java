package projeto.service;

import projeto.business.EstudanteBusiness;
import projeto.dto.EstudanteDTO;
import projeto.exception.BusinessException;
import projeto.repository.EstudanteRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EstudanteService {

    @Inject
    private EstudanteBusiness estudanteBusiness;

    @Inject
    private EstudanteRepository estudanteRepository;

    public void cadastrar(EstudanteDTO estudanteDTO) throws BusinessException {
        estudanteBusiness.cadastrar(estudanteDTO);
    }

    public EstudanteDTO consultarDadosEstudante(Long idEstudante) throws BusinessException {
        return estudanteBusiness.consultarDadosEstudante(idEstudante);
    }

    public List<EstudanteDTO> consultarEstudantePorNomeOuMatricula(String query) {
        return estudanteRepository.consultarEstudantePorNomeOuMatricula(query);
    }
}
