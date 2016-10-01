package br.senai.sp.informatica.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.informatica.todolist.model.Usuario;

@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public List<Usuario> listar() {
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return query.getResultList();
	}
	
	public Usuario logar(Usuario usuario) {
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		
		// outra forma de fazer - forma certa
		// List<Usuario> lista = query.getResultList();
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
