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
 * ����� ������ �� ����-������
 * @author Boris
 *
 */
public class Stoplist extends Request {

	private QueryString queryAdd = new QueryString();
	private QueryString queryDel = new QueryString();
	private QueryString queryGet = new QueryString();

	public Stoplist(AuthParameters auth) throws MalformedURLException,
			NoSuchAlgorithmException, IOException {
		queryAdd = new Auth().check(auth, queryAdd);
		queryDel = new Auth().check(auth, queryDel);
		queryGet = new Auth().check(auth, queryGet);
	}

	/**
	 * �� ������, ����������� � ��������, �� ������������ ��������� (� �� ��� ��
	 * ����������� ������).
	 * 
	 * @param stoplist_phone
	 *            - ����� ��������
	 * @param stoplist_text
	 *            - �������� ������� ���������� � ����-����
	 * @return 100 ����� �������� � �������� <br>
	 *         202 ����� �������� � ������������ �������
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String add(String stoplist_phone, String stoplist_text)
			throws MalformedURLException, IOException {
		queryAdd.add("stoplist_phone", stoplist_phone);
		queryAdd.add("stoplist_text", stoplist_text);
		request(queryAdd, "http://sms.ru/stoplist/add");

		return getResponse()[0];
	}

	/**
	 * ������� ���� ����� �� ���������.
	 * 
	 * @param stoplist_phone
	 *            - ����� ��������
	 * @return 100 ����� ������ �� ��������� <br>
	 *         202 ����� �������� � ������������ �������
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String del(String stoplist_phone) throws MalformedURLException,
			IOException {
		queryDel.add("stoplist_phone", stoplist_phone);
		request(queryDel, "http://sms.ru/stoplist/del");

		return getResponse()[0];
	}

	/**
	 * ���� ����-���� ����������� � ������� �����;���������� �� ���������
	 * ��������
	 * 
	 * @return ������ ������� ����������� � ����-�����.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<String> get() throws MalformedURLException, IOException {
		request(queryGet, "http://sms.ru/stoplist/get");

		List<String> senders = new ArrayList<>();
		for (int i = 1; i < getResponse().length; i++) {
			senders.add(getResponse()[i]);
		}
		return senders;
	}
}
