package com;

public class Usuario {

	private String username;
	private String password;

	// Constructor que crea un usuario con un usuario y password por defecto
	public Usuario() {
		username = "admin";
		password = "pass123";
	}

	// Constructor que crea un usuario con un usuario y password especifico
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
	// Metodo encargar de validar el objeto actual datos pasados por parametros
	// Utilizado para verificar la igualdad de usuario y password
	public boolean validarUsuario(String user, String pass) {

		if (user.equals(username) && pass.equals(password)) {
			return true;
		}

		return false;

	}

}
