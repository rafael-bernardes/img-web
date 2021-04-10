package br.gov.bom_destino.img_web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	public String login() {
		Faces.setSessionAttribute("usuario-logado", login);
		return "/pages/home";
	}
	
	public String logout() {
		Faces.invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}