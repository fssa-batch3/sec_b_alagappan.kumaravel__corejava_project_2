package in.fssa.sportshub.service;

import in.fssa.sportshub.dao.RequestResponseDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.validator.RequestResponseValidator;
public class RequestResponseService {
	// this invitation particular person not validate.
	
	public void accept(int toTeamCaptainRelationId, int matchRequestId) throws ValidationException, ServiceException{
		try {
		RequestResponseValidator.validateAccept(toTeamCaptainRelationId, matchRequestId);
		
		RequestResponseDAO requsetResponseDAO = new RequestResponseDAO();
		
		TeamMemberService teamMemberServ = new TeamMemberService();
		
		TeamMember teamMember = teamMemberServ.findById(toTeamCaptainRelationId);
		
		requsetResponseDAO.accept(teamMember.getTeamId(), matchRequestId);
		System.out.println("working");
		MatchRequestService matchReqService = new MatchRequestService();
		
		matchReqService.updateAccept(teamMember.getTeamId(), matchRequestId);
		
		
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public void reject(int toTeamCaptainRelationId, int matchRequestId) throws ValidationException, ServiceException{
		try {
		RequestResponseValidator.validateAccept(toTeamCaptainRelationId, matchRequestId);
		
		RequestResponseDAO requsetResponseDAO = new RequestResponseDAO();
		
		MatchRequestService matchRequestServ = new MatchRequestService();
		
		TeamMemberService teamMemberServ = new TeamMemberService();
		
		TeamMember teamMember = teamMemberServ.findById(toTeamCaptainRelationId);
		
		MatchRequestDTO matchRequest = matchRequestServ.findById(matchRequestId);
		
		requsetResponseDAO.reject(teamMember.getTeamId(), matchRequestId);
		
		if(matchRequest.getOpponentType().getDisplayName() == "1") {
			matchRequestServ.updateReject(toTeamCaptainRelationId, matchRequestId);
		}
		
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}	
	
}
