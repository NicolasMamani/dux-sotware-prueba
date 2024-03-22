package com.duxsoftware.apirest.apirest.controllers;

import com.duxsoftware.apirest.apirest.DTO.EquipoRequest;
import com.duxsoftware.apirest.apirest.models.Equipo;
import com.duxsoftware.apirest.apirest.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> list(){
        return equipoService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<Equipo> equipoOptional = equipoService.findById(id);
        if(equipoOptional.isPresent()){
            return ResponseEntity.ok(equipoOptional.orElseThrow());
        }
        Map<String, Object> body = new LinkedHashMap<>(); // Utilizamos LinkedHashMap
        body.put("mensaje", "Equipo no encontrado");
        body.put("codigo", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @GetMapping( path="/buscar" )
    public ResponseEntity<List<Equipo>> findByNombre(@RequestParam("nombre") String nombre){
        List<Equipo> equiposEncontrados = equipoService.findByNombreContaining(nombre);
        return ResponseEntity.ok(equiposEncontrados);
    }

    @PostMapping
    public ResponseEntity<?> createEquipo(@RequestBody EquipoRequest equipoRequest){
        try{
            Equipo nuevoEquipo = equipoService.createEquipo(equipoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
        }catch(IllegalArgumentException error){
            Map<String, Object> body = new LinkedHashMap<>(); // Utilizamos LinkedHashMap
            body.put("mensaje", "La solicitud es invalida");
            body.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @PutMapping( path = "/{id}" )
    public ResponseEntity<?> updateEquipo(@PathVariable("id") Long id, @RequestBody EquipoRequest equipoRequest){
        try{
            Equipo equipoActualizado = equipoService.updateEquipo(id, equipoRequest);
            return ResponseEntity.status(HttpStatus.OK).body(equipoActualizado);
        }catch(NoSuchElementException e){
            Map<String, Object> body= new LinkedHashMap<>();
            body.put("mensaje", "Equipo no encontrado");
            body.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @DeleteMapping( path = "/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id){
        try{
            equipoService.deleteEquipo(id);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException e){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", "Equipo no encontrado");
            body.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}
