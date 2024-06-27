package pe.com.nttdata.customer.repository;

import org.springframework.data.repository.CrudRepository;
import pe.com.nttdata.customer.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, String>  {

}
