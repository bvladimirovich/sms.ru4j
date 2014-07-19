package ru.sms4j.methods;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import ru.sms4j.common.Auth;
import ru.sms4j.common.AuthParameters;
import ru.sms4j.common.QueryString;
import ru.sms4j.common.Request;

/**
 * ����� ��������� ���������� � ��������� �������, �������� ������, ������ ������������.
 * @author Boris
 *
 */
public class My extends Request {

	private QueryString queryString = new QueryString();

	public My(AuthParameters auth) throws MalformedURLException,
			NoSuchAlgorithmException, IOException {
		queryString = new Auth().check(auth, queryString);
	}

	/**
	 * ��������� ��������� �������.
	 * @return ��������� �������.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String balance() throws MalformedURLException, IOException {
		request(queryString, "http://sms.ru/my/balance");

		return getResponse()[1];
	}

	/**
	 * ��������� �������� ��������� ������ �������� ������.
	 * @return ��������� ������ �������� ������.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String limit() throws MalformedURLException, IOException {
		request(queryString, "http://sms.ru/my/limit");

		return getResponse()[1];
	}

	/**
	 * ��������� ������ ������������.
	 * @return ������ ������������.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<String> senders() throws MalformedURLException, IOException {
		request(queryString, "http://sms.ru/my/senders");

		List<String> senders = new ArrayList<>();
		for (int i = 1; i < getResponse().length; i++) {
			senders.add(getResponse()[i]);
		}
		return senders;
	}
}
