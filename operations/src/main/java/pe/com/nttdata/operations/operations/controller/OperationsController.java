package pe.com.nttdata.operations.operations.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.operations.operations.model.Operations;
import pe.com.nttdata.operations.operations.service.OperationsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v01/operation")
@Slf4j
public class OperationsController {

	@Autowired
	OperationsService service;
	
	@GetMapping("/{id}")
	public Mono<Operations> index(String id){
		return service.findById(id);
	}
	
	@GetMapping("/historial/{productId}")
	public Flux<Operations> historial(String productId){
		return service.historialByProduct(productId);//modificar, es por producto y cliente
	}
	
	@PostMapping("/Create-operation")
	public Mono<Operations> create(@RequestBody Operations operation){
		return service.create(operation); 
	}
}
