package ru.sms4j.common;

import java.io.IOException;
import java.net.MalformedURLException;

public abstract class Request {

	private String response;

	protected void request(QueryString queryString, String url)
			throws MalformedURLException, IOException {
		PostRequest request = new PostRequest();
		request.setUrl(url.toString());
		request.setQuery(queryString);
		response = request.getResponse();

		try {
			ServerResponses.check(getServerResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String[] getResponse() {
		return this.response.split("\n");
	}

	public int getServerResponse() {
		return Integer.parseInt(getResponse()[0]);
	}
}
