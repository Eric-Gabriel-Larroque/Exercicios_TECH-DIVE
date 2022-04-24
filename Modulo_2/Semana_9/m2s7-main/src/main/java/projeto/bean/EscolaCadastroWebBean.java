package projeto.bean;


import org.omnifaces.cdi.ViewScoped;
import projeto.dto.EnderecoDTO;
import projeto.dto.EscolaDTO;
import projeto.dto.TurmaDTO;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("escolaCadastroWebBean")
public class EscolaCadastroWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EscolaDTO escolaDTO = new EscolaDTO();

    private EnderecoDTO enderecoDTO = new EnderecoDTO();

    private List<TurmaDTO> turmasDisponiveis = new ArrayList<>();

    public void inicializar() {

    }

    public void cadastrar() {

    }


    public EscolaDTO getEscolaDTO() {
        return escolaDTO;
    }

    public void setEscolaDTO(EscolaDTO escolaDTO) {
        this.escolaDTO = escolaDTO;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }

    public List<TurmaDTO> getTurmas() {
        return turmasDisponiveis;
    }

    public void setTurmas(List<TurmaDTO> turmas) {
        this.turmasDisponiveis = turmas;
    }


}
