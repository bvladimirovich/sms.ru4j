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
 * Класс получения информации о состоянии баланса, дневного лимита, списка отправителей.
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
	 * Получение состояния баланса.
	 * @return Состояния баланса.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String balance() throws MalformedURLException, IOException {
		request(queryString, "http://sms.ru/my/balance");

		return getResponse()[1];
	}

	/**
	 * Получение текущего состояния вашего дневного лимита.
	 * @return Состояния вашего дневного лимита.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String limit() throws MalformedURLException, IOException {
		request(queryString, "http://sms.ru/my/limit");

		return getResponse()[1];
	}

	/**
	 * Получение списка отправителей.
	 * @return Список отправителей.
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
