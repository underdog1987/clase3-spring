package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sf;

	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		// Crea la session y la inicia
		Session s= sf.openSession(); //org.hibernate.session
		s.beginTransaction();
		// inicia transaccion
		
		Usuario u = (Usuario) s.get(Usuario.class, usuarioId);
		
		/* Aqui arriba van los cambios a la base */
		// commit (obtiene transaccion actual y guarda en la base)
		s.getTransaction().commit();
		// cerrar
		s.close();
		return u;
	}

	public void creaUsuario(Usuario usuario) {
		// Crea la session y la inicia
		Session s= sf.openSession(); //org.hibernate.session
		s.beginTransaction();
		// inicia transaccion
		s.saveOrUpdate(usuario);
		/* Aqui arriba van los cambios a la base */
		// commit (obtiene transaccion actual y guarda en la base)
		s.getTransaction().commit();
		// cerrar
		s.close();
		
		
	}

	public Usuario buscaPorEmailPassword(String email, String password) {
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Criteria criterio = s.createCriteria(Usuario.class); // La clase mapeada a la tabla
		// Nombre de la propiedad de la clase y con qué se compara
		// Por default AND
		criterio.add(Restrictions.eq("email", email));
		// Nombre de la propiedad de la clase y con qué se compara
		criterio.add(Restrictions.eq("password", password));
		
		//Ejemplo usando OR
		/*
		criterio.add(Restrictions.or(	
			Restrictions.eq("password", password),
			Restrictions.eq("email", email)
			)
		);
		*/
		
		// Obtener el resultado
		// Si no encuentra nada retorna NULL
		Usuario userResult =(Usuario) criterio.uniqueResult(); // devuelve objeto
		
		s.getTransaction().commit();
		s.close();
		
		return userResult;
		
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		
		nombre="%"+nombre+"%";
		
		List<Usuario> ret;
		
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Criteria criterio = s.createCriteria(Usuario.class); // La clase mapeada a la tabla

		criterio.add(Restrictions.like("nombre", nombre));
		ret =criterio.list();	// Para cuando la consulta da mas de una fila
		
		s.getTransaction().commit();
		s.close();
		
		return ret;
	}

}
