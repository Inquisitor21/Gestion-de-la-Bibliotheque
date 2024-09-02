package tp4.service.dto;

import lombok.*;
import tp4.model.CD;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CDDTO extends DocumentDTO {

    private String artist;

    public static CDDTO toDTO(CD cd) {
        CDDTO cd_dto = new CDDTO();
        cd_dto.setId(cd.getId());
        cd_dto.setTitle(cd.getTitle());
        cd_dto.setAuthor(cd.getAuthor());
        cd_dto.setPublisher(cd.getPublisher());
        cd_dto.setPublicationYear(cd.getPublicationYear());
        cd_dto.setGenre(cd.getGenre());
        cd_dto.setStock(cd.getStock());
        cd_dto.setArtist(cd.getArtist());
        return cd_dto;
    }

    public static CD toEntity(CDDTO cd_dto) {
        CD cd = new CD();
        cd.setId(cd_dto.getId());
        cd.setTitle(cd_dto.getTitle());
        cd.setAuthor(cd_dto.getAuthor());
        cd.setPublisher(cd_dto.getPublisher());
        cd.setPublicationYear(cd_dto.getPublicationYear());
        cd.setGenre(cd_dto.getGenre());
        cd.setStock(cd_dto.getStock());
        cd.setArtist(cd_dto.getArtist());
        return cd;
    }

    public static List<CDDTO> toDTOs(List<CD> cds) {
        return cds.stream()
                .map(CDDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "CD {" +
                "artist=" + artist +
                ", id=" + getId() +
                ", title=" + getTitle() +
                ", author=" + getAuthor() +
                ", publisher=" + getPublisher() +
                ", publicationYear=" + getPublicationYear() +
                ", genre=" + getGenre() +
                ", stock=" + getStock() +
                "}";
    }
}