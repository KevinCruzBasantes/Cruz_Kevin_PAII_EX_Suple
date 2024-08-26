package com.edu.uce.TareaApi.Controller;

import com.edu.uce.TareaApi.Entity.Tarea;
import com.edu.uce.TareaApi.Repository.TareaRepository;
import com.edu.uce.TareaApi.Service.TareaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaControler {
    @Autowired
    private TareaServices tareaServices;
    @GetMapping
    public List<Tarea> leerTareas() {
        return tareaServices.buscar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> leerById(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaServices.buscarID(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Tarea> creaarTarea(@RequestBody Tarea tarea) {
        Tarea crearTarea = tareaServices.crear(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearTarea);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea actualizarTarea = tareaServices.actualizar(id,tarea);
        return actualizarTarea != null ? ResponseEntity.ok(actualizarTarea) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTarea(@PathVariable Long id) {
        tareaServices.borrar(id);
        return ResponseEntity.noContent().build();
    }
    // Nuevo endpoint para listar tareas por estado
    @GetMapping("/status/{estado}")
    public ResponseEntity<List<Tarea>> leerEstado(@PathVariable boolean estado) {
        List<Tarea> tareas = tareaServices.buscarEstado(estado);
        return ResponseEntity.ok(tareas);
    }
    //Endpoint para actualizar el estado de la tarea
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Tarea> actualizarEstadoTarea(@PathVariable Long id, @RequestParam boolean estado) {
        Tarea tareaActualizada = tareaServices.actualizarEstado(id, estado);
        return tareaActualizada != null ? ResponseEntity.ok(tareaActualizada) : ResponseEntity.notFound().build();
    }
}
//post para ser cargado con postman
/*
{
    "titulo": "Comprar leche",
    "descripcion": "Ir al supermercado para comprar leche",
    "estado": true,
    "fechaCreacion": "2024-08-26T15:30:00"
}
{
    "titulo": "Comprar pan",
    "descripcion": "Ir al supermercado para comprar pan",
    "estado": false,
    "fechaCreacion": "2024-08-26T15:30:00"
}

 */