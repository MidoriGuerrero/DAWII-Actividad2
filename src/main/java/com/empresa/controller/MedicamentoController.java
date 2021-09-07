package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamento")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamento(){
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Medicamento> registraMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			obj.setIdMedicamento(0);
			Medicamento objSalida = service.insertaActualiza(obj);
			return ResponseEntity.ok(objSalida);
		}
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.badRequest().build();
		}else {

			Optional<Medicamento> optMedicamento = service.listaMedicamentoxId(obj.getIdMedicamento());
			if (optMedicamento.isPresent()) {
				Medicamento objActualizado = service.insertaActualiza(obj);
				return ResponseEntity.ok(objActualizado);
			}else {

				return ResponseEntity.badRequest().build();

			}

		}

	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Medicamento> eliminaMedicamento(@PathVariable int id) {
		Optional<Medicamento> med = service.listaMedicamentoxId(id);
		
		if (med.isPresent()) {
			service.elimina(id);
			Optional<Medicamento> optEliminado = service.listaMedicamentoxId(id);
			
			if (optEliminado.isPresent()) {
				return ResponseEntity.badRequest().build();
				
			}else {
				return ResponseEntity.ok(med.get());
			}
			
		}else {
			return ResponseEntity.badRequest().build();

		}
    }
	
	@GetMapping("/id/{paramId}")	
	@ResponseBody	
	public ResponseEntity<Medicamento> listaMedicamentoxId(@PathVariable("paramId")int id){
		Optional<Medicamento> optMedicamento = service.listaMedicamentoxId(id);
	
		if (optMedicamento.isPresent()) {	
			return ResponseEntity.ok(optMedicamento.get());
			
		}else {
	
			return ResponseEntity.badRequest().build();
	
		}
	
	}
	
	@GetMapping("/nombre/{paramNombre}")	
	@ResponseBody	
	public ResponseEntity<List<Medicamento>> listaMedicamentoxNombre(@PathVariable("paramNombre")String nombre){
		List<Medicamento> lista  = service.listaMedicamentoxNombre(nombre);
		
		if (CollectionUtils.isEmpty(lista)) {	
			return ResponseEntity.badRequest().build();
	
		}else {
			return ResponseEntity.ok(lista);		
	
		}
	}
	
	@GetMapping("/stock/{paramStock}")	
	@ResponseBody	
	public ResponseEntity<List<Medicamento>> listaMedicamentoxNombre(@PathVariable("paramStock")int stock){
		List<Medicamento> lista  = service.listaMedicamentoxStock(stock);		
		if (CollectionUtils.isEmpty(lista)) {	
			return ResponseEntity.badRequest().build();
	
		}else {
			return ResponseEntity.ok(lista);		
	
		}
	}
	
}