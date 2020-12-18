package br.gov.ma.tce.recepcao.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class FiltroUsuario {
	
	private String nome;
	private String login;
	

	public Specification<Usuario> toSpec(){
		
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<Predicate>();
			
			if(StringUtils.hasText(nome)) {
				predicados.add(builder.equal(root.<String>get("nome"), this.nome));
			}
			if(StringUtils.hasText(login)) {
				predicados.add(builder.equal(root.<String>get("login"), this.login));
			}
			if(predicados.isEmpty()) {
				return null;
			}else {
				return builder.and(predicados.toArray(new Predicate[0]));
			}
			
		};
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
