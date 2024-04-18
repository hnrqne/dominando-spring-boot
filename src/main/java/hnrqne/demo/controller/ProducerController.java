package hnrqne.demo.controller;

import hnrqne.demo.domain.Producer;
import hnrqne.demo.mapper.ProducerMapper;
import hnrqne.demo.request.ProducerPostRequest;
import hnrqne.demo.request.ProducerPutRequest;
import hnrqne.demo.response.ProducerGetResponse;
import hnrqne.demo.response.ProducerPostResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = {"v1/producers", "v1/producers/"})
@Log4j2
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> list(@RequestParam(required = false) String name) {
        log.info("Request received to list all producers, param name '{}'", name);
        var producers = Producer.getProducers();
        var producerGetResponses = MAPPER.toProducerGetResponses(producers);
        if (name == null) return ResponseEntity.ok(producerGetResponses);

        producerGetResponses = producerGetResponses
                .stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(producerGetResponses);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-version=v1")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest request) {

        var producer = MAPPER.toProducer(request);
        var response = MAPPER.toProducerPostResponse(producer);

        Producer.getProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Request received to delete the producer by id '{}'", id);

        var producerFound = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not found to be deleted"));

        Producer.getProducers().remove(producerFound);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.info("Request received to update the producer '{}'", request);

        var producerToRemove = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not found to be updated"));

        var producerUpdated = MAPPER.toProducer(request, producerToRemove.getCreatedAt());
        Producer.getProducers().remove(producerToRemove);
        Producer.getProducers().add(producerUpdated);

        return ResponseEntity.noContent().build();
    }
}
