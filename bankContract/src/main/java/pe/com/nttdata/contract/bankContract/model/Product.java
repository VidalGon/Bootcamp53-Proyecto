package pe.com.nttdata.contract.bankContract.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(value = "Product")
public class Product {
	@Id
	private String id;
	private String name;
	private TypeProduct type;
	private String movementNumber;
	private BigDecimal commission;
	private Integer limitOfMovements;
	private Boolean requeridAccount;
	
}
