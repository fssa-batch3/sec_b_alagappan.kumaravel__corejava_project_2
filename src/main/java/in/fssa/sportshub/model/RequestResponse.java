package in.fssa.sportshub.model;

public class RequestResponse {
	private int id;
	private int requestId;
	private int fromTeamId;
	private boolean statusOfResponse;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getFromTeamId() {
		return fromTeamId;
	}
	public void setFromTeamId(int fromTeamId) {
		this.fromTeamId = fromTeamId;
	}
	public boolean isStatusOfResponse() {
		return statusOfResponse;
	}
	public void setStatusOfResponse(boolean statusOfResponse) {
		this.statusOfResponse = statusOfResponse;
	}
	@Override
	public String toString() {
		return "RequestResponse [id=" + id + ", requestId=" + requestId + ", fromTeamId=" + fromTeamId
				+ ", statusOfResponse=" + statusOfResponse + "]";
	}
	
}
