package ru.sms4j.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Auth {

	public Auth() {
	}

	public QueryString check(AuthParameters auth, QueryString queryString)
			throws MalformedURLException, IOException, NoSuchAlgorithmException {
		if (auth.getAuthWithApiId() && auth.getApiId() != null) {
			queryString.add("api_id", auth.getApiId());
		} else if (auth.getAuthWithoutToken() && auth.getLogin() != null
				&& auth.getPassword() != null) {
			queryString.add("login", auth.getLogin()).add("password",
					auth.getPassword());
		} else if (auth.getAuthWithToken() && auth.getLogin() != null
				&& auth.getPassword() != null) {
			String token = new GetRequest()
					.getResponse("http://sms.ru/auth/get_token");
			String sha512 = toSha512(auth.getPassword(), token);
			queryString.add("login", auth.getLogin()).add("token", token)
					.add("sha512", sha512);
		}

		return queryString;
	}

	/**
	 * Java SHA Hashing Example -
	 * http://www.mkyong.com/java/java-sha-hashing-example/
	 * */
	private String toSha512(String password, String token)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(password.concat(token).getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}
}
