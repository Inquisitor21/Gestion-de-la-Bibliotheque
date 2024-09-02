package tp4.service.dto;

import lombok.*;
import tp4.model.Emprunteur;

@Getter
@Setter
@AllArgsConstructor

public class EmprunteurDTO extends UtilisateurDTO {

    public static EmprunteurDTO toDTO(Emprunteur emprunteur) {
        EmprunteurDTO emprunteur_dto = new EmprunteurDTO();
        emprunteur_dto.setId(emprunteur.getId());
        emprunteur_dto.setNom(emprunteur.getNom());
        emprunteur_dto.setPrenom(emprunteur.getPrenom());
        emprunteur_dto.setAdresse(emprunteur.getAdresse());
        emprunteur_dto.setTelephone(emprunteur.getTelephone());
        emprunteur_dto.setEmail(emprunteur.getEmail());
        emprunteur_dto.setRole("Emprunteur");
        return emprunteur_dto;
    }

    public static Emprunteur toEntity(EmprunteurDTO emprunteur_dto) {
        Emprunteur emprunteur = new Emprunteur();
        emprunteur.setId(emprunteur_dto.getId());
        emprunteur.setNom(emprunteur_dto.getNom());
        emprunteur.setPrenom(emprunteur_dto.getPrenom());
        emprunteur.setAdresse(emprunteur_dto.getAdresse());
        emprunteur.setTelephone(emprunteur_dto.getTelephone());
        emprunteur.setEmail(emprunteur_dto.getEmail());
        emprunteur.setRole("Emprunteur");
        return emprunteur;
    }

    @Override
    public String toString() {
        return "Emprunteur {" +
                "id=" + getId() +
                ", prenom=" + getPrenom() +
                ", nom=" + getNom() +
                ", adresse=" + getAdresse() +
                ", telephone=" + getTelephone() +
                ", email=" + getEmail() +
                ", role=" + getRole() +
                "}";
    }
}