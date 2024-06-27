package pe.com.nttdata.operations.operations.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.nttdata.operations.operations.model.Contract;
import pe.com.nttdata.operations.operations.model.Operations;
import pe.com.nttdata.operations.operations.repository.OperationsRepository;
import pe.com.nttdata.operations.operations.service.OperationsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperationsServiceImpl implements OperationsService {

	private final WebClient webClientContract= WebClient.create("http://localhost:8092/api/v01/contracts");
	
	@Autowired
	OperationsRepository repo;
	
	@Override
	public Mono<Operations> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Flux<Operations> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Operations> create(Operations operations) {
		Mono<Contract> webContract=findByContract(operations.getContract().getId())
				.flatMap(o->{
					if(operations.getOperationType().equalsIgnoreCase("R") && (o.getMonto().compareTo(operations.getAmount()))>=0 )
					{
						o.setMonto(o.getMonto().subtract(operations.getAmount()));
						return webClientContract.put().uri("/edit",o)
								.accept(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromObject(o))
								.retrieve()
								.bodyToMono(Contract.class);
					}
					if(operations.getOperationType().equalsIgnoreCase("D") )
					{
						o.setMonto(o.getMonto().add(operations.getAmount()));
						return webClientContract.put().uri("/edit",o)
								.accept(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromObject(o))
								.retrieve()
								.bodyToMono(Contract.class);
					}
					if(operations.getOperationType().equalsIgnoreCase("P") )
					{
						o.setMonto(o.getMonto().subtract(operations.getAmount()));
						o.setCreditLine(o.getCreditLine().subtract(operations.getAmount()));
						o.setNumberInstallments(o.getNumberInstallments().subtract(new BigDecimal(1)));
						
						return webClientContract.put().uri("/edit",o)
								.accept(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromObject(o))
								.retrieve()
								.bodyToMono(Contract.class);
					}
					if(operations.getOperationType().equalsIgnoreCase("RC") && (o.getAmountCreditLine().add(operations.getAmount())).compareTo(o.getCreditLine())>0 )
					{
						o.setAmountCreditLine(o.getAmountCreditLine().add(operations.getAmount()));
						
						return webClientContract.put().uri("/update",o)
								.accept(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromObject(o))
								.retrieve()
								.bodyToMono(Contract.class);
					}
					return Mono.just(o);
				});
		operations.setContract(webContract.block());
		return repo.save(operations);
	}

	@Override
	public Flux<Operations> historialByProduct(String productId) {
		return repo.findByProduct(productId);
	}
	public Mono<Contract> findByContract(String id){
		return webClientContract.get().uri("/find/{id}",id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Contract.class);
	}

}
