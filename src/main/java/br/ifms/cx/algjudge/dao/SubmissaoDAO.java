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
public class SubmissaoDAO extends HibernateDAO<Submissao> {

    private Submissao submissao;

    public SubmissaoDAO() {
        super(Submissao.class);
    }

    /**
     * salvar uma submissÃ£o
     *
     * @param s
     */
    public void salvarSubmissao(Submissao s) {
        super.save(s);
    }

    /**
     * update uma submissÃ£o
     *
     * @param s
     * @param id
     */
    public void updateSubmissao(Submissao s, Integer id) {
        submissao = super.get(id);

        submissao.setCodigoFonte(s.getCodigoFonte());
        submissao.setDataEnvio(s.getDataEnvio());
        submissao.setSituacao(s.getSituacao());
        submissao.setTempoExecucao(s.getTempoExecucao());

        super.update(submissao);
    }

    /**
     * delete uma submissÃ£o
     *
     * @param s
     * @param id
     */
    public void deleteSubmissao(Integer id) {
        submissao = super.get(id);
        submissao.setAtivo(Boolean.FALSE);
        super.update(submissao);
    }

    /**
     * lista de submissoes
     *
     * @param s
     * @param id
     */
    public List<Submissao> listarSubmissoesPorIdUsuario(Long id, int page) {
        page--;
        Query query = super.createQuery("SELECT new Submissao(s.id, s.codigoFonte, s.dataEnvio, s.tempoExecucao, s.situacao, s.problema.id, s.problema.titulo)FROM Submissao s WHERE s.usuario.id = :id AND s.ativo = true ORDER BY s.id DESC");
        query.setParameter("id", id);
        query.setFirstResult(page * 10);
        query.setMaxResults(10);
        return query.list();
    }
}
