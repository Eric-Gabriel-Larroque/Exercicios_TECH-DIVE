package projeto.bean;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ViewScoped
@Named("turmaCadastroWebBean")
public class TurmaCadastroWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Param(name = "idTurma")
    private Long idTurma;

    private TurmaDTO turmaDTO = new TurmaDTO();

    private EstudanteDTO estudanteSelecionado;

    private List<EstudanteDTO> estudantesSemTurmas = new ArrayList<>();

    public void inicializar() {
        if (idTurma != null) {
            try {
                turmaDTO = turmaService.consultarDadosTurma(idTurma);
                MessageUtils.limparMensagens();
            } catch (BusinessException e) {
                MessageUtils.returnGlobalMessageOnFail(e.getErros());
                Faces.redirect("/turma.xhtml");
            }
        }
        estudantesSemTurmas = turmaService.consultarEstudantesSemTurmas();
    }

    public void cadastrar() {
        try {
            turmaService.cadastrar(turmaDTO);
            if (idTurma == null) {
                MessageUtils.returnGlobalMessageOnSuccess("Salvo com sucesso!");
                Faces.redirect("/turma.xhtml?idTurma=" + turmaDTO.getIdTurma());
            } else {
                MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
            }
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail("Ocorreu um erro ao salvar a turma. Por favor, entre em contato com o suporte.");
        }
    }

    public void adicionarEstudante() {
        turmaDTO.getEstudantes().add(estudanteSelecionado);
        turmaDTO.getEstudantes().sort(Comparator.comparing(EstudanteDTO::getNome));
        getEstudantesSemTurmas().remove(estudanteSelecionado);
        estudanteSelecionado = null;
    }

    public boolean desabilitarAdicionarEstudante() {
        return (estudanteSelecionado == null);
    }

    public void removerEstudante(EstudanteDTO estudanteDTO) {
        turmaDTO.getEstudantes().remove(estudanteDTO);
    }

    public TurmaDTO getTurmaDTO() {
        return turmaDTO;
    }

    public void setTurmaDTO(TurmaDTO turmaDTO) {
        this.turmaDTO = turmaDTO;
    }

    public EstudanteDTO getEstudanteSelecionado() {
        return estudanteSelecionado;
    }

    public void setEstudanteSelecionado(EstudanteDTO estudanteSelecionado) {
        this.estudanteSelecionado = estudanteSelecionado;
    }

    public List<EstudanteDTO> getEstudantesSemTurmas() {
        return estudantesSemTurmas;
    }

    public void setEstudantesSemTurmas(List<EstudanteDTO> estudantesSemTurmas) {
        this.estudantesSemTurmas = estudantesSemTurmas;
    }
}
