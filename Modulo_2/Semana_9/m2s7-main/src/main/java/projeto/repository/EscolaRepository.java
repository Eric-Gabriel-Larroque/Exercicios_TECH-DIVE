package projeto.repository;

import projeto.dto.EscolaDTO;
import projeto.dto.EstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.entity.Escola;
import projeto.entity.Turma;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class EscolaRepository extends GenericRepository{


    public List<EscolaDTO> consultarEscolas() {
        return entityManager.createNamedQuery(Escola.GET_ESCOLASDTO, EscolaDTO.class)
                .getResultList();
    }


    public List<EscolaDTO> consultaEscolaPeloNome(String query) {

        query = "%" + query + "%";
        query = query.toLowerCase();

        try {
            return entityManager.createQuery("SELECT new projeto.dto.EscolaDTO(e.idEscola, e.nome) " +
                            "FROM Escola e " +
                            "WHERE CAST(e.idEscola AS string) = :query " +
                            "OR LOWER(e.nome) LIKE :query", EscolaDTO.class)
                    .setParameter("query", query)
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
