package com.example.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.springboot.app.models.entity.Cliente;

/*  Componente de persistencia de acceso a datos,
 *  se le puede poner el nombre o no. Si no se le pone
 *  nombre, toma el nombre de la clase 
 *  
   Componente de persitencia, de acceso a datos.*/
@Primary
@Repository
public class ClienteDaoImple implements IClienteDao {
	
	/*Se encarga de manejar las clases de entidades, el ciclo de vida, 
	 hace todas las operaciones a base de datos pero a nivel de objeto.
	 
	 PersisteceContext contiene la unidad de persitencia.. Inyecta el objeto !!!! */
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cliente> findAll() {
		/*Se hace consulta desde entityManager*/
		return entityManager.createQuery("from Cliente").getResultList();
	}
	
	@Override
	public Cliente findOneByID(Long ID) {
		return entityManager.find(Cliente.class, ID);
	}

	@Override
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			entityManager.merge(cliente);
		}else {
			entityManager.persist(cliente);			
		}
	}

	@Override
	public void delete(Long ID) {
		entityManager.remove(findOneByID(ID));
	}
}
