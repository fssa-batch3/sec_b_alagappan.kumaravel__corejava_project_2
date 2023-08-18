package in.fssa.sportshub.service;


import in.fssa.sportshub.dao.PlayerDAO;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.validator.AddressValidator;
import in.fssa.sportshub.validator.PlayerValidator;
public class PlayerService {
//	service doa validator util
	
	/**
	 * 
	 * @param player
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean phoneNumberAlreadyExist(long phoneNumber) throws Exception{
		PlayerValidator.validatePhoneNumber(phoneNumber);
		PlayerDAO dao = new PlayerDAO();
		return dao.phoneNumberAlreadyExist(phoneNumber);
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean playerExist(int id) throws Exception{
		PlayerValidator.validateId(id, "Player");
		PlayerDAO dao = new PlayerDAO();
		return dao.playerExist(id);
	}
	
	/**
	 * 
	 * @param player
	 * @throws Exception
	 */
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
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception{
		
		PlayerValidator.validateId(id, "Player");
		boolean checkPlayerExist = this.playerExist(id);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptain(id);
		if(isCaptain){
			throw new Exception("Player is captain of a team");
		}
		
			PlayerDAO playerDao = new PlayerDAO();
			playerDao.delete(id);	
	}
	
}


