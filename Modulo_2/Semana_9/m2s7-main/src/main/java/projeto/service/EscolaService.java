package projeto.service;

import projeto.business.EscolaBusiness;
import projeto.repository.EscolaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EscolaService {

    @Inject
    private EscolaBusiness escolaBusiness;

    @Inject
    private EscolaRepository escolaRepository;

}
