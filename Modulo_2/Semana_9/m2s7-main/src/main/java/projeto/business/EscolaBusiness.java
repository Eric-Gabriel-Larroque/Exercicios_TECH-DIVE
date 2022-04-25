package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EscolaDTO;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.entity.Endereco;
import projeto.entity.Escola;
import projeto.entity.Estudante;
import projeto.entity.Turma;
import projeto.exception.BusinessException;
import projeto.repository.EscolaRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaBusiness {

    @Inject
    private EscolaRepository escolaRepository;

    public EscolaDTO consultarDadosEscola(Long idEscola) throws BusinessException {
        Escola escola = escolaRepository.find(Escola.class, idEscola);
        if (escola == null) {
            throw new BusinessException("Escola não encontrada através do ID " + idEscola + ".");
        }

        return new EscolaDTO(escola);
    }

    public void cadastrar(EscolaDTO escolaDTO) throws BusinessException {
        validarCadastrar(escolaDTO);
        cadastrarEscolaNoBanco(escolaDTO);
    }

    public void validarCadastrar(EscolaDTO escolaDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if(escolaDTO.getTurmas() == null) {
            erros.add("Necessário adicionar pelo menos uma turma.");
        }

        if(escolaDTO.getIdEndereco() == null) {
            erros.add("Valor de endereço é inválido.");
        }

        if(escolaDTO.getCriacao() == null) {
            erros.add("O valor da data de criação da escola é inválido.");
        }

        if(StringUtils.isBlank(escolaDTO.getNome())) {
            erros.add("O valor do nome é inválido.");
        }

        if(!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public void cadastrarEscolaNoBanco(EscolaDTO escolaDTO) throws BusinessException{
        Escola escola;

        if(escolaDTO.getIdEscola() != null) {
            escola = escolaRepository.find(Escola.class,escolaDTO.getIdEscola());

            if(escola.getIdEscola() == null) {
                throw new BusinessException("Escola não encontrada.");
            }
        } else {
            escola = new Escola();
        }

        escola.setCriacao(escolaDTO.getCriacao());
        escola.setNome(escolaDTO.getNome());

        Endereco endereco = escolaRepository.find(Endereco.class,escolaDTO.getIdEndereco());

        if(endereco == null) {
            throw new BusinessException("Endereço não encontrado.");
        }

        escola.setEndereco(endereco);

        for(TurmaDTO turmaDTO: escolaDTO.getTurmas()) {
            if(escolaDTO.getTurmas().stream().noneMatch(t->t.getIdTurma().equals(turmaDTO.getIdTurma()))) {
                Turma turma = escolaRepository.find(Turma.class,turmaDTO.getIdTurma());

                if(turma == null) {
                    throw new BusinessException("Turma não encontrada");
                }
                turma.setEscola(escola);
            }
        }

        if (escola.getIdEscola() != null) {
            escolaRepository.merge(escola);
        } else {
            escolaRepository.persist(escola);
            escolaDTO.setIdEscola(escola.getIdEscola());
        }
    }
}
