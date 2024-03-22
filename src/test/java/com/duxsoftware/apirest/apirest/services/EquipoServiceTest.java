package com.duxsoftware.apirest.apirest.services;

import com.duxsoftware.apirest.apirest.DTO.EquipoRequest;
import com.duxsoftware.apirest.apirest.models.Equipo;
import com.duxsoftware.apirest.apirest.repositories.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {
    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    private List<Equipo> equipos;

    @BeforeEach
    public void setup(){
        equipos = new ArrayList<>();
        equipos.add(new Equipo( 1L, "Real Madrid", "La Liga", "España"));
        equipos.add(new Equipo( 2L, "Barcelona","La Liga", "España" ));
        equipos.add(new Equipo( 3L, "Liverpool FC", "Premier League", "Inglaterra"));
        equipos.add(new Equipo( 4L, "FC Porto", "Primeira Liga", "Portugal"));
    }

    @Test
    public void testFindAll(){
        when(equipoRepository.findAll()).thenReturn(equipos);
        List<Equipo> resultado = equipoService.findAll();
        assertEquals(4, resultado.size());
    }

    @Test
    public void testFindById(){
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipos.get(0)));
        Optional<Equipo> resultado = equipoService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(equipos.get(0),resultado.get());
    }

    @Test
    public void testFindByNombreContaining(){
        String nombre = "FC";
        List<Equipo> equiposConFc = equipos.stream()
                .filter(equipo -> equipo.getNombre().contains("FC"))
                .collect(Collectors.toList());
        when(equipoRepository.findByNombreContaining(nombre)).thenReturn(equiposConFc);
        List<Equipo> equipos = equipoService.findByNombreContaining(nombre);
        assertEquals(2, equipos.size());
    }

    @Test
    public void testCreateEquipo(){
        EquipoRequest equipoRequest = new EquipoRequest();
        equipoRequest.setNombre("River Plate");
        equipoRequest.setLiga("Primera Division");
        equipoRequest.setPais("Argentina");

        Equipo equipoGuadado = new Equipo("River Plate", "Primera Division", "Argentina");

        when(equipoRepository.findByNombreAndLigaAndPais(anyString(), anyString(), anyString())).thenReturn(null);
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoGuadado);

        Equipo nuevoEquipo = equipoService.createEquipo(equipoRequest);

        assertNotNull(nuevoEquipo);
        assertEquals("River Plate", nuevoEquipo.getNombre());
        assertEquals("Primera Division", nuevoEquipo.getLiga());
        assertEquals("Argentina", nuevoEquipo.getPais());
    }

    @Test
    public void testUpdateEquipo(){
        Long equipoId = 2L;
        EquipoRequest equipoRequest = new EquipoRequest();
        equipoRequest.setNombre("Barcelona Actualizado");
        equipoRequest.setLiga("La Liga Actualizada");
        equipoRequest.setPais("España Actualizada");

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipos.get(1)));
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipos.get(1));

        Equipo equipoActualizado = equipoService.updateEquipo(equipoId, equipoRequest);
        assertNotNull(equipoActualizado);
        assertEquals(equipoId, equipoActualizado.getId());
        assertEquals("Barcelona Actualizado", equipoActualizado.getNombre());
        assertEquals("La Liga Actualizada", equipoActualizado.getLiga());
        assertEquals("España Actualizada", equipoActualizado.getPais());
    }

    @Test
    public void testDeleteEquipo(){
        when(equipoRepository.findById(2L)).thenReturn(Optional.of(equipos.get(1)));

        assertDoesNotThrow(()-> equipoService.deleteEquipo(2L));
        verify(equipoRepository, times(1)).delete(equipos.get(1));
    }

    @Test
    public void testDeleteEquipoNotFound(){
        Long equipoId = 999999999L;
        when(equipoRepository.findById(equipoId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {equipoService.deleteEquipo(equipoId);});
    }
}
