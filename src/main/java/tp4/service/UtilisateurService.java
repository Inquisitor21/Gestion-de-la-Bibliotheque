package tp4.service;

import org.springframework.stereotype.Service;
import tp4.model.Emprunteur;
import tp4.model.Prepose;
import tp4.repository.vides.EmprunteurRepository;
import tp4.repository.vides.PreposeRepository;
import tp4.service.dto.EmprunteurDTO;
import tp4.service.dto.PreposeDTO;
import tp4.service.dto.UtilisateurDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final EmprunteurRepository emprunteurRepository;
    private final PreposeRepository preposeRepository;

    public UtilisateurService(EmprunteurRepository emprunteurRepository,
                              PreposeRepository preposeRepository)
    {
        this.emprunteurRepository = emprunteurRepository;
        this.preposeRepository = preposeRepository;
    }

    public UtilisateurDTO getUtilisateurById(Long id, String userType) {
        switch (userType) {
            case "Prepose":
                Prepose prepose = preposeRepository.findById(id).orElse(null);
                if (prepose == null) {
                    throw new IllegalArgumentException("Prepose avec ID: " + id + " n'existe pas");
                }
                return PreposeDTO.toDTO(prepose);
            case "Emprunteur":
                Emprunteur emprunteur = emprunteurRepository.findById(id).orElse(null);
                if (emprunteur == null) {
                    throw new IllegalArgumentException("Emprunteur avec ID: " + id + " n'existe pas");
                }
                return EmprunteurDTO.toDTO(emprunteur);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public void addUtilisateur(UtilisateurDTO utilisateurDTO, String userType) {
        switch (userType) {
            case "Prepose":
                Prepose prepose = PreposeDTO.toEntity((PreposeDTO) utilisateurDTO);
                preposeRepository.save(prepose);
                break;
            case "Emprunteur":
                Emprunteur emprunteur = EmprunteurDTO.toEntity((EmprunteurDTO) utilisateurDTO);
                emprunteurRepository.save(emprunteur);
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public void updateUtilisateur(UtilisateurDTO utilisateurDTO, String userType) {
        switch (userType) {
            case "Prepose":
                Prepose prepose = PreposeDTO.toEntity((PreposeDTO) utilisateurDTO);
                if (preposeRepository.existsById(prepose.getId())) {
                    preposeRepository.save(prepose);
                }
                break;
            case "Emprunteur":
                Emprunteur emprunteur = EmprunteurDTO.toEntity((EmprunteurDTO) utilisateurDTO);
                if (emprunteurRepository.existsById(emprunteur.getId())) {
                    emprunteurRepository.save(emprunteur);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public void deleteUtilisateur(Long id, String userType) {
        UtilisateurDTO utilisateur = getUtilisateurById(id, userType);
        
        if (utilisateur == null) {
            System.out.println("L'utilisateur de type " + userType + " n'existe pas.");
            return;
        }

        switch (userType) {
            case "Prepose":
	            preposeRepository.findById(id).ifPresent(preposeRepository::delete);
	            break;
            case "Emprunteur":
	            emprunteurRepository.findById(id).ifPresent(emprunteurRepository::delete);
	            break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
        System.out.println( userType + " avec id " + id + " a été supprimé avec succès.");
    }

    public List<UtilisateurDTO> getUtilisateurs(String userType) {
	    return switch (userType) {
		    case "Emprunteur" -> emprunteurRepository.findAll()
				    .stream()
				    .map(EmprunteurDTO::toDTO)
				    .collect(Collectors.toList());
		    case "Prepose" -> preposeRepository.findAll()
				    .stream()
				    .map(PreposeDTO::toDTO)
				    .collect(Collectors.toList());
		    default -> throw new IllegalArgumentException("Invalid user type: " + userType);
	    };
    }
}