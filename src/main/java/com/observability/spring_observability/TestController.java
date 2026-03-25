package com.observability.spring_observability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private TestJpaRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<String> testGetId(@PathVariable("id") Long id) {
        Optional<TestEntity> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            return ResponseEntity.ok(String.valueOf(entityOptional.get().getId()));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> createTestId(@PathVariable("id") Long id) {
        TestEntity test = new TestEntity();
        test.setId(id);

        test = repository.saveAndFlush(test);
        return ResponseEntity.ok(String.valueOf(test.getId()));
    }

}
