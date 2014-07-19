package ru.sms4j.methods;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ru.sms4j.common.Auth;
import ru.sms4j.common.AuthParameters;
import ru.sms4j.common.QueryString;
import ru.sms4j.common.Request;

public class Sms extends Request {

	private QueryString querySend = new QueryString();
	private QueryString queryCost = new QueryString();
	private QueryString queryStatus = new QueryString();

	public Sms(AuthParameters auth) throws MalformedURLException, IOException,
			NoSuchAlgorithmException {
		querySend = new Auth().check(auth, querySend);
		queryCost = new Auth().check(auth, queryCost);
		queryStatus = new Auth().check(auth, queryStatus);
	}

	/**
	 * ��������� �������� ��� ��������� ������ ��� ���������� �����������.
	 * 
	 * @return ������ �������������� ������������ ���������.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<String> send() throws MalformedURLException, IOException {
		request(querySend, "http://sms.ru/sms/send");

		List<String> ids = new ArrayList<>();
		for (String id : getResponse())
			if (id.length() > 3) {
				boolean isString = false;
				for (char c : id.toCharArray()) {
					if (Pattern.matches("[A-Za-z]", Character.toString(c))) {
						isString = true;
						break;
					}
				}
				if (!isString)
					ids.add(id);
			}

		return ids;
	}

	/**
	 * �������� ������� ������������� ���������.
	 * 
	 * @param id
	 *            - ������������� ���������, ���������� ��� ������������� ������
	 *            sms/send
	 * @return ������ ������������� ���������
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public int status(String id) throws MalformedURLException, IOException {
		queryStatus.add("id", id);
		request(queryStatus, "http://sms.ru/sms/status");

		return getServerResponse();
	}

	/**
	 * ���������� ��������� ��������� �� ��������� ����� � ���������� ���������,
	 * ����������� ��� ��� ��������.
	 * 
	 * @param to
	 *            - ����� �������� ����������
	 * @param text
	 *            - ����� ��������� � ��������� UTF-8. ���� ����� �� ������, ��
	 *            ������������ ��������� 1 ���������. ���� ����� ������, ��
	 *            ������������ ���������, ������������ �� ����� ���������.
	 * @param translit
	 *            - ��������� ��� ������� ������� � ���������.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String cost(String to, String text, String translit)
			throws MalformedURLException, IOException {
		queryCost.add("to", to);
		queryCost.add("text", text);
		queryCost.add("translit", translit);
		request(queryCost, "http://sms.ru/sms/cost");

		return getResponse()[1];
	}

	/**
	 * ����� �������� ���������� (���� ��������� �������, <br>
	 * ����� ������� � �� 100 ���� �� ���� ������)
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms to(String to) throws UnsupportedEncodingException {
		querySend.add("to", to);
		return this;
	}

	/**
	 * ����� ���������
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms text(String text) throws UnsupportedEncodingException {
		querySend.add("text", text);
		return this;
	}

	/**
	 * ��� ����������� (������ ���� ����������� � ��������������).<br>
	 * ���� �� ���������, � �������� ����������� ����� ������ ��� �����.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms from(String from) throws UnsupportedEncodingException {
		querySend.add("from", from);
		return this;
	}

	/**
	 * ���� �� ���������� � ����������� ���������, ������� ���� �������� <br>
	 * � ������� � ��������� �������� �� ��������� ������������ ���������.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms partner_id(String partner_id)
			throws UnsupportedEncodingException {
		querySend.add("partner_id", partner_id);
		return this;
	}

	/**
	 * ���� ��� ����� ���������� ��������, �� ������� ����� ��������. <br>
	 * ����������� � ������� UNIX TIME (������: 1280307978). <br>
	 * ������ ���� �� ������ 7 ���� � ������� ������ �������. <br>
	 * ���� ����� ������ �������� �������, ��������� ������������ �����������.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms time(String time) throws UnsupportedEncodingException {
		querySend.add("time", time);
		return this;
	}

	/**
	 * ��������� ��� ������� ������� � ���������.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms translit(String translit) throws UnsupportedEncodingException {
		querySend.add("translit", translit);
		return this;
	}

	/**
	 * ��������� �������� ��������� ��� ������������ ����� �������� �� <br>
	 * ������������ ��������� ������� �������. ��� ���� ���� ��������� ��
	 * ������������ � ������ �� �����������.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms test(String test) throws UnsupportedEncodingException {
		querySend.add("test", test);
		return this;
	}

	/**
	 * ���� �� ������ � ����� ������� ��������� ������ ��������� <br>
	 * �� ��������� �������, �� �������������� ���� ���������� (�� 100 ���������
	 * �� 1 ������).<br>
	 * � ���� ������, ��������� to � text ������������ �� �����:<br>
	 * ������ ��������� ���������� � ���� multi[�����
	 * ����������]=�����&multi[����� ����������]=�����<br>
	 * ��������: <br>
	 * http://sms.ru/sms/send?api_id=api_id&multi[phone]=hello+world&multi[
	 * ������ �����]=world+not+hello
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms multi(String multi) throws UnsupportedEncodingException {
		querySend.add("multi", multi);
		return this;
	}
}
