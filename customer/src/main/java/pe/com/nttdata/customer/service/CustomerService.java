package pe.com.nttdata.customer.service;

import java.util.List;
import java.util.Optional;

import pe.com.nttdata.customer.model.Customer;



public interface CustomerService {
	public List<Customer> findAll();
	public Optional<Customer> findById(String id);
	public Customer create(Customer customer);
	public Customer edit(Customer customer);
}
