package projeto.repository;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstudanteRepository extends GenericRepository {

    public List<EstudanteDTO> consultarEstudantePorNomeOuMatricula(String query) {
        query = "%" + query + "%";
        query = query.toLowerCase();

        try {
            return entityManager.createQuery("SELECT new projeto.dto.EstudanteDTO(e.idEstudante, e.nome) " +
                            "FROM Estudante e " +
                            "WHERE CAST(e.idEstudante AS string) = :query " +
                            "OR LOWER(e.nome) LIKE :query", EstudanteDTO.class)
                    .setParameter("query", query)
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public List<EstudanteDTO> buscar(EstudanteDTO estudanteDTO) {
        String hql = montarHqlBuscaEstudante(estudanteDTO);
        Query query = entityManager.createQuery(hql,EstudanteDTO.class);
        popularParametrosBuscaEstudante(estudanteDTO, query);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    private String montarHqlBuscaEstudante(EstudanteDTO estudanteDTO) {
        String hql = "SELECT new projeto.dto.EstudanteDTO(e) " +
                "FROM Estudante e ";
        String andOrWhere = "WHERE ";

        if(estudanteDTO.getIdTurma() != null) {
            hql = hql.concat("JOIN e.turma t ");

            hql = hql.concat(andOrWhere).concat("t.idTurma = :idTurma ");
            andOrWhere = "AND ";
        }


        if(estudanteDTO.getIdEstudante() != null) {
            hql = hql.concat(andOrWhere).concat("e.idEstudante= :idEstudante ");
            andOrWhere = "AND ";
        }

        if(!StringUtils.isBlank(estudanteDTO.getNome())) {
            hql = hql.concat(andOrWhere).concat("LOWER(e.nome) LIKE :nome ");
            andOrWhere = "AND ";
        }

        if(!StringUtils.isBlank(estudanteDTO.getEmail())) {
            hql = hql.concat(andOrWhere).concat("LOWER(e.email)= :email ");
            andOrWhere = "AND ";
        }

        if(estudanteDTO.getDataNascimento()!=null) {
            hql = hql.concat(andOrWhere).concat("e.dataNascimento= :dataNascimento ");
        }

        return hql.concat("ORDER BY e.idEstudante");
    }


    private void popularParametrosBuscaEstudante(EstudanteDTO estudanteDTO, Query query) {

        if(estudanteDTO.getIdTurma() != null) {
            query.setParameter("idTurma", estudanteDTO.getIdTurma());
        }

        if(estudanteDTO.getIdEstudante() != null) {
            query.setParameter("idEstudante", estudanteDTO.getIdEstudante());
        }

        if(estudanteDTO.getDataNascimento() != null) {
            query.setParameter("dataNascimento", estudanteDTO.getDataNascimento());
        }

        if(estudanteDTO.getNome() != null) {
            String nome = "%" + estudanteDTO.getNome() + "%";
            nome = nome.toLowerCase();
            query.setParameter("nome",nome);
        }

        if(estudanteDTO.getEmail() != null) {
            String email = estudanteDTO.getEmail().toLowerCase();
            query.setParameter("email",email);
        }
    }

}
