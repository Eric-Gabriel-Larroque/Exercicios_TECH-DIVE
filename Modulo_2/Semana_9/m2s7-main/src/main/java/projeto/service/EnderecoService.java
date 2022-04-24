package projeto.service;


import projeto.business.EnderecoBusiness;
import projeto.dto.EnderecoDTO;
import projeto.exception.BusinessException;
import projeto.repository.EnderecoRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnderecoService {

    @Inject
    private EnderecoBusiness enderecoBusiness;

    @Inject
    private EnderecoRepository enderecoRepository;


    public void cadastrar(EnderecoDTO enderecoDTO) throws BusinessException {
        enderecoBusiness.cadastrar(enderecoDTO);
    }
}
