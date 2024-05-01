package com.example.placemanagement;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlacementController {

    @Autowired
    private PlacementService service;

    // RESTful API methods for Retrieval operations
    @GetMapping("/placement")
    public List<Placement> list() {
        return service.listAll();
    }

    @GetMapping("/placement/{id}")
    public ResponseEntity<Placement> get(@PathVariable Integer id) {
        try {
            Placement placement = service.get(id);
            return new ResponseEntity<Placement>(placement, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Placement>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Create operation
    @PostMapping("/placement")
    public void add(@RequestBody Placement placement) {
        service.save(placement);
    }

    // RESTful API method for Update operation
    @PutMapping("/placement/{id}")
    public ResponseEntity<?> update(@RequestBody Placement placement, @PathVariable Integer id) {
        try {
            Placement existPlacement = service.get(id);
            if (existPlacement != null) {
                // Set the ID of the existing placement to ensure it's updated
                placement.setId(id);
                service.save(placement);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // Placement with the specified ID doesn't exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Delete operation
    @DeleteMapping("/placement/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
