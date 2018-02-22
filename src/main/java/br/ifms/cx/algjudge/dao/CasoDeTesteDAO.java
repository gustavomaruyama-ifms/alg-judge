/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.CasoDeTeste;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo
 */
@Component 
public class CasoDeTesteDAO extends HibernateDAO<CasoDeTeste>{
    
    private CasoDeTeste caso;
    
    
    public CasoDeTesteDAO() {
        super(CasoDeTeste.class);
    }
    
    /**
     * salvar caso de teste
     * @param c 
     */
    public void inserirCasoDeTeste (CasoDeTeste c) {
        super.save(c);
    }
    
    /**
     * update caso de teste
     * @param c
     * @param id 
     */
    public void updateCasoDeTeste (CasoDeTeste c) {
        caso = get(c.getId());
        
        caso.setEntrada(c.getEntrada());
        caso.setExemplo(c.getExemplo());
        caso.setSaida(c.getSaida());
        
        super.update(c);
    }
    
    /**
     * delatar um caso de teste
     * @param id 
     */
    public void deleteCasoDeTeste (CasoDeTeste c) {
        caso = get(c.getId());
        caso.setAtivo(false);
        super.update(caso);
    }
    
    /**
     * salvar caso de teste
     * @param c 
     */
    public void salvarCasoDeTeste (CasoDeTeste c) {
        super.save(c);
    }
    
    /**
     * listar casos de teste
     * @return 
     */
    public List<CasoDeTeste> listarCasoDeTeste (Long id) {
        Query query = super.createQuery("Select new CasoDeTeste(c.id,c.exemplo,c.entrada,c.saida) from CasoDeTeste c where c.ativo = true and c.problema.id = :id");
        query.setParameter("id", id);
        return query.list();
    }
}
