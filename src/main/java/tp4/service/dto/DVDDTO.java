package tp4.service.dto;

import lombok.*;
import tp4.model.DVD;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DVDDTO extends DocumentDTO {

    private String director;

    public static DVDDTO toDTO(DVD dvd) {
        DVDDTO dvd_dto = new DVDDTO();
        dvd_dto.setId(dvd.getId());
        dvd_dto.setTitle(dvd.getTitle());
        dvd_dto.setAuthor(dvd.getAuthor());
        dvd_dto.setPublisher(dvd.getPublisher());
        dvd_dto.setPublicationYear(dvd.getPublicationYear());
        dvd_dto.setGenre(dvd.getGenre());
        dvd_dto.setStock(dvd.getStock());
        dvd_dto.setDirector(dvd.getDirector());
        return dvd_dto;
    }

    public static DVD toEntity(DVDDTO dvd_dto) {
        DVD dvd = new DVD();
        dvd.setId(dvd_dto.getId());
        dvd.setTitle(dvd_dto.getTitle());
        dvd.setAuthor(dvd_dto.getAuthor());
        dvd.setPublisher(dvd_dto.getPublisher());
        dvd.setPublicationYear(dvd_dto.getPublicationYear());
        dvd.setGenre(dvd_dto.getGenre());
        dvd.setStock(dvd_dto.getStock());
        dvd.setDirector(dvd_dto.getDirector());
        return dvd;
    }

    public static List<DVDDTO> toDTOs(List<DVD> dvds) {
        return dvds.stream()
                .map(DVDDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "DVD {" +
                "id=" + getId() +
                ", title=" + getTitle() +
                ", author=" + getAuthor() +
                ", publisher=" + getPublisher() +
                ", publicationYear=" + getPublicationYear() +
                ", genre=" + getGenre() +
                ", stock=" + getStock() +
                ", director=" + director +
                "}";
    }
}