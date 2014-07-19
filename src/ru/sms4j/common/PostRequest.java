package ru.sms4j.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PostRequest extends StreamToString {

	public PostRequest() {
	}

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	private String response;

	public void setQuery(QueryString request) throws MalformedURLException,
			IOException {
		// устанавливаем соединение
		URLConnection conn = new URL(getUrl()).openConnection();
		// мы будем писать POST данные в out stream
		conn.setDoOutput(true);
		// создаем поток
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
				"ASCII");
		out.write(request.toString());
		out.write("\r\n");
		out.flush();
		out.close();

		// читаем то, что отдал нам сервер
		this.response = readStreamToString(conn.getInputStream(), "UTF-8");
	}

	public String getResponse() {
		return this.response;
	}
}
