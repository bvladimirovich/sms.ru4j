package ru.sms4j.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetRequest extends StreamToString {

	public GetRequest() {
	}

	public String getResponse(String url) throws MalformedURLException,
			IOException {
		// ������������� ����������
		URLConnection conn = new URL(url).openConnection();
		// ������ ��, ��� ����� ��� ������
		return readStreamToString(conn.getInputStream(), "UTF-8");
	}

}
