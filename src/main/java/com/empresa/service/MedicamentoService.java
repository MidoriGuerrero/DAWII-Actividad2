package com.empresa.service;
import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {

	public abstract Medicamento insertaActualiza(Medicamento obj);
	public abstract void elimina(int id);
	public abstract List<Medicamento> listaMedicamento();
	public abstract Optional<Medicamento> listaMedicamentoxId(int id);
	public abstract List<Medicamento> listaMedicamentoxNombre(String nombre);
	public abstract List<Medicamento> listaMedicamentoxStock(int stock);

}
