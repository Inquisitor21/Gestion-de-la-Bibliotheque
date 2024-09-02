package tp4.service.dto;

import lombok.*;
import tp4.model.Prepose;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PreposeDTO extends UtilisateurDTO {

    private String role;

    public static PreposeDTO toDTO(Prepose prepose) {
        PreposeDTO prepose_dto = new PreposeDTO();
        prepose_dto.setId(prepose.getId());
        prepose_dto.setNom(prepose.getNom());
        prepose_dto.setPrenom(prepose.getPrenom());
        prepose_dto.setAdresse(prepose.getAdresse());
        prepose_dto.setTelephone(prepose.getTelephone());
        prepose_dto.setEmail(prepose.getEmail());
        prepose_dto.setRole(prepose.getRole());
        return prepose_dto;
    }

    public static Prepose toEntity(PreposeDTO prepose) {
        Prepose prepose_entity = new Prepose();
        prepose_entity.setId(prepose.getId());
        prepose_entity.setNom(prepose.getNom());
        prepose_entity.setPrenom(prepose.getPrenom());
        prepose_entity.setAdresse(prepose.getAdresse());
        prepose_entity.setTelephone(prepose.getTelephone());
        prepose_entity.setEmail(prepose.getEmail());
        prepose_entity.setRole(prepose.getRole());
        return prepose_entity;
    }

    @Override
    public String toString() {
        return "Prepose {" +
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