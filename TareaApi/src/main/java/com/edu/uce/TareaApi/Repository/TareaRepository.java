package com.edu.uce.TareaApi.Repository;

import com.edu.uce.TareaApi.Entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
    List<Tarea> findByEstado(boolean estado);
}
