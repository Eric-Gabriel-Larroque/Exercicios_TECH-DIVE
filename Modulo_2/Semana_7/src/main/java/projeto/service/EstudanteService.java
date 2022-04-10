package projeto.service;

import projeto.business.EstudanteBusiness;
import projeto.dto.EstudanteDTO;
import projeto.exception.BusinessException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EstudanteService {

    @Inject
    private EstudanteBusiness estudanteBusiness;

    public void cadastrar(EstudanteDTO estudanteDTO) throws BusinessException {
        estudanteBusiness.cadastrar(estudanteDTO);
    }

    public EstudanteDTO consultarDadosEstudante(Long idEstudante) throws BusinessException {
       return estudanteBusiness.consultarDadosEstudante(idEstudante);
    }
}
