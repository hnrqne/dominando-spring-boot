package hnrqne.demo.mapper;

import hnrqne.demo.domain.Producer;
import hnrqne.demo.request.ProducerPostRequest;
import hnrqne.demo.response.ProducerGetResponse;
import hnrqne.demo.response.ProducerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProducerMapper {

    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest request);

    ProducerPostResponse toProducerPostResponse(Producer producer);

    List<ProducerGetResponse> toProducerGetResponses(List<Producer> producers);
}
