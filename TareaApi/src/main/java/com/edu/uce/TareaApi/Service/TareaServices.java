package com.edu.uce.TareaApi.Service;

import com.edu.uce.TareaApi.Entity.Tarea;
import com.edu.uce.TareaApi.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TareaServices {
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> buscar(){
        return tareaRepository.findAll();
    }
    public Optional<Tarea>buscarID(long id){
        return tareaRepository.findById(id);
    }
    public Tarea crear(Tarea tarea){
        tarea.setFechaCreacion(String.valueOf(LocalDateTime.now()));
     return tareaRepository.save(tarea);
    }
    public Tarea actualizar(Long id, Tarea tarea) {
        return tareaRepository.findById(id)
                .map(existingTarea -> {
                    tarea.setId(id);
                    return tareaRepository.save(tarea);
                })
                .orElse(null);
    }

    public void borrar(Long id) {
        tareaRepository.deleteById(id);
    }

    public List<Tarea> buscarEstado(boolean estado) {
        return tareaRepository.findByEstado(estado);
    }
}
