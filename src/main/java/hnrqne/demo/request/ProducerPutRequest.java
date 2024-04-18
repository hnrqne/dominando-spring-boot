package hnrqne.demo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerPutRequest {
    Long id;
    String name;
}
