package br.gov.ma.tce.recepcao.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.ma.tce.recepcao.domain.models.Visita;

public interface VisitaRepository extends JpaRepository<Visita,Integer>,JpaSpecificationExecutor<Visita>{

	
	
	public static final String VISITANTES_NO_TCE_QUERY= "select * from sisrep.visita where to_char(cast(sisrep.visita.hora_entrada as date), 'dd/MM/yyyy') in (\n"
			+ "select to_char(cast(sisrep.visita.hora_entrada as date), 'dd/MM/yyyy') from sisrep.visita\n"
			+ "where to_char(cast(sisrep.visita.hora_entrada as date), 'dd/MM/yyyy') in (select to_char(cast(now() as date), 'dd/MM/yyyy'))\n"
			+ ") and sisrep.visita.hora_saida is null ";
	
	public static final String FILTRO_DE_VISITANTES = "select visita from Visita.name visita where visita.visitaId is not null";
			
	
	//@Query("select v from  Visita.name v ORDER BY v.horaEntrada DESC")
	//public List<Visita> findAllOrdenadosPorEntrada();
	
	public List<Visita> findByCpf(String cpf);	
	
	public List<Visita> findBySetorId(String setorId);
	
	public List<Visita> findByOrderByHoraEntradaDesc();
	
	public List<Visita> findByOrderByHoraEntrada();
	
	
	@Query("select visita from Visita visita where visita.visitaId is not null ")
	public List<Visita> findByFiltro();
	
	public Visita findByVisitaId(Integer visitaId);	
	
	@Query(value=VisitaRepository.VISITANTES_NO_TCE_QUERY, nativeQuery = true)
	public Collection<Visita> findVisitasNoTribunal();
	
	
	@Query(value="select v from  Visita v where v.cpf = :cpf order by v.horaEntrada desc", nativeQuery = true)
	public Collection<Visita> findVisitasPorCpf(@Param("cpf") String cpf);
	
	
	
	
}
