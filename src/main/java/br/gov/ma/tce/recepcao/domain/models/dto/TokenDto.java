package br.gov.ma.tce.recepcao.domain.models.dto;

public class TokenDto {
	
	
	private String token;
	private String string;
	
	
	
	
	
	
	public TokenDto(String token, String string) {
		super();
		this.token = token;
		this.string = string;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	
	
	

}
