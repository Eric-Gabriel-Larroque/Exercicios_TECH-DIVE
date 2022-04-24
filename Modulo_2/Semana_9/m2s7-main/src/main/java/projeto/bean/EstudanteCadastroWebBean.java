package projeto.bean;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.service.EstudanteService;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("estudanteCadastroWebBean")
public class EstudanteCadastroWebBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private EstudanteDTO estudanteDTO = new EstudanteDTO();

    private List<TurmaDTO> turmas = new ArrayList<>();

    @Param(name = "idEstudante")
    private Long idEstudante;

    @Inject
    private EstudanteService estudanteService;

    @Inject
    private TurmaService turmaService;

    public void inicializar() {
        if (idEstudante != null) {
            try {
                estudanteDTO = estudanteService.consultarDadosEstudante(idEstudante);
                MessageUtils.limparMensagens();
            } catch (BusinessException e) {
                MessageUtils.returnGlobalMessageOnFail(e.getErros());
                Faces.redirect("/estudante.xhtml");
            }
        }
        turmas = turmaService.consultarTurmas();
    }

    public void cadastrar() {
        try {
            estudanteService.cadastrar(estudanteDTO);
            if (idEstudante == null) {
                MessageUtils.returnGlobalMessageOnSuccess("Salvo com sucesso!");
                Faces.redirect("/estudante.xhtml?idEstudante=" + estudanteDTO.getIdEstudante());
            } else {
                MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
            }
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail("Ocorreu um erro ao salvar o estudante. Por favor, entre em contato com o suporte.");
        }
    }

    public EstudanteDTO getEstudanteDTO() {
        return estudanteDTO;
    }

    public void setEstudanteDTO(EstudanteDTO estudanteDTO) {
        this.estudanteDTO = estudanteDTO;
    }

    public List<TurmaDTO> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaDTO> turmas) {
        this.turmas = turmas;
    }
}
