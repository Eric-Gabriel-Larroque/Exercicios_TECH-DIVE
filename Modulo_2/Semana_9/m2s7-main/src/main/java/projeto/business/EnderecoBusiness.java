package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EnderecoDTO;
import projeto.entity.Endereco;
import projeto.exception.BusinessException;
import projeto.repository.EnderecoRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EnderecoBusiness {

    @Inject
    private EnderecoRepository enderecoRepository;

    public void cadastrar(EnderecoDTO enderecoDTO) throws BusinessException {
        validarCadastrar(enderecoDTO);
        cadastrarEnderecoNoBanco(enderecoDTO);

    }

    public void validarCadastrar(EnderecoDTO enderecoDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if(StringUtils.isBlank(enderecoDTO.getNumero())) {
            erros.add("o valor do número é inválido");
        }
        if(StringUtils.isBlank(enderecoDTO.getRua())) {
            erros.add("O valor da rua é inválido");
        }
        if(StringUtils.isBlank(enderecoDTO.getBairro())) {
            erros.add("O valor de bairro é inválido");
        }
        if(StringUtils.isBlank(enderecoDTO.getCidade())) {
            erros.add("O valor de cidade é inválido");
        }
        if(StringUtils.isBlank(enderecoDTO.getEstado())) {
            erros.add("O valor de estado e inválido");
        }
        if(StringUtils.isBlank(enderecoDTO.getPais())) {
            erros.add("O valor de pais e inválido");
        }

        if(!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }


    public void cadastrarEnderecoNoBanco(EnderecoDTO enderecoDTO) throws BusinessException {
        Endereco endereco;

        if(enderecoDTO.getIdEndereco() != null) {
            endereco = enderecoRepository.find(Endereco.class,enderecoDTO.getIdEndereco());

            if(endereco.getIdEndereco() == null) {
                throw new BusinessException("Endereço não encontrado.");
            }
        } else {
            endereco = new Endereco();
        }

        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setPais(enderecoDTO.getPais());

        if(endereco.getIdEndereco() != null) {
            enderecoRepository.merge(endereco);
        } else {
            enderecoRepository.persist(endereco);
            enderecoDTO.setIdEndereco(endereco.getIdEndereco());
        }

    }
}
