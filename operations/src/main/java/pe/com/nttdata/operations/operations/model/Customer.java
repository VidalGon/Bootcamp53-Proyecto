package pe.com.nttdata.operations.operations.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "customer")
public class Customer {
	@Id
	private String id;
	private String name;	
	private String lastName;
	private String typeCustomer;
	private String dateRegister;
	private String Birthdate;//fecha de Nacimiento
}
