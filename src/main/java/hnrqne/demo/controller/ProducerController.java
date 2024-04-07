package hnrqne.demo.controller;

import hnrqne.demo.domain.Producer;
import hnrqne.demo.mapper.ProducerMapper;
import hnrqne.demo.request.ProducerPostRequest;
import hnrqne.demo.response.ProducerPostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"v1/producers", "v1/producers/"})
public class ProducerController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-version=v1")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest request) {
        var mapper = ProducerMapper.INSTANCE;
        var producer = mapper.toProducer(request);
        var response = mapper.toProducerPostResponse(producer);

        Producer.getProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
