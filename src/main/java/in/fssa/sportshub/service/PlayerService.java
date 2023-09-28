package in.fssa.sportshub.service;

import java.security.NoSuchAlgorithmException;

import in.fssa.sportshub.dao.PlayerDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.util.SecureUtils;
import in.fssa.sportshub.util.StringUtil;
import in.fssa.sportshub.validator.AddressValidator;
import in.fssa.sportshub.validator.PlayerValidator;

public class PlayerService {

	/**
	 * 
	 * @param player
	 * @throws ValidationException, ServiceException
	 */
	public void create(Player player) throws ValidationException, ServiceException {

		try {
			PlayerValidator.validateCreate(player);

			Address address = player.getAddress();
			AddressService addressService = new AddressService();
			String password = player.getPassword();
			String encriptedPassword = SecureUtils.encryptPassword(password);
			player.setPassword(encriptedPassword);
			int addressId = addressService.create(address);

			player.getAddress().setId(addressId);

			PlayerDAO playerDAO = new PlayerDAO();
			playerDAO.create(player);

		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param player
	 * @throws ValidationException, ServiceException
	 */
	public void update(Player player) throws ValidationException, ServiceException {
		try {

			PlayerValidator.validateUpdate(player);

			Address address = player.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);

			player.getAddress().setId(addressId);

			PlayerDAO playerDAO = new PlayerDAO();
			playerDAO.update(player);

		} catch (ValidationException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			System.out.println("service in 1");
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException, ServiceException
	 */
	public void delete(int id) throws ValidationException, ServiceException {
		try {
			PlayerValidator.validateDelete(id);
			PlayerDAO playerDAO = new PlayerDAO();
			playerDAO.delete(id);

		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void changeDelete(int id) throws ValidationException, ServiceException {
		try {
			PlayerValidator.validateDeleteChange(id);
			PlayerDAO playerDAO = new PlayerDAO();
			playerDAO.changeDelete(id);

		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws ValidationException, ServiceException
	 */
	public static int findByPhoneNumber(long phoneNumber) throws ValidationException, ServiceException {
		int player;
		try {
			PlayerValidator.validatePhoneNumber(phoneNumber);

			PlayerDAO dao = new PlayerDAO();
			player = dao.findByPhoneNumber(phoneNumber);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return player;
	}

	public static int logIn(long phoneNumber, String password) throws ValidationException, ServiceException {
		System.out.println("inside");
		int player = 0;
		
		PlayerDAO dao = new PlayerDAO();
		try {
			PlayerValidator.validatePhoneNumber(phoneNumber);
			StringUtil.rejectIfInvalidString(password, "Password");

			String daoPassword = dao.getPasswordByPhonenumber(phoneNumber);

			int firstIndex = daoPassword.indexOf('$'); 
			int secondIndex = daoPassword.indexOf('$', firstIndex + 1); 
																		

			if (firstIndex != -1 && secondIndex != -1) {
				String salt = daoPassword.substring(firstIndex + 1, secondIndex); 
				String password1 = SecureUtils.checkPass(password, salt);
				
				String finalpassword = "$"+salt+"$"+password1;
				
				player = dao.logIn(phoneNumber, finalpassword);
			} else {
				System.out.println("The input string does not contain the expected format.");
			}

		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return player;
	}

	public static Player findById(int id) throws ValidationException, ServiceException {
		Player player;
		try {
			PlayerValidator.validateId(id, "Player");

			PlayerDAO dao = new PlayerDAO();
			player = dao.findById(id);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return player;
	}

}
