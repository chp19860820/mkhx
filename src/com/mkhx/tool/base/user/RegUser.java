package com.mkhx.tool.base.user;

public class RegUser extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.userInfoPath;
	protected String requestURL = "http://pp.fantasytoyou.com/pp/httpService.do";
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public RegUser(String cookie) {
		this.cookie = cookie;
	}

	public RegUser(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public void request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}

	public void setUser(String user, String password) {
		this.requestBody = "{\"serviceName\":\"activeGameBase64Json\",\"callPara\":\"JTdCJTIydXNlck5hbWUlMjIlM0ElMjJhaGJjZDglMjIlMkMlMjJlbWFpbCUyMiUzQSUyMiUyMiUyQyUyMnVzZXJQYXNzd29yZCUyMiUzQSUyMnp6enolMjIlMkMlMjJnYW1lTmFtZSUyMiUzQSUyMkNBUkQtQU5EUk9JRC1DSFMlMjIlMkMlMjJ1ZGlkJTIyJTNBJTIyNEIlM0EyMCUzQTREJTNBQ0MlM0ExOSUzQUE1JTIyJTJDJTIyY2xpZW50VHlwZSUyMiUzQSUyMmZsYXNoJTIyJTJDJTIycmVsZWFzZUNoYW5uZWwlMjIlM0ElMjIlMjIlMkMlMjJsb2NhbGUlMjIlM0ElMjJjaHMlMjIlMkMlMjJmcmllbmRDb2RlJTIyJTNBJTIyJTIyJTJDJTIyc2VsR3NJZCUyMiUzQTI0NSUyQyUyMmNhcHRjaGFDaGFsbGVuZ2UlMjIlM0ElMjIwM0FISl9WdXNlQUlXTjg4R1Q1RnFUZ0Y2ZVlUeDFSU29kMEo1M3RULXdyVmFhYU40VE14cVlHTldYczdCb1hxR2hvSzVEQWtlem5KbVp0RThoQVQ5azI1RzlOLW01bkVxODJVWGFodUVrXzh1dEN0SmRmSkNMZ2Viekx1Z0pUc0tzTVAyWG1LeGlENTE3Z0lESUgwUFRtRFBibUJIbTBxZ0hhMUh3VXV0VWpmcjBKQ2ZyRFVvQUlFNCUyMiUyQyUyMmNhcHRjaGFSZXNwb25zZSUyMiUzQSUyMmVhc3lpdGVtYmxlbSUyMiU3RA==\"}";
	}
}
