package hnrqne.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerGetResponse {
    Long id;
    String name;
}
