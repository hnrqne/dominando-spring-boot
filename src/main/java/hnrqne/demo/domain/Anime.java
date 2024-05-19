package hnrqne.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    private Long id;
    @JsonProperty(value = "name")
    private String name;
}
