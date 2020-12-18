package br.gov.ma.tce.recepcao.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ma.tce.recepcao.domain.models.FiltroUsuario;
import br.gov.ma.tce.recepcao.domain.models.Usuario;
import br.gov.ma.tce.recepcao.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public  ResponseEntity <List<Usuario>> listar(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/porLogin")
	public ResponseEntity<Usuario> buscarPorLogin(String login) {
		return ResponseEntity.ok(usuarioRepository.findByLogin(login));
	}
	
	
	 @GetMapping("/porFiltro") 
	 public ResponseEntity<List<Usuario>> buscarPorFiltro(FiltroUsuario especification){ 
		 List<Usuario> usuarios = usuarioRepository.findAll(especification.toSpec());
		 return ResponseEntity.ok(usuarios);
	 }
	 
	@GetMapping("/porKey")
	public ResponseEntity<Usuario> buscarPorKey(Integer usuarioId){
		return ResponseEntity.ok(usuarioRepository.findByUsuarioId(usuarioId));
	}
	
	//@Transactional
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity <Usuario> atualizar(@PathVariable("usuarioId") Integer usuarioId, 
	@RequestBody Usuario usuario) throws Exception {
		Usuario existingUsuario = usuarioRepository.findById(usuarioId).orElse(null);
		if(existingUsuario != null) {
		
			existingUsuario.setNome(usuario.getNome());
			existingUsuario.setSenha(usuario.getSenha());
			existingUsuario.setLogin(usuario.getLogin());
			existingUsuario.setDataCriacao(usuario.getDataCriacao());
			existingUsuario.setStatus(usuario.getStatus());
			
			usuarioRepository.save(existingUsuario);
		
			return ResponseEntity.ok(existingUsuario);
		}else {
			throw new Exception("Usuário não encontrado");
		}
		//PROVISÓRIO
		//return  ResponseEntity.badRequest().body(existingUsuario);
		
	}
	
	@DeleteMapping("/{usuarioId}")
	public void remover(@PathVariable("usuarioId") Integer usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
	
	
		
}
