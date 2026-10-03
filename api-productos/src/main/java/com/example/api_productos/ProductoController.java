package com.example.api_productos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Datos de ejemplo en memoria 
    private final List<Producto> productos = List.of(
        new Producto(1L, "Laptop Lenovo IdeaPad", "Computadores", 2500000, 12),
        new Producto(2L, "Mouse inalámbrico Logitech", "Accesorios", 65000, 40),
        new Producto(3L, "Teclado mecánico Redragon", "Accesorios", 180000, 25),
        new Producto(4L, "Monitor Samsung 24\"", "Pantallas", 620000, 8),
        new Producto(5L, "Audífonos Sony WH-CH520", "Audio", 230000, 15)
    );

    // GET /api/productos: todos los registros
    @GetMapping
    public List<Producto> listarTodos() {
        return productos;
    }

    // GET /api/productos/{id}: un registro (200) o 404
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return productos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
