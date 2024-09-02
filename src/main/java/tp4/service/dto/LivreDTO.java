package tp4.service.dto;

import lombok.*;
import tp4.model.Livre;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LivreDTO extends DocumentDTO {

    private String isbn;

    public static LivreDTO toDTO(Livre livre) {
        LivreDTO livre_dto = new LivreDTO();
        livre_dto.setId(livre.getId());
        livre_dto.setTitle(livre.getTitle());
        livre_dto.setAuthor(livre.getAuthor());
        livre_dto.setPublisher(livre.getPublisher());
        livre_dto.setPublicationYear(livre.getPublicationYear());
        livre_dto.setPages(livre.getPages());
        livre_dto.setGenre(livre.getGenre());
        livre_dto.setStock(livre.getStock());
        livre_dto.setIsbn(livre.getIsbn());
        return livre_dto;
    }

    public static Livre toEntity(LivreDTO livre_dto) {
        Livre livre = new Livre();
        livre.setId(livre_dto.getId());
        livre.setTitle(livre_dto.getTitle());
        livre.setAuthor(livre_dto.getAuthor());
        livre.setPublisher(livre_dto.getPublisher());
        livre.setPublicationYear(livre_dto.getPublicationYear());
        livre.setPages(livre_dto.getPages());
        livre.setGenre(livre_dto.getGenre());
        livre.setStock(livre_dto.getStock());
        livre.setIsbn(livre_dto.getIsbn());
        return livre;
    }

    public static List<LivreDTO> toDTOs(List<Livre> livres) {
        return livres.stream()
                .map(LivreDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Livre {" +
                "id=" + getId() +
                ", title=" + getTitle() +
                ", author=" + getAuthor() +
                ", publisher=" + getPublisher() +
                ", publicationYear=" + getPublicationYear() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", stock=" + getStock() +
                ", isbn=" + isbn +
                "}";
    }
}