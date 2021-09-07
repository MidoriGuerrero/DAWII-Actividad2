package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{
	
	@Autowired
	private MedicamentoRepository repository;

	@Override
	public List<Medicamento> listaMedicamento() {
		return repository.findAll();

	}

	@Override
	public Medicamento insertaActualiza(Medicamento obj) {
		return repository.save(obj);
	}

	@Override
	public void elimina(int id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Medicamento> listaMedicamentoxId(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Medicamento> listaMedicamentoxNombre(String nombre) {
		return repository.findByNombreLike(nombre);
	}

	@Override
	public List<Medicamento> listaMedicamentoxStock(int stock) {
		return repository.findByStock(stock);
	}

}