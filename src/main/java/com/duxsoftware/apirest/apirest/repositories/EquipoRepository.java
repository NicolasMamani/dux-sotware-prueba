package com.duxsoftware.apirest.apirest.repositories;

import com.duxsoftware.apirest.apirest.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByNombreContaining(String nombre);

    Equipo findByNombreAndLigaAndPais(String nombre, String Liga, String Pais);
}
