package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 *
 * Classe que cuida de operacoes de banco de dados referente a {@link Usuario}
 *
 * @author Gustavo
 */
@Component
public class UsuarioDAO extends HibernateDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        Query query = super.createQuery("FROM Usuario u WHERE u.email LIKE :email AND u.ativo = true");
        query.setParameter("email", email);
        query.setMaxResults(1);
        return (Usuario) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    /**
     * listagem de usuarios do sistema
     *
     * @param papel
     * @return
     */
    public List<Usuario> listar(String papel) {
        Query query = super.createQuery("FROM Usuario u WHERE u.papel LIKE % ? AND u.ativo = true");
        query.setString(0, papel);
        return query.list();
    }

    /**
     * insercao de um admin
     *
     * @param u
     */
    public void inserirAdmin(Usuario u) {
        u.setPapel(Usuario.PAPEL_ADMINISTRADOR);
        super.save(u);
    }

    /**
     * insercao de um professor
     *
     * @param u
     */
    public void inserirProfessor(Usuario u) {
        u.setPapel(Usuario.PAPEL_PROFESSOR);
        super.save(u);
    }

    /**
     * insercao de um aluno
     *
     * @param u
     */
    public void inserirAluno(Usuario u) {
        u.setPapel(Usuario.PAPEL_ALUNO);
        super.save(u);
    }

    /**
     * delete logica de {@link Usuario}
     *
     * @param id
     */
    public void delete(Integer id) {
        Usuario user = super.get(id);
        user.setAtivo(Boolean.FALSE);
        super.update(user);
    }

    /**
     * update de um {@link Usuario}
     *
     * @param usuario
     * @param id
     */
    public void updateUsuario(Usuario usuario, Integer id) {

        Usuario user = new Usuario();

        user = super.get(id);

        user.setNome(usuario.getNome());
        user.setSenha(usuario.getSenha());
        user.setEmail(usuario.getEmail());
        user.setPapel(usuario.getPapel());

        super.update(user);
    }
}