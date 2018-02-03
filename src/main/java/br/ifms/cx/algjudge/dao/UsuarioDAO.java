/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.PapelDoUsuarioEnum;
import br.ifms.cx.algjudge.domain.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo
 */
@Component 
public class UsuarioDAO extends HibernateDAO<Usuario>{
    
    private Usuario user;
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    @SuppressWarnings("unchecked")
    /**
     * listagem de usúarios do sistema
     * @param papel
     * @return 
     */
    public List<Usuario> listar (String papel) {
        Query query = super.createQuery("FROM Usuario u WHERE u.papel LIKE % ? AND u.delete = false");
        query.setString(0, papel);
        return query.list();
    }
    
    /**
     * inserção de um admin
     * @param u 
     */
    public void inserirAdmin (Usuario u) {
      u.setPapel(PapelDoUsuarioEnum.ADMINISTRADOR.toString());
      super.save(u);
    }
    
    /**
     * inserção de um professor
     * @param u 
     */
    public void inserirProfessor (Usuario u) {
       u.setPapel(PapelDoUsuarioEnum.PROFESSOR.toString());
       super.save(u);
    }
    
    /**
     * inserção de um aluno
     * @param u 
     */
    public void inserirAluno (Usuario u) {
       u.setPapel(PapelDoUsuarioEnum.ALUNO.toString());
       super.save(u);
    }
    
    /**
     * delete logíco de user
     * @param id 
     */
    public void delete (Integer id) {
        user = super.get(id);
        user.setAtivo(false);
        super.update(user);
    }
    
    /**
     * update de um user
     * @param usuario
     * @param id 
     */
    public void updateUsuario (Usuario usuario, Integer id) {
        
        user = new Usuario();
        
        user = super.get(id);
        
        user.setNome(usuario.getNome());
        user.setSenha(usuario.getSenha());
        user.setEmail(usuario.getEmail());
        user.setPapel(usuario.getPapel());
        
        super.update(user);
    }
    
    public Usuario buscarUsuarioPorEmail (String userName) {
        return null;
    }
    
}
