package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
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
@Named("buscaEstudanteWebBean")
public class BuscaEstudanteWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Inject
    private EstudanteService estudanteService;

    private EstudanteDTO estudanteDTO = new EstudanteDTO();

    private List<EstudanteDTO> estudantesEncontrados = new ArrayList<>();

    private boolean buscaFeita;


    public void buscar() {
        try {
            estudantesEncontrados = estudanteService.buscar(estudanteDTO);
            buscaFeita = true;
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        }
    }

    public List<TurmaDTO> consultarTurmaPorNomeOuCodigo(Object query) {
        return turmaService.consultarTurmaPorNomeOuCodigo(query.toString());
    }

    public EstudanteDTO getEstudanteDTO() {
        return estudanteDTO;
    }

    public void setEstudanteDTO(EstudanteDTO estudanteDTO) {
        this.estudanteDTO = estudanteDTO;
    }

    public List<EstudanteDTO> getEstudantesEncontrados() {
        return estudantesEncontrados;
    }

    public void setEstudantesEncontrados(List<EstudanteDTO> estudantesEncontrados) {
        this.estudantesEncontrados = estudantesEncontrados;
    }

    public boolean isBuscaFeita() {
        return buscaFeita;
    }

    public void setBuscaFeita(boolean buscaFeita) {
        this.buscaFeita = buscaFeita;
    }
}
