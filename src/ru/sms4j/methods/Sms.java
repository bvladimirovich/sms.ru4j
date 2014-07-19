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
	 * Совершает отправку СМС сообщения одному или нескольким получателям.
	 * 
	 * @return Список идетификаторов отправленных сообщений.
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
	 * Проверка статуса отправленного сообщения.
	 * 
	 * @param id
	 *            - Идентификатор сообщения, полученный при использовании метода
	 *            sms/send
	 * @return Статус отправленного сообщения
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public int status(String id) throws MalformedURLException, IOException {
		queryStatus.add("id", id);
		request(queryStatus, "http://sms.ru/sms/status");

		return getServerResponse();
	}

	/**
	 * Возвращает стоимость сообщения на указанный номер и количество сообщений,
	 * необходимых для его отправки.
	 * 
	 * @param to
	 *            - Номер телефона получателя
	 * @param text
	 *            - Текст сообщения в кодировке UTF-8. Если текст не введен, то
	 *            возвращается стоимость 1 сообщения. Если текст введен, то
	 *            возвращается стоимость, рассчитанная по длине сообщения.
	 * @param translit
	 *            - Переводит все русские символы в латинские.
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
	 * Номер телефона получателя (либо несколько номеров, <br>
	 * через запятую — до 100 штук за один запрос)
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms to(String to) throws UnsupportedEncodingException {
		querySend.add("to", to);
		return this;
	}

	/**
	 * Текст сообщения
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms text(String text) throws UnsupportedEncodingException {
		querySend.add("text", text);
		return this;
	}

	/**
	 * Имя отправителя (должно быть согласовано с администрацией).<br>
	 * Если не заполнено, в качестве отправителя будет указан ваш номер.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms from(String from) throws UnsupportedEncodingException {
		querySend.add("from", from);
		return this;
	}

	/**
	 * Если вы участвуете в партнерской программе, укажите этот параметр <br>
	 * в запросе и получайте проценты от стоимости отправленных сообщений.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms partner_id(String partner_id)
			throws UnsupportedEncodingException {
		querySend.add("partner_id", partner_id);
		return this;
	}

	/**
	 * Если вам нужна отложенная отправка, то укажите время отправки. <br>
	 * Указывается в формате UNIX TIME (пример: 1280307978). <br>
	 * Должно быть не больше 7 дней с момента подачи запроса. <br>
	 * Если время меньше текущего времени, сообщение отправляется моментально.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms time(String time) throws UnsupportedEncodingException {
		querySend.add("time", time);
		return this;
	}

	/**
	 * Переводит все русские символы в латинские.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms translit(String translit) throws UnsupportedEncodingException {
		querySend.add("translit", translit);
		return this;
	}

	/**
	 * Имитирует отправку сообщения для тестирования ваших программ на <br>
	 * правильность обработки ответов сервера. При этом само сообщение не
	 * отправляется и баланс не расходуется.
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms test(String test) throws UnsupportedEncodingException {
		querySend.add("test", test);
		return this;
	}

	/**
	 * Если вы хотите в одном запросе отправить разные сообщения <br>
	 * на несколько номеров, то воспользуйтесь этим параметром (до 100 сообщений
	 * за 1 запрос).<br>
	 * В этом случае, параметры to и text использовать не нужно:<br>
	 * каждое сообщение передается в виде multi[номер
	 * получателя]=текст&multi[номер получателя]=текст<br>
	 * Например: <br>
	 * http://sms.ru/sms/send?api_id=api_id&multi[phone]=hello+world&multi[
	 * другой номер]=world+not+hello
	 * 
	 * @throws UnsupportedEncodingException
	 * */
	public Sms multi(String multi) throws UnsupportedEncodingException {
		querySend.add("multi", multi);
		return this;
	}
}
