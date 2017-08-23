package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.Problema;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Classe que cuida de operacoes de banco de dados referente a problemas
 * @author Gustavo
 */
@Component
public class ProblemaDAO extends HibernateDAO<Problema> {

    public ProblemaDAO() {
        super(Problema.class);
    }

    /**
     * Metodo que busca um problema pelo seu ID
     * @param id
     * @return 
     */
    public Problema buscarProblemaPorId(Long id) {
        //Query query = super.createQuery("FROM Coordenada c ORDER BY c.id DESC WHERE c.id = :id");
        //query.setParameter("id", id);
        //query.setMaxResults(1);
        //return (Problema) query.uniqueResult();
        return get(id);
    }

    /**
     * Metodo para salvar um problema no banco de dados
     * @param p 
     */
    public void persistirProblema(Problema p) {
        super.save(p);
    }

    /**
     * Metodo que retorna uma lista de problema. É necessário passar um numero desejado de problemas a serem listados
     * @param qtde
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<Problema> listarProblemas(Integer qtde) {
        Query query = super.createQuery("FROM Problema ORDER BY id DESC");
        query.setMaxResults(qtde);
        return query.list();
    }
}
