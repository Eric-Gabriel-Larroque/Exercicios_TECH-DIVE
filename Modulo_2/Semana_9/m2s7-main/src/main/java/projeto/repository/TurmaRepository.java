package projeto.repository;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;
import projeto.dto.FiltroTurmaDTO;
import projeto.dto.TurmaDTO;
import projeto.entity.Turma;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TurmaRepository extends GenericRepository {

    public List<TurmaDTO> consultarTurmas() {
        return entityManager.createNamedQuery(Turma.GET_TURMASDTO, TurmaDTO.class)
                .getResultList();
    }

    public List<EstudanteDTO> consultarEstudantesSemTurmas() {
        return entityManager.createQuery("SELECT new projeto.dto.EstudanteDTO(e) " +
                        "FROM Estudante e " +
                        "WHERE e.turma is null", EstudanteDTO.class)
                .getResultList();
    }

    public List<TurmaDTO> buscar(FiltroTurmaDTO filtro) {
        String hql = montarSqlBuscaTurma(filtro);
        Query query = entityManager.createQuery(hql, TurmaDTO.class);
        popularParametrosBuscaTurma(filtro, query);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    private void popularParametrosBuscaTurma(FiltroTurmaDTO filtro, Query query) {
        if (filtro.getIdTurma() != null) {
            query.setParameter("idTurma", filtro.getIdTurma());
        }

        if (filtro.getIdEstudante() != null) {
            query.setParameter("idEstudante", filtro.getIdEstudante());
        }

        if (!StringUtils.isBlank(filtro.getNome())) {
            String nome = "%" + filtro.getNome() + "%";
            nome = nome.toLowerCase();
            query.setParameter("nome", nome);
        }

        if (filtro.getDataInicio() != null) {
            query.setParameter("dataInicio", filtro.getDataInicio());
        }

        if (filtro.getDataTermino() != null) {
            query.setParameter("dataTermino", filtro.getDataTermino());
        }
    }

    private String montarSqlBuscaTurma(FiltroTurmaDTO filtro) {
        String hql = "SELECT new projeto.dto.TurmaDTO(t) " +
                "FROM Turma t ";
        String andOrWhere = "WHERE ";

        if (filtro.getIdEstudante() != null) {
            hql = hql.concat("JOIN t.estudantes e ");

            hql = hql.concat(andOrWhere).concat("e.idEstudante = :idEstudante ");
            andOrWhere = "AND ";
        }

        if (filtro.getIdTurma() != null) {
            hql = hql.concat(andOrWhere).concat("t.idTurma = :idTurma ");
            andOrWhere = "AND ";
        }

        if (!StringUtils.isBlank(filtro.getNome())) {
            hql = hql.concat(andOrWhere).concat("LOWER(t.nome) LIKE :nome ");
            andOrWhere = "AND ";
        }

        if (filtro.getDataInicio() != null) {
            hql = hql.concat(andOrWhere).concat("t.dataInicio >= :dataInicio ");
            andOrWhere = "AND ";
        }

        if (filtro.getDataTermino() != null) {
            hql = hql.concat(andOrWhere).concat("t.dataTermino <= :dataTermino ");
        }

        return hql.concat("ORDER BY t.idTurma");
    }
}
