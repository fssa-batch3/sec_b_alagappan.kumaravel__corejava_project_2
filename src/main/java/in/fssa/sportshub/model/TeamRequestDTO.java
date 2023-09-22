package in.fssa.sportshub.model;

public class TeamRequestDTO extends Team{

	private int requestStatus;
	private int requestId;
	private String createdTime;

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "TeamRequestDTO [requestStatus=" + requestStatus + ", requestId=" + requestId + ", createdTime="
				+ createdTime + "]";
	}


	
	
}
