package pe.com.nttdata.contract.bankContract.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.contract.bankContract.model.Contract;
import pe.com.nttdata.contract.bankContract.model.Customer;
import pe.com.nttdata.contract.bankContract.model.Product;
import pe.com.nttdata.contract.bankContract.model.RequestContract;
import pe.com.nttdata.contract.bankContract.service.ContractService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v01/contracts")
@Slf4j
public class ContractController {
	
	@Value("${dato.typeProduct.b1}")
	private String ahorro;//="Cuenta de ahorro";
	@Value("${dato.typeProduct.b2}")
	private String corriente;//="Cuanta Corriente";
	@Value("${dato.typeProduct.b3}")
	private String plazoFijo;
	String typeCustomer;//="Empresarial";
	String typeProducto="cuenta";
	
	@Autowired
	ContractService service;
	
	//localhost:8092/api/v01/customer/find/{1}
	@GetMapping("/find/{id}")
	public Mono<Contract> findById(String id) {
		log.info("Buscando el cliente: "+id);
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	public Flux<Contract> findAll() {
		log.info("Buscando todos los Clientes");
		return service.findAll();
	}
	@GetMapping("/historial/{customerId}")
	public Flux<Contract> findHistorial(Customer customer) {
		log.info("Buscando todos los Clientes");
		return service.findByCustomer(customer).map(c->{
			//c.setCreateAccount(false);
			c.setFirmantes(null);
			c.setMontoInicial(null);
			c.setTitulares(null);
			return c;
		});
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<Contract>> create(@RequestBody RequestContract request) {
		log.info("iniciando Contrato");
		Contract objContract = new Contract();
		objContract.setCustomer(request.getCustomer());
		objContract.setDateRegister(new Date());
		objContract.setMontoInicial(request.getMontoInicial());
		objContract.setMonto(request.getMonto());
		objContract.setProduct(request.getProduct());
		if (request.getProduct().getName().contains("Credito")) {
			objContract.setAccount(UUID.randomUUID().toString());
		}else {
			objContract.setAccount(request.getContract().getAccount());
		}

		if(request.getCustomer().getTypeCustomer().equals("Personal"))
		{
			return service.findByCustomerAndProduct(request.getCustomer(),request.getProduct())
					.flatMap(c->{
						return Mono.just(ResponseEntity.badRequest().body(c));
					})
					.switchIfEmpty(Mono.just(ResponseEntity.created(URI.create("api/v01/contracts"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.create(objContract).block()))
							);
					//.switchIfEmpty(Mono.just( ResponseEntity.status(HttpStatus.CREATED).body(service.create(objContract).block())) );
					//.defaultIfEmpty(ResponseEntity.status(HttpStatus.CREATED).body(service.create(objContract).block()));
		}
		else if(request.getCustomer().getTypeCustomer().equals("Empresarial") && !request.getProduct().getName().equals(ahorro) && !request.getProduct().getName().equals(plazoFijo))
		{
			objContract=service.create(objContract).block();
			return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(objContract));
		}else {			
			return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(objContract));
		}
	}
	@PutMapping("/edit")
	public Mono<Contract> edit(@RequestBody Contract request) {
		return service.edit(request);
	}
	
}
