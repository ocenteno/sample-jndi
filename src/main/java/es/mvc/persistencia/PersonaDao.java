package es.mvc.persistencia;

import java.util.List;

import es.mvc.modelo.Persona;

public interface PersonaDao {

	void guardar(Persona p);
	void borrar(Persona p);
	List<Persona> listar();
	Persona buscar(String dni);

}