package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.dto.EstudanteDTO;
import projeto.dto.FiltroTurmaDTO;
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
@Named("buscaTurmaWebBean")
public class BuscaTurmaWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Inject
    private EstudanteService estudanteService;

    private FiltroTurmaDTO filtro = new FiltroTurmaDTO();

    private List<TurmaDTO> turmasEncontradas = new ArrayList<>();

    private boolean buscaFeita;

    public List<EstudanteDTO> consultarEstudantePorNomeOuMatricula(Object query) {
        return estudanteService.consultarEstudantePorNomeOuMatricula(query.toString());
    }

    public void buscar() {
        try {
            turmasEncontradas = turmaService.buscar(filtro);
            buscaFeita = true;
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        }
    }

    public FiltroTurmaDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroTurmaDTO filtro) {
        this.filtro = filtro;
    }

    public List<TurmaDTO> getTurmasEncontradas() {
        return turmasEncontradas;
    }

    public void setTurmasEncontradas(List<TurmaDTO> turmasEncontradas) {
        this.turmasEncontradas = turmasEncontradas;
    }

    public boolean isBuscaFeita() {
        return buscaFeita;
    }

    public void setBuscaFeita(boolean buscaFeita) {
        this.buscaFeita = buscaFeita;
    }
}
