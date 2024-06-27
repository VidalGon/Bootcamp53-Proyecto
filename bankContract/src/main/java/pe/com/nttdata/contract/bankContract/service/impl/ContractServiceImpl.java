package pe.com.nttdata.contract.bankContract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.contract.bankContract.model.Contract;
import pe.com.nttdata.contract.bankContract.model.Customer;
import pe.com.nttdata.contract.bankContract.model.Product;
import pe.com.nttdata.contract.bankContract.repository.ContractRepository;
import pe.com.nttdata.contract.bankContract.service.ContractService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService{
	
	@Autowired
	ContractRepository repo;
	
	@Override
	public Mono<Contract> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Flux<Contract> findAll() {
		return repo.findAll();
	}
	@Override
	public Mono<Contract> create(Contract contract) {
		return repo.save(contract);
	}

	@Override
	public Mono<Contract> findByCustomerAndProduct(Customer customer, Product product) {
		return repo.findByCustomerAndProduct(customer, product);
	}

	@Override
	public Flux<Contract> findByCustomer(Customer customer) {
		return repo.findByCustomer(customer);
	}

	@Override
	public Mono<Contract> edit(Contract contract) {
		return repo.save(contract);
	}
}
