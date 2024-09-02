package tp4.repository.vides;

import org.springframework.data.jpa.repository.JpaRepository;
import tp4.model.Utilisateur;

public interface UtilisateurRepository<T extends Utilisateur, ID> extends JpaRepository<T, ID> {}
