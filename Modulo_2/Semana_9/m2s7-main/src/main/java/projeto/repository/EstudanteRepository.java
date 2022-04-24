package projeto.repository;

import projeto.dto.EstudanteDTO;

import javax.persistence.NoResultException;
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
}
