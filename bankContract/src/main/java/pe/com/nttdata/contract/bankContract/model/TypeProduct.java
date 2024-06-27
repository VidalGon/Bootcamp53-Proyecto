package pe.com.nttdata.contract.bankContract.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(value = "TypeProduct")
public class TypeProduct {
	private String id;
	private String description;	
}
