package ru.sms4j.common;

public abstract class ServerResponses {
	private static int res;

	public static boolean check(int i) throws Exception {
		boolean f = false;

		switch (i) {
		case 100:
			f = true;
			res = i;
			break;
		case 200:
			f = false;
			throw new Exception("Неправильный api_id");
		case 201:
			f = false;
			throw new Exception("Не хватает средств на лицевом счету");
		case 202:
			f = false;
			throw new Exception("Неправильно указан получатель");
		case 203:
			f = false;
			throw new Exception("Нет текста сообщения");
		case 204:
			f = false;
			throw new Exception(
					"�?мя отправителя не согласовано с администрацией");
		case 205:
			f = false;
			throw new Exception("Сообщение слишком длинное (превышает 8 СМС)");
		case 206:
			f = false;
			throw new Exception(
					"Будет превышен или уже превышен дневной лимит на отправку сообщений");
		case 207:
			f = false;
			throw new Exception(
					"На этот номер (или один из номеров) нельзя отправлять сообщения, либо указано более 100 номеров в списке получателей");
		case 208:
			f = false;
			throw new Exception("Параметр time указан неправильно");
		case 209:
			f = false;
			throw new Exception(
					"Вы добавили этот номер (или один из номеров) в стоп-лист");
		case 210:
			f = false;
			throw new Exception(
					"�?спользуется GET, где необходимо использовать POST");
		case 211:
			f = false;
			throw new Exception("Метод не найден");
		case 212:
			f = false;
			throw new Exception(
					"Текст сообщения необходимо передать в кодировке UTF-8 (вы передали в другой кодировке)");
		case 220:
			f = false;
			throw new Exception(
					"Сервис временно недоступен, попробуйте чуть позже");
		case 230:
			f = false;
			throw new Exception(
					"Сообщение не принято к отправке, так как на один номер в день нельзя отправлять более 60 сообщений");
		case 300:
			f = false;
			throw new Exception(
					"Неправильный token (возможно истек срок действия, либо ваш IP изменился)");
		case 301:
			f = false;
			throw new Exception(
					"Неправильный пароль, либо пользователь не найден");
		case 302:
			f = false;
			throw new Exception(
					"Пользователь авторизован, но аккаунт не подтвержден (пользователь не ввел код, присланный в регистрационной смс)");
		default:
			f = false;
		}

		return f;
	}

	public static int getResponse() {
		return res;
	}
}
