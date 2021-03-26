package br.com.bom_destino.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import br.com.bom_destino.utils.PropertiesUtil;


@ManagedBean(name = "atualizacao")
public class AtualizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCidade;
	
	public void atualizarDados() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		try {
			WebTarget target = client.target(PropertiesUtil.obterURI("gateway-api")).path("dados-geograficos");
			
			Response response = target.request().post(Entity.entity(nomeCidade, MediaType.APPLICATION_JSON));
			
			if(!Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar dados geográficos.", ""));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados geográficos atualizados com sucesso", ""));
			}
			
		} catch (IllegalArgumentException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar dados geográficos", e.getMessage()));
		} catch (NullPointerException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar dados geográficos", e.getMessage()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar dados geográficos", e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar dados geográficos", e.getMessage()));
		}
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
}
