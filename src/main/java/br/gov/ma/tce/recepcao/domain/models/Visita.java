package br.gov.ma.tce.recepcao.domain.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(schema = "sisrep", name = "visita")
public class Visita implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name = "sisrep.seq_visita", sequenceName = "sisrep.seq_visita", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sisrep.seq_visita")
	@Column(name = "visita_id")
	private Integer visitaId;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "hora_entrada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEntrada;

	@Column(name = "hora_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSaida;

	@Column(name = "objetos_pessoais")
	private String objetosPessoais;

	@Column(name = "usuario_cadastro")
	private Integer usuarioCadastroId;

	@Column(name = "setorId")
	private Integer setorId;

	@Column(name = "observacao")
	private String observacao;

	public Integer getVisitaId() {
		return visitaId;
	}

	public void setVisitaId(Integer visitaId) {
		this.visitaId = visitaId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getObjetosPessoais() {
		return objetosPessoais;
	}

	public void setObjetosPessoais(String objetosPessoais) {
		this.objetosPessoais = objetosPessoais;
	}

	public Integer getUsuarioCadastroId() {
		return usuarioCadastroId;
	}

	public void setUsuarioCadastroId(Integer usuarioCadastroId) {
		this.usuarioCadastroId = usuarioCadastroId;
	}

	public Integer getSetorId() {
		return setorId;
	}

	public void setSetorId(Integer setorId) {
		this.setorId = setorId;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

	
	/*
	 * @Transient public String getNomePessoaFisica(){
	 * 
	 * 
	 * try { if(this.cpf!=null){
	 * 
	 * PessoaFisicaFacadeBean pessoaFisicaFacadeBean;
	 * 
	 * InitialContext ctx = new InitialContext();
	 * 
	 * pessoaFisicaFacadeBean = (PessoaFisicaFacadeBean) ctx.lookup(
	 * "java:global/sisrep_ear/pessoa_rf_server/PessoaFisicaFacadeBean!br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean"
	 * );
	 * 
	 * PessoaFisica pf = pessoaFisicaFacadeBean.findByCpf(this.cpf);
	 * 
	 * return pf.getNome(); } return "-";
	 * 
	 * } catch (NamingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; } }
	 */
	/*
	 * public String getNomeSetor() {
	 * 
	 * try { if(this.setorId!=null){
	 * 
	 * SetorFacadeBean setorFacadeBean;
	 * 
	 * InitialContext ctx = new InitialContext();
	 * 
	 * setorFacadeBean = (SetorFacadeBean) ctx.lookup(
	 * "java:global/sisrep_ear/seguranca_cache_server/SegCache_SetorFacadeBean!br.gov.ma.tce.seguranca.server.beans.setor.SetorFacadeBean"
	 * );
	 * 
	 * Setor s = setorFacadeBean.findSetor(this.setorId);
	 * 
	 * if(s!=null) { return s.getSigla(); } } return "-"; } catch (NamingException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); return null; } }
	 */
	
}