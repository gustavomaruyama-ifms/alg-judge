/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.dao;

import br.ifms.cx.algjudge.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo
 */
@Component 
public class UsuarioDAO extends HibernateDAO<Usuario>{
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    
    
}
