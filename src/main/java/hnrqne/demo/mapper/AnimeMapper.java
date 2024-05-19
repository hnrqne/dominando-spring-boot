package hnrqne.demo.mapper;

import hnrqne.demo.domain.Anime;
import hnrqne.demo.request.AnimePostRequest;
import hnrqne.demo.request.AnimePutRequest;
import hnrqne.demo.response.AnimeGetResponse;
import hnrqne.demo.response.AnimePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimeMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest anime);

    Anime toAnime(AnimePutRequest anime);

    AnimePostResponse toAnimePostResponse(Anime anime);

    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponses(List<Anime> animes);
}
