package br.gov.ma.tce.recepcao.domain.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.gov.ma.tce.recepcao.domain.models.FiltroVisita;
import br.gov.ma.tce.recepcao.domain.models.Visita;
import br.gov.ma.tce.recepcao.domain.repository.VisitaRepository;

@RestController
@RequestMapping("/visitas")
public class VisitaController {
	
	
	@Autowired
	private VisitaRepository visitaRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Visita>> listar(){
		
		return ResponseEntity.ok(visitaRepository.findAll());
		//return ResponseEntity.ok(visitaRepository.findAllOrdenadosPorEntrada());
	}
	
	@GetMapping("/porEntrada")
	public ResponseEntity<List<Visita>> listarPorEntrada(){
		return ResponseEntity.ok(visitaRepository.findByOrderByHoraEntradaDesc());
	}
	
	
	@GetMapping("/porKey")
	public ResponseEntity<Visita> buscarPorKey(Integer visitaId){
		return ResponseEntity.ok(visitaRepository.findByVisitaId(visitaId));
	}
	
	@GetMapping("/porCpf")
	public ResponseEntity<List<Visita>> buscarPorCpf(@RequestParam("cpf") String cpf){
		return ResponseEntity.ok(visitaRepository.findByCpf(cpf));
	}
	
	@GetMapping("/porSetor")
	public ResponseEntity<List<Visita>> buscarVisitasPorSetor(@RequestParam("setorId") String setorId){
		return ResponseEntity.ok(visitaRepository.findBySetorId(setorId));
	}
	
	@GetMapping("/porFiltro")
	public ResponseEntity<List<Visita>> buscarPorFiltro(FiltroVisita especification){
		
		List<Visita> visitas =  visitaRepository.findAll(especification.toSpec());
		return ResponseEntity.ok(visitas);
		
	}
	
	
	@PostMapping
	public ResponseEntity<Visita> salvar(@RequestBody Visita visita){
		
		Visita visitaSalva = visitaRepository.save(visita);
		return ResponseEntity.ok(visitaSalva);
		
	}
	
	@PutMapping("/{visitaId}")
	public ResponseEntity<Visita> encerrarVisita(@PathVariable("visitaId") Integer visitaId
	){
		
		Visita visitaToUpdate = visitaRepository.findById(visitaId).orElse(null);
		visitaToUpdate.setHoraSaida(new Date());
		visitaRepository.save(visitaToUpdate);
		return ResponseEntity.ok(visitaToUpdate);
	}
	
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/{visitaId}")
	public void remover(@PathVariable("visitaId") Integer visitaId) {
		visitaRepository.deleteById(visitaId);
	}
	
	
	
	
	/*
	 * public void salvar(Visita visita) { visitaRepository.save(visita); }
	 */
	
	

}
