package com;

public class Usuario {
	
	private String username;
	private String password;
	
	public Usuario() {
		username = "admin";
		password = "pass123";
	}
	
	public Usuario(String user, String pass) {
		
		this.username = user;
		this.password = pass;
		
	}
	
	public String getUsername() {
		
		return username;
		
	}
	
	public void setUsername(String username) {
		
		this.username = username;
		
	}
	
	public String getPassword() {
		
		return password;
		
	}
	
	public void setPassword(String password) {
		
		this.password = password;
		
	}
	
	public boolean validarUsuario(String user, String pass) {
		
		if(user.equals(username) && pass.equals(password)) {
			return true;
		}
		
		return false;
		
	}
	
}
