package br.gov.ma.tce.recepcao.domain.repository;
import br.gov.ma.tce.recepcao.domain.models.Usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>, JpaSpecificationExecutor<Usuario>{
	
	
	@Query("select u from Usuario u where 1=1 ")
	public List<Usuario> findUsuarioByFiltro(String login, String nome);
	
	@Query("select u from  Usuario u where u.login =:login")
	public Usuario findByLogin(String login);
	
	@Query("select u from Usuario u where u.login =:login and u.nome =:nome order by u.nome")
	public Usuario findByFiltro(String login, String nome);
		
	public Usuario findByUsuarioId(Integer usuarioId);
	
	
	
	

}
