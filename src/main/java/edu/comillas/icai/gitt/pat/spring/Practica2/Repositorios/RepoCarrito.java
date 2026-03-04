package edu.comillas.icai.gitt.pat.spring.Practica2.Repositorios;

import edu.comillas.icai.gitt.pat.spring.Practica2.Entidades.Carrito;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoCarrito extends CrudRepository<Carrito, Long> {
    Carrito findByCorreoUsuario(String correoUsuario);
}
