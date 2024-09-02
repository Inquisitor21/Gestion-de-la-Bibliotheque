package tp4.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp4.service.EmpruntService;
import tp4.service.UtilisateurService;
import tp4.service.dto.EmpruntDTO;
import tp4.service.dto.EmprunteurDTO;
import tp4.service.dto.UtilisateurDTO;
import java.util.List;

@RestController
public class EmprunteurController {
    
    private final EmpruntService empruntService;
    private final UtilisateurService utilisateurService;
    
    public EmprunteurController(EmpruntService empruntService,
                                UtilisateurService utilisateurService
    ) {
        this.empruntService = empruntService;
        this.utilisateurService = utilisateurService;
    }
    
    //Créer un emprunteur
    @PostMapping("/creer-emprunteur")
    public ResponseEntity<?> apresCreationEmprunteur(@RequestBody EmprunteurDTO emprunteurDTO) {
        utilisateurService.addUtilisateur(emprunteurDTO, "Emprunteur");
        return ResponseEntity.ok().body(emprunteurDTO);
    }
    
    //Lister les emprunteurs
    @GetMapping("/emprunteurs")
    public List<UtilisateurDTO> listEmprunteurs(){
        return utilisateurService.getUtilisateurs("Emprunteur");
    }
    
    //Voir les emprunts d'un emprunteur
    @GetMapping("/emprunts-emprunteur/{id}")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsByEmprunteurId(@PathVariable Long id) {
        List<EmpruntDTO> emprunts = empruntService.getEmpruntsByEmprunteurId(id);
        return ResponseEntity.ok(emprunts);
    }
    
    //Voir formulaire
    @GetMapping("/emprunts-par-emprunteur")
    public List<UtilisateurDTO> getEmpruntsByEmprunteurForm() {
        return utilisateurService.getUtilisateurs("Emprunteur");
    }
}