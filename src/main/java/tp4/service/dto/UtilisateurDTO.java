package tp4.service.dto;

import lombok.*;
import tp4.model.Utilisateur;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class UtilisateurDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String role = "Empreunteur"; // Par défaut

    public Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());
        utilisateur.setTelephone(utilisateurDTO.getTelephone());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setRole(utilisateurDTO.getRole());
        return utilisateur;
    }

    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setAdresse(utilisateur.getAdresse());
        utilisateurDTO.setTelephone(utilisateur.getTelephone());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setRole(utilisateur.getRole());
        return utilisateurDTO;
    }
}