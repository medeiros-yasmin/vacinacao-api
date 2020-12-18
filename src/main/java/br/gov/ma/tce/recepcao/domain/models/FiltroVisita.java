package br.gov.ma.tce.recepcao.domain.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;



public class FiltroVisita {

	private String cpf;

	private Date horaSaida;

	private Date horaEntrada;

	private Integer setorId;


	public Specification<Visita> toSpec(){

		return (root, query, builder) -> {

			List<Predicate> predicados = new ArrayList<Predicate>();

			if(StringUtils.hasText(cpf)) {

				predicados.add(builder.equal(root.<String>get("cpf"),this.cpf));
			}
			if(setorId != null) {
				predicados.add(builder.equal(root.<Integer>get("setorId"),this.setorId));

			}
			if(  horaEntrada != null) {
				predicados.add(builder.greaterThanOrEqualTo(root.<Date>get("horaEntrada"), this.horaEntrada));

			}

			if(  horaSaida != null) {
				predicados.add(builder.lessThanOrEqualTo(root.<Date>get("horaSaida"),this.horaSaida));

			}
			if(predicados.isEmpty()) {
				return null;
			}else {
				return builder.and(predicados.toArray(new Predicate[0]));
			}

		};

	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Integer getSetorId() {
		return setorId;
	}

	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}



}
