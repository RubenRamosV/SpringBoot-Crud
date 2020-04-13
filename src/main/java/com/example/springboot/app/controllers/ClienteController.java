package com.example.springboot.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.springboot.app.models.entity.Cliente;
import com.example.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("listaClientes",clienteService.findAll());
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario de cliente");
		model.addAttribute("cliente", cliente);
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult bingindResult,Model model, SessionStatus status) {
		/*Se agrega el parametro bindingResult, este objeto valida si se originaron
		 * errores a la hora de hacer binding, si hay errores, se regresa la vista 
		 * del formulario y se muestran los errores al cliente.*/
		
		if(bingindResult.hasErrors()) {
			/*Se agregan los valores que necesita la vista. El modelo cliente se pasa de forma
			 * automatica*/
			model.addAttribute("titulo","Formulario de cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		status.isComplete();
		return "redirect:listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id,Model model){
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteService.findOneByID(id);
		}else {
			return "redirect:listar";
		}
		
		model.addAttribute("titulo","Editar cliente");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			clienteService.delete(id);
		}
		return "redirect:/clientes/listar";
	}
}
