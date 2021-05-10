package br.gov.ma.tce.recepcao.domain.models;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;


public class FiltroVisita {

	private String cpf;

	private String dataSaida;

	private String dataEntrada;

	private Integer setorId;
	
	private DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");


	public Specification<Visita> toSpec(){
		

		
		if(this.cpf != null) {
			System.out.println(cpf);
		}
		
		if(this.dataEntrada != null) {
			System.out.println(dataEntrada);
		}
		
		return (root, query, builder) -> {

			List<Predicate> predicados = new ArrayList<Predicate>();

			if(StringUtils.hasText(cpf)) {

				predicados.add(builder.equal(root.<String>get("cpf"),this.cpf.trim()));
			}
			if(setorId != null) {
				predicados.add(builder.equal(root.<Integer>get("setorId"),this.setorId));

			}
			if(  dataEntrada != null) {
				predicados.add(builder.greaterThanOrEqualTo(root.<Date>get("horaEntrada"), parseDate(this.dataEntrada)));

			}

			if(  dataSaida != null) {
				predicados.add(builder.lessThanOrEqualTo(root.<Date>get("horaEntrada"),parseDate(this.dataSaida)));

			}
			if(predicados.isEmpty()) {
				return null;
			}else {
				return builder.and(predicados.toArray(new Predicate[0]));
			}

		};

	}
	
	
	private Date parseDate(String dateStr) {
		
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getDataSaida() {
		return dataSaida;
	}


	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}


	public String getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public Integer getSetorId() {
		return setorId;
	}


	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}


	



}
