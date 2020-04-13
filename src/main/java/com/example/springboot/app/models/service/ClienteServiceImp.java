package com.example.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.app.models.dao.IClienteDao;
import com.example.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	IClienteDao clienteDao;
	
	/*Marcamos al metodo como solo de lectura ya que solo es una consulta.
	 * Cuando son otro metodo, se puedo omitir readOnly porque hay unos que son de
	 * escritura.*/
	@Transactional(readOnly=true) 
	@Override
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true) 
	public Cliente findOneByID(Long ID) {
		return clienteDao.findOneByID(ID);
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long ID) {
		clienteDao.delete(ID);
	}	

}
