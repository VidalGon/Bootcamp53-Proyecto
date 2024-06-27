package pe.com.nttdata.contract.bankContract.repository;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.com.nttdata.contract.bankContract.model.Contract;
import pe.com.nttdata.contract.bankContract.model.Customer;
import pe.com.nttdata.contract.bankContract.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractRepository extends ReactiveMongoRepository<Contract, String>  {
	Mono<Contract> findByCustomerAndProduct(Customer customer,Product product); 
	Flux<Contract> findByCustomer(Customer customer);
}
