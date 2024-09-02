package tp4.service.dto;

import lombok.*;
import tp4.model.ManuelScolaire;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ManuelScolaireDTO extends DocumentDTO {

    private int niveauSecondaire;

    public static ManuelScolaireDTO toDTO(ManuelScolaire manuel_scolaire) {
        ManuelScolaireDTO manuel_scolaire_dto = new ManuelScolaireDTO();
        manuel_scolaire_dto.setId(manuel_scolaire.getId());
        manuel_scolaire_dto.setTitle(manuel_scolaire.getTitle());
        manuel_scolaire_dto.setAuthor(manuel_scolaire.getAuthor());
        manuel_scolaire_dto.setPublisher(manuel_scolaire.getPublisher());
        manuel_scolaire_dto.setPublicationYear(manuel_scolaire.getPublicationYear());
        manuel_scolaire_dto.setPages(manuel_scolaire.getPages());
        manuel_scolaire_dto.setGenre(manuel_scolaire.getGenre());
        manuel_scolaire_dto.setStock(manuel_scolaire.getStock());
        manuel_scolaire_dto.setNiveauSecondaire(manuel_scolaire.getNiveauSecondaire());

        return manuel_scolaire_dto;
    }

    public static ManuelScolaire toEntity(ManuelScolaireDTO manuel_scolaire_dto) {
        ManuelScolaire manuel_scolaire = new ManuelScolaire();
        manuel_scolaire.setId(manuel_scolaire_dto.getId());
        manuel_scolaire.setTitle(manuel_scolaire_dto.getTitle());
        manuel_scolaire.setAuthor(manuel_scolaire_dto.getAuthor());
        manuel_scolaire.setPublisher(manuel_scolaire_dto.getPublisher());
        manuel_scolaire.setPublicationYear(manuel_scolaire_dto.getPublicationYear());
        manuel_scolaire.setPages(manuel_scolaire_dto.getPages());
        manuel_scolaire.setGenre(manuel_scolaire_dto.getGenre());
        manuel_scolaire.setGenre(manuel_scolaire_dto.getGenre());
        manuel_scolaire.setStock(manuel_scolaire_dto.getStock());
        manuel_scolaire.setNiveauSecondaire(manuel_scolaire_dto.getNiveauSecondaire());
        return manuel_scolaire;
    }

    public static List<ManuelScolaireDTO> toDTOs(List<ManuelScolaire> manuelsScolaires) {
        return manuelsScolaires.stream()
                .map(ManuelScolaireDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ManuelScolaire {" +
                "id=" + getId() +
                ", title=" + getTitle() +
                ", author=" + getAuthor() +
                ", publisher=" + getPublisher() +
                ", publicationYear=" + getPublicationYear() +
                ", pages=" + getPages() +
                ", genre=" + getGenre() +
                ", stock=" + getStock() +
                ", niveauSecondaire=" + niveauSecondaire +
                " }";
    }
}