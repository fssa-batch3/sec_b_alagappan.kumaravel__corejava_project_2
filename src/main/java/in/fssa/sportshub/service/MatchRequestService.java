package in.fssa.sportshub.service;

import java.util.HashSet;
import java.util.Set;

import in.fssa.sportshub.dao.MatchRequestDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.MatchRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.validator.AddressValidator;
import in.fssa.sportshub.validator.MatchRequestValidator;
import in.fssa.sportshub.validator.PlayerValidator;
import in.fssa.sportshub.validator.TeamMemberValidator;
import in.fssa.sportshub.validator.TeamValidator;

public class MatchRequestService {
	public void create(MatchRequest matchRequest, int captainId) throws ValidationException, ServiceException{
		try {
		MatchRequestValidator.validateCreate(matchRequest, captainId);
		
		MatchRequestDAO matchReqDAO = new MatchRequestDAO();
		
		
		if(matchRequest.getOpponentType().getDisplayName() == "1") {
			boolean checkTeamExist = TeamValidator.checkTeamExist(matchRequest.getToTeam());
			if(!checkTeamExist) {
				throw new ValidationException("To team not exist");
			}
			matchReqDAO.createToTeam(matchRequest);
			System.out.println("match created to team");
		}
		else if(matchRequest.getOpponentType().getDisplayName() == "2") {
			boolean checkAddressExist = AddressValidator.checkAddressExist(matchRequest.getAddressId());
			if(!checkAddressExist) {
				throw new ValidationException("Address not exist");
			}
			matchReqDAO.createToArea(matchRequest);
			System.out.println("match created to area");
		}
		
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public Set<MatchRequest> listAllOpenRequest() throws PersistanceException{
		MatchRequestDAO matchReqDAO = new MatchRequestDAO();
		return matchReqDAO.getAllOpenRequest();
	}
	
	// invitation page 
	public Set<MatchRequestDTO> getAllMyMatchRequest(int createdById ,int toTeamId, int addressId) throws ServiceException, ValidationException{
		
		try {
		MatchRequestValidator.validateId(addressId, "addressId");
		MatchRequestValidator.validateId(toTeamId, "toTeamId");
		MatchRequestValidator.validateId(createdById, "createdById");
		
		MatchRequestDAO matchReqDAO = new MatchRequestDAO();
		return matchReqDAO.getAllMyMatchRequest(createdById ,toTeamId, addressId);
		}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	// response page
	public Set<MatchRequestDTO> listOfMyMatchInvitation(int createdById) throws ServiceException, ValidationException{
		
		try {
		MatchRequestValidator.validateId(createdById, "teamId");
		
		MatchRequestDAO matchReqDAO = new MatchRequestDAO();
		
		Set<MatchRequestDTO> data1 = matchReqDAO.listOfMyMatchInvitationAccepted(createdById);
		Set<MatchRequestDTO> data2 = matchReqDAO.listOfMyMatchInvitationNotAcceptedToTeam(createdById);
		Set<MatchRequestDTO> data3 = matchReqDAO.listOfMyMatchInvitationNotAcceptedToArea(createdById);
		Set<MatchRequestDTO> mergedData = new HashSet<>();

		mergedData.addAll(data1);
		mergedData.addAll(data2);
		mergedData.addAll(data3);
		
		return mergedData;
		}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
		
	}
	
	public void updateAccept(int toTeamCaptainRelationId, int matchRequestId) throws ServiceException, ValidationException {		
		try {
			MatchRequestValidator.validateId(toTeamCaptainRelationId, "toTeamCaptainRelationId");
			
			MatchRequestDAO matchReqDAO = new MatchRequestDAO();
			matchReqDAO.updateAccept(toTeamCaptainRelationId, matchRequestId);
			}catch(PersistanceException e) {
		 		e.printStackTrace();
				throw new ServiceException(e.getMessage());
		 	}
		
	}
	
}
