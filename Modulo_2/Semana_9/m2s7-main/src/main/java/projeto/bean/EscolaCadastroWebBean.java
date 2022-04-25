package projeto.bean;


import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import projeto.dto.EnderecoDTO;
import projeto.dto.EscolaDTO;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.service.EnderecoService;
import projeto.service.EscolaService;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("escolaCadastroWebBean")
public class EscolaCadastroWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Inject
    private EscolaService escolaService;

    @Inject
    private EnderecoService enderecoService;

    @Param(name = "idEscola")
    private Long idEscola;

    private EscolaDTO escolaDTO = new EscolaDTO();

    private EnderecoDTO enderecoDTO = new EnderecoDTO();

    private List<TurmaDTO> turmasDisponiveis = new ArrayList<>();

    public void inicializar() {
        if (idEscola != null) {
            try {
                escolaDTO = escolaService.consultarDadosEscola(idEscola);
                MessageUtils.limparMensagens();
            } catch (BusinessException e) {
                MessageUtils.returnGlobalMessageOnFail(e.getErros());
                Faces.redirect("/escola.xhtml");
            }
        }
        turmasDisponiveis = turmaService.consultarTurmasSemEscola();
    }

    public void cadastrar() {
        try {
            enderecoService.cadastrar(enderecoDTO);
            escolaDTO.setIdEndereco(enderecoDTO.getIdEndereco());
            escolaService.cadastrar(escolaDTO);
            if (idEscola == null) {
                MessageUtils.returnGlobalMessageOnSuccess("Salvo com sucesso!");
                Faces.redirect("/estudante.xhtml?idEstudante=" + escolaDTO.getIdEscola());
            } else {
                MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
            }
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail("Ocorreu um erro ao salvar o estudante. Por favor, entre em contato com o suporte.");
        }
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

    public List<TurmaDTO> getTurmasDisponiveis() {
        return turmasDisponiveis;
    }

    public void setTurmasDisponiveis(List<TurmaDTO> turmasDisponiveis) {
        this.turmasDisponiveis = turmasDisponiveis;
    }
}
