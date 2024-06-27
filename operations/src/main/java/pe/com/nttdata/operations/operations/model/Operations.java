package pe.com.nttdata.operations.operations.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operations {
	@Id
	private String id;
	private Contract contract;//{id}
	private Product product;//{id}
	private String operationType;//D=deposito, R=retiro, P=pago
	private BigDecimal amount;
	private String 	originAccount;
	private String 	destinationAccount;
	private LocalDateTime registerDate;
}
