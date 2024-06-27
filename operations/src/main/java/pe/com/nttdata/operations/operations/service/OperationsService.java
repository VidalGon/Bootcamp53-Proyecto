package pe.com.nttdata.operations.operations.service;

import pe.com.nttdata.operations.operations.model.Operations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationsService {
	Mono<Operations> findById(String id);
	Flux<Operations> findByAll();
	Mono<Operations> create(Operations operations);
	Flux<Operations> historialByProduct(String productId);
	//Mono<Operations> edit(Operations operations);
}
