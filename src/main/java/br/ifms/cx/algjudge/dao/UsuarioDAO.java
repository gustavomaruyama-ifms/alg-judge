package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.Problema;
import br.ifms.cx.algjudge.domain.Usuario;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Classe que cuida de operacoes de banco de dados referente a {@link Usuario}
 * @author Gustavo
 */
@Component
public class UsuarioDAO extends HibernateDAO<Problema> {

    public UsuarioDAO() {
        super(Problema.class);
    }

    /**
     * Metodo que busca um {@link Usuario} pelo seu ID
     * @param id
     * @return 
     */
    public Usuario buscarUsuarioPorEmail(String email) {
        Query query = super.createQuery("FROM Usuario u WHERE u.email LIKE :email");
        query.setParameter("email", email);
        query.setMaxResults(1);
        return (Usuario) query.uniqueResult();
    }

}
