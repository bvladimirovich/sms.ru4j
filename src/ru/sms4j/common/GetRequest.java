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
		// устанавливаем соединение
		URLConnection conn = new URL(url).openConnection();
		// читаем то, что отдал нам сервер
		return readStreamToString(conn.getInputStream(), "UTF-8");
	}

}
