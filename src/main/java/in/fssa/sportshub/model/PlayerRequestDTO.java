package in.fssa.sportshub.model;

public class PlayerRequestDTO extends Player{
	private int requestId;
	private String createdTime;

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "PlayerRequestDTO [requestId=" + requestId + ", createdTime=" + createdTime + "]";
	}
	
	
}
