package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		
		UsuarioDAO uDAO = (UsuarioDAO) contexto.getBean("usuarioDAO"); 
		// Mismo nombre que se usa
		// en la anotation @Component de HibernateUsuaroDAO
		
		Usuario u = new Usuario();
		u.setUsuarioId(3);
		u.setNombre("Guillermo");
		u.setEmail("foobar@gmail.com");
		u.setPassword("Tradec0@");
		
		
		uDAO.creaUsuario(u);

		System.out.println("Ya acabamos de crear");
		
		
		u.setPassword("Abcd3f.");
		uDAO.creaUsuario(u);
		
		System.out.println("Ya acabamos de modificar");
		
		Usuario uBD = uDAO.buscaUsuarioPorId(3);
		System.out.println(uBD.toString());
		
		
		Usuario uCriterio = uDAO.buscaPorEmailPassword("foobar@gmail.com", "Abcd3f");
		
		System.out.println("Encontrado con criterio de Hibernate (email / password) \n"
		+ uCriterio);
		
		System.out.println("Criterio Like ======");
		List<Usuario> users=uDAO.buscaPorNombre("z");
		System.out.println("Usuarios encontrados: " + users);
		
	}

}
