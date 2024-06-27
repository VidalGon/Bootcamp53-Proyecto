package pe.com.nttdata.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.customer.model.Customer;
import pe.com.nttdata.customer.service.CustomerService;

@RestController
@RequestMapping("api/v01/customer")
@Slf4j
public class CustomerController {
	@Autowired
	CustomerService service;
	
	//localhost:8092/api/v01/customer/find/{1}
	@GetMapping("/find/{id}")
	public Optional<Customer> findById(String id) {
		log.info("Buscando el cliente: "+id);
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	public List<Customer> findAll() {
		log.info("Buscando todos los Clientes");
		return service.findAll();
	}
	@PutMapping("/update")
	public List<Customer> update(@RequestBody Customer customer) {
		log.info("Buscando todos los Clientes");
		return service.findAll();
	}
	@DeleteMapping("/delete")
	public List<Customer> delete( Customer customer) {
		log.info("Buscando todos los Clientes");
		return service.findAll();
	}
	
	/*
	@GetMapping("/contract")
	public Contract contract(
			@RequestBody RequestContract request) {
		log.info("iniciando Contrato");
		//return service.create faltaaaaaaaaaaa
		return null;
	}*/
}
