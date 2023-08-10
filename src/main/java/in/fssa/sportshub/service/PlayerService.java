package in.fssa.sportshub.service;


import in.fssa.sportshub.dao.PlayerDAO;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.validator.AddressValidator;
import in.fssa.sportshub.validator.PlayerValidator;
public class PlayerService {
	public void create(Player player) throws Exception{
		
		PlayerValidator.validateAll(player);
		
		boolean checkPhoneNumberExist = this.phoneNumberAlreadyExist(player.getPhoneNumber());
		if(!checkPhoneNumberExist){
			
			Address address = player.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			player.getAddress().setId(addressId);
			
			PlayerDAO playerDao = new PlayerDAO();
			playerDao.create(player);	
			
		}else {
			throw new ValidationException("Phone number already exist");
		}
	}
	
	public boolean phoneNumberAlreadyExist(long phoneNumber) throws Exception{
		PlayerValidator.validatePhoneNumber(phoneNumber);
		PlayerDAO dao = new PlayerDAO();
		return dao.phoneNumberAlreadyExist(phoneNumber);
	}
	
	public boolean playerExist(int id) throws Exception{
		PlayerValidator.validateId(id, "Player");
		PlayerDAO dao = new PlayerDAO();
		return dao.playerExist(id);
	}
	
	public void update(Player player) throws Exception{
		
		PlayerValidator.validatePartial(player);
		AddressValidator.validate(player.getAddress());
		boolean checkPlayerExist = this.playerExist(player.getId());
		if(checkPlayerExist){
			
			Address address = player.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			player.getAddress().setId(addressId);
			
			PlayerDAO playerDao = new PlayerDAO();
			playerDao.update(player);	
			
		}else {
			throw new ValidationException("Player not exist");
		}
	}
	
	public void delete(int id) throws Exception{// not check team already exist
		
		PlayerValidator.validateId(id, "PLayer");
		boolean checkPlayerExist = this.playerExist(id);
		if(checkPlayerExist){
			
			PlayerDAO playerDao = new PlayerDAO();
			playerDao.delete(id);	
			
		}else {
			throw new ValidationException("Player not exist");
		}
	}
	
}


