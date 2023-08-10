package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.service.AddressService;

public class TestCreateAddress {
	
	@Test
	public void CreateAddressWithValidData() throws Exception {
		AddressService addressCreate = new AddressService();
		
		Address address = new Address();
		address.setArea("Aminjikarai");
		address.setDistrict("Chennai");
		assertDoesNotThrow(()->{
			addressCreate.create(address);
		});
	}
	
	@Test
	public void FindAddressId() throws Exception {
		AddressService addressFind = new AddressService();

		Address data = addressFind.findById(1);
		System.out.println(data.toString());
	}
	
	@Test
	public void GetAllAddress() throws Exception {
		AddressService addressFind = new AddressService();

		Set<Address> data = addressFind.getAllAddress();
		data.forEach(System.out::println);
	}
	
}
