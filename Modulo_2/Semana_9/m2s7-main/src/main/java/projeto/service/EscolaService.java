package projeto.service;

import projeto.business.EscolaBusiness;
import projeto.dto.EscolaDTO;
import projeto.exception.BusinessException;
import projeto.repository.EscolaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EscolaService {

    @Inject
    private EscolaBusiness escolaBusiness;

    @Inject
    private EscolaRepository escolaRepository;

    public EscolaDTO consultarDadosEscola(Long idEscola) throws BusinessException {
        return escolaBusiness.consultarDadosEscola(idEscola);
    }

    public void cadastrar(EscolaDTO escolaDTO) throws BusinessException{
        escolaBusiness.cadastrar(escolaDTO);
    }
}
