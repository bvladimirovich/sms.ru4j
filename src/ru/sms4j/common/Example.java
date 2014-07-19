package ru.sms4j.common;

import java.util.List;

import ru.sms4j.methods.My;
import ru.sms4j.methods.Sms;
import ru.sms4j.methods.Stoplist;

public class Example {

	public static void main(String[] args) throws Exception {
		
		String apiId = "your apiId";
		// or
//		String login = "your login";
//		String password = "your password";
		
		AuthParameters auth = new AuthParameters();
		// 1
		auth.setAuthWithApiId(true);
		auth.setApiId(apiId);
		// or 2
//		auth.setAuthWithoutToken(true);
		// or 3
//		auth.setAuthWithToken(true);
//		auth.setLogin(login);
//		auth.setPassword(password);
		
		Sms sms = new Sms(auth);
		List<String> ids = sms.to("phone/phones").text("message").send();
		String cost = sms.cost("phone", "message", "translit 1 or 0");
		int status = sms.status(ids.get(0));
		
		for (String id : ids) {
			System.out.println("id: " + id);
		}
		System.out.println("cost: " + cost);
		System.out.println("status: " + status);
		
		My my = new My(auth);
		String balance = my.balance();
		String limit = my.limit();
		List<String> senders = my.senders();
		
		System.out.println("balance: " + balance);
		System.out.println("limit: " + limit);
		System.out.println("senders: " + senders);
		
		Stoplist stoplist = new Stoplist(auth);
		stoplist.add("phone", "reason");

		List<String> get = stoplist.get();
		for (String phone : get) {
			stoplist.del(phone);
		}
		get = stoplist.get();
		
		System.out.println("stoplist.get: " + get);
	}

}
