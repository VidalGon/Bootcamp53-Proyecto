package pe.com.nttdata.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.customer.model.Customer;
import pe.com.nttdata.customer.repository.CustomerRepository;
import pe.com.nttdata.customer.service.CustomerService;



@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository repo;
	
	@Override
	public Optional<Customer> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) repo.findAll();
	}

	@Override
	public Customer create(Customer customer) {
		return repo.save(customer);
	}

	@Override
	public Customer edit(Customer customer) {
		// TODO Auto-generated method stub
		return repo.save(customer);
	}

}
