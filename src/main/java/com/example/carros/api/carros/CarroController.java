package com.example.carros.api.carros;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<Iterable<CarroDTO>> get(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(service.getCarros(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        CarroDTO carro = service.getCarroById(id);
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        List<CarroDTO> carros = service.getCarroByTipo(tipo, PageRequest.of(page, size));
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Carro carro) {
        CarroDTO c = service.save(carro);
        URI location = getURI(c.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        CarroDTO c = service.update(carro, id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


    private URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
