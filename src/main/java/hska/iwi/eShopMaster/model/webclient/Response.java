package hska.iwi.eShopMaster.model.webclient;

public class Response {

	private final int statusCode;
	private final String body;

	public Response(int statusCode, String body) {
		this.statusCode = statusCode;
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}
}
