package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.CasoDeTeste;
import br.ifms.cx.algjudge.domain.Problema;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Classe que cuida de operacoes de banco de dados referente a problemas
 *
 * @author Gustavo
 */
@Component
public class ProblemaDAO extends HibernateDAO<Problema> {

    public ProblemaDAO() {
        super(Problema.class);
    }

    /**
     * Metodo que busca um problema pelo seu ID
     *
     * @param id
     * @return
     */
    public Problema buscarProblemaPorId(Long id) {
        return get(id);
    }

    /**
     * Metodo para salvar um problema no banco de dados
     *
     * @param p
     */
    public void persistirProblema(Problema p) {
        super.save(p);
    }

    /**
     * Metodo que retorna uma lista de problema. É necessário passar um numero
     * desejado de problemas a serem listados
     *
     * @param qtde
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Problema> listarProblemas(Integer page) {
        page--;
        Query query = super.createQuery("from Problema as p where p.ativo = true");
        query.setFirstResult(page*10);
        query.setMaxResults(10);
        return query.list();
    }

    public List<CasoDeTeste> buscarCasoDeTestePorIdProblema(Long idProblema) {
        Query query = super.createQuery("from CasoDeTeste as c join fetch c.problema p where c.problema.id = :id and p.ativo = true");
        query.setParameter("id", idProblema);
        return query.list();
    }
    
    public List<CasoDeTeste> buscarExemplosDeCasosDeTestePorIdProblema(Long idProblema) {
        Query query = super.createQuery("SELECT new CasoDeTeste(c.id, c.entrada, c.saida) from CasoDeTeste as c where c.problema.id = :id and c.exemplo = true and c.ativo = true");
        query.setParameter("id", idProblema);
        return query.list();
    }
}
