package in.fssa.sportshub.model;

public class PlayerRequestDTO extends Player{
	private int requestId;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "PlayerRequestDTO [requestId=" + requestId + "]";
	}
	
	
}
