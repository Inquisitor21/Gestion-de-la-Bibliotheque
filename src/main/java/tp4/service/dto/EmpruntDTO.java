package tp4.service.dto;

import lombok.*;
import tp4.model.Document;
import tp4.model.Emprunt;
import tp4.model.Emprunteur;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class EmpruntDTO {

    private Long id;
    private Emprunteur emprunteur;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffectue;
    private int copiesLouees;
    private int fraisRetard;

    public static EmpruntDTO toDTO(Emprunt emprunt) {
        EmpruntDTO dto = new EmpruntDTO();
        dto.setId(emprunt.getId());
        dto.setEmprunteur(emprunt.getEmprunteur());
        dto.setDocument(emprunt.getDocument());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        dto.setDateRetourEffectue(emprunt.getDateRetourEffectue());
        dto.setCopiesLouees(emprunt.getCopiesLouees());
        dto.setFraisRetard(emprunt.getFraisRetard());
        return dto;
    }

    public static Emprunt toEntity(EmpruntDTO dto) {
        Emprunt emprunt = new Emprunt();
        emprunt.setId(dto.getId());
        emprunt.setEmprunteur(dto.getEmprunteur());
        emprunt.setDocument(dto.getDocument());
        emprunt.setDateEmprunt(dto.getDateEmprunt());
        emprunt.setDateRetourPrevue(dto.getDateRetourPrevue());
        emprunt.setDateRetourEffectue(dto.getDateRetourEffectue());
        emprunt.setCopiesLouees(dto.getCopiesLouees());
        emprunt.setFraisRetard(dto.getFraisRetard());
        return emprunt;
    }
}