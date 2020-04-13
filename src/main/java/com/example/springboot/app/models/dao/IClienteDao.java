package com.example.springboot.app.models.dao;

import java.util.List;

import com.example.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public void delete(Long ID);
		
	public Cliente findOneByID(Long ID);
	
}
