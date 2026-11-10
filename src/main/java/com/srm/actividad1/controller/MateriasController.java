package com.srm.actividad1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MateriasController {

    
    private final List<String> materias = List.of(
        "Programacion Web",
        "Servicios Web",
        "Conmutacion y Enrutamiento."
        "Programacion web2"
    );

    //  hacer GET /api/materias.
    @GetMapping("/materias")
    public List<String> mostrarMaterias() {
        return materias;
    }

    // hacer GET /api/total-materias.
    @GetMapping("/total-materias")
    public int contarMaterias() {
        return materias.size();
    }
}
