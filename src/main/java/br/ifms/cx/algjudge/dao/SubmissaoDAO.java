/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.Submissao;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo
 */
@Component
public class SubmissaoDAO extends HibernateDAO<Submissao>{
    private Submissao submissao;
    
    public SubmissaoDAO() {
        super(Submissao.class);
    }
   
    /**
     * salvar uma submissão
     * @param s 
     */
    public void salvarSubmissao (Submissao s) {
        super.save(s);
    }
    
    /**
     * update uma submissão
     * @param s
     * @param id 
     */
    public void updateSubmissao (Submissao s, Integer id) {
      submissao = super.get(id);
      
      submissao.setCodigoFonte(s.getCodigoFonte());
      submissao.setDataEnvio(s.getDataEnvio());
      submissao.setSituacao(s.getSituacao());
      submissao.setTempoExecucao(s.getTempoExecucao());
      
      super.update(submissao);
    }
    
    /**
     * delete uma submissão
     * @param s
     * @param id 
     */
    public void deleteSubmissao (Integer id) {
      submissao = super.get(id);
      submissao.setDelete(true); 
      super.update(submissao);
    }
    
    /**
     * lista de submissões
     * @param s
     * @param id 
     */
    public List <Submissao> listSubmissao () {
      Query query = super.createQuery("FROM Submissao s WHERE s.delete = false");
      return query.list();
    }
}
