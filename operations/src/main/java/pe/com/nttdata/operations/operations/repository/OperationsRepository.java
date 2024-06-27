package pe.com.nttdata.operations.operations.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.com.nttdata.operations.operations.model.Operations;
import pe.com.nttdata.operations.operations.model.Product;
import reactor.core.publisher.Flux;

public interface OperationsRepository extends ReactiveMongoRepository<Operations, String>{
	Flux<Operations> findByProduct(String productId); 

}
