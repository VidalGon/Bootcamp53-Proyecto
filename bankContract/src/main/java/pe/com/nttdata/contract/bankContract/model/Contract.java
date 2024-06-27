package pe.com.nttdata.contract.bankContract.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
	@Id
	private String id;
	//private boolean createAccount;	
	private String account;	
	private Product product;
	private Customer customer;
	private Date DateRegister;
	//credito:
	private BigDecimal creditLine;
	private BigDecimal amountCreditLine;
	private BigDecimal numberInstallments;
	private BigDecimal TotalNumberInstallments;
	
	private BigDecimal montoInicial;
	private BigDecimal monto;
	private List<Customer> titulares;
	private List<Customer> firmantes;
	//
	
}
