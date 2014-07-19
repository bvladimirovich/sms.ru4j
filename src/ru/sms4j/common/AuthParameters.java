package ru.sms4j.common;

public class AuthParameters {

	public AuthParameters() {
	}

	private String apiId;

	/**
	 * Авторизацию по вашему уникальному ключу (api_id). Этот способ авторизации
	 * - самый удобный.
	 * 
	 * @param apiId
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiId() {
		return this.apiId;
	}

	private String password;

	/**
	 * Пароль.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	private String login;

	/**
	 * Ваш номер телефона (логин).
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	private boolean authWithToken;

	public void setAuthWithToken(boolean f) {
		this.authWithToken = f;
	}

	public boolean getAuthWithToken() {
		return this.authWithToken;
	}

	private boolean authWithoutToken;

	public void setAuthWithoutToken(boolean f) {
		this.authWithoutToken = f;
	}

	public boolean getAuthWithoutToken() {
		return this.authWithoutToken;
	}

	private boolean authWithApiId;

	public void setAuthWithApiId(boolean f) {
		this.authWithApiId = f;
	}

	public boolean getAuthWithApiId() {
		return this.authWithApiId;
	}
}
