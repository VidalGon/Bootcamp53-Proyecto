package pe.com.nttdata.contract.bankContract.service;


import pe.com.nttdata.contract.bankContract.model.Contract;
import pe.com.nttdata.contract.bankContract.model.Customer;
import pe.com.nttdata.contract.bankContract.model.Product;
import pe.com.nttdata.contract.bankContract.model.RequestContract;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractService {
	public Flux<Contract> findAll();
	public Mono<Contract> findById(String id);
	public Mono<Contract> findByCustomerAndProduct(Customer customer,Product product); 
	public Flux<Contract> findByCustomer(Customer customer); 
	public Mono<Contract> create(Contract contract);
	public Mono<Contract> edit(Contract contract);
}
