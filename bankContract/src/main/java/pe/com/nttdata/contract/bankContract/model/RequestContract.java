package pe.com.nttdata.contract.bankContract.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RequestContract {
	private Product product;
	private Customer customer;
	private BigDecimal montoInicial;
	private BigDecimal monto;
	private Contract contract;
	
}
