package tp4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp4.service.dto.EmprunteurDTO;
import tp4.service.dto.PreposeDTO;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")

public class Utilisateur {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String role;

    public static Utilisateur createUtilisateur(String type, String nom, String prenom, String adresse, String telephone, String email, String role) {
        if (type.equalsIgnoreCase("Emprunteur")) {
            Emprunteur emprunteur = new Emprunteur();
            emprunteur.setNom(nom);
            emprunteur.setPrenom(prenom);
            emprunteur.setAdresse(adresse);
            emprunteur.setTelephone(telephone);
            emprunteur.setEmail(email);
            emprunteur.setRole(role);
            return emprunteur;
        } else if (type.equalsIgnoreCase("Prepose")) {
            Prepose prepose = new Prepose();
            prepose.setNom(nom);
            prepose.setPrenom(prenom);
            prepose.setAdresse(adresse);
            prepose.setTelephone(telephone);
            prepose.setEmail(email);
            prepose.setRole(role);
            return prepose;
        } else {
            throw new IllegalArgumentException("Invalid user type: " + type);
        }
    }

    public EmprunteurDTO toEmprunteurDTO() {
        EmprunteurDTO emprunteurDTO = new EmprunteurDTO();
        emprunteurDTO.setId(this.id);
        emprunteurDTO.setNom(this.nom);
        emprunteurDTO.setPrenom(this.prenom);
        emprunteurDTO.setAdresse(this.adresse);
        emprunteurDTO.setTelephone(this.telephone);
        emprunteurDTO.setEmail(this.email);
        emprunteurDTO.setRole(this.role);
        return emprunteurDTO;
    }

    public PreposeDTO toPreposeDTO() {
        PreposeDTO preposeDTO = new PreposeDTO();
        preposeDTO.setId(this.id);
        preposeDTO.setNom(this.nom);
        preposeDTO.setPrenom(this.prenom);
        preposeDTO.setAdresse(this.adresse);
        preposeDTO.setTelephone(this.telephone);
        preposeDTO.setEmail(this.email);
        preposeDTO.setRole(this.role);
        return preposeDTO;
    }
}