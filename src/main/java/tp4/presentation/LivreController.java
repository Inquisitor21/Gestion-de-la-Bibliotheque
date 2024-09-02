package tp4.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp4.model.Livre;
import tp4.presentation.exception.CopiesEpuiseesException;
import tp4.service.DocumentService;
import tp4.service.EmpruntService;
import tp4.service.dto.LivreDTO;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class LivreController {

    private final DocumentService documentService;
    private final EmpruntService empruntService;

    public LivreController(EmpruntService empruntService, DocumentService documentService) {
        this.documentService = documentService;
        this.empruntService = empruntService;
    }

    //Créer un livre
    @PostMapping("/creer-livre")
    public ResponseEntity<?> enregistrerLivre(@RequestBody LivreDTO livreDTO) {

        Livre livreEntity = LivreDTO.toEntity(livreDTO, Livre.class);
        documentService.createDocument("Livre", livreEntity);

        return ResponseEntity.ok().body(livreEntity);
    }

    //Lister les livres
    @GetMapping("/livres")
    @ResponseBody
    public List<LivreDTO> listLivres() {
        return documentService.getLivres();
    }

    // Emprunter un livre
    @PostMapping("/emprunter-livre")
    public ResponseEntity<?> emprunterDocument(@RequestBody Map<String, Long> payload) {
        Long emprunteurId = payload.get("emprunteurId");
        Long documentId = payload.get("documentId");
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetourPrevue = dateEmprunt.plusDays(14);
        try {
            empruntService.addEmprunt(emprunteurId, documentId, dateEmprunt, dateRetourPrevue);

            // Diminuer le stock du livre
            empruntService.decrementStock(documentId);

            return ResponseEntity.ok().body(emprunteurId);
        } catch (CopiesEpuiseesException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/error/" + e.getMessage()));
        }
    }

    // Retourner un livre
    @PostMapping("/retourner-livre/{id}")
    public void retournerLivre(@PathVariable Long id) {
        LocalDate dateRetourEffectue = LocalDate.now();
        empruntService.returnEmprunt(id, dateRetourEffectue);

        // Augmenter le stock du livre
        empruntService.incrementStock(id);
    }

    @PostMapping("/retourner-livre-retard/{id}")
    public void retournerLivreAvecRetard(@PathVariable Long id) {
        
        // Retourne le livre avec un mois de retard
        LocalDate dateRetourEffectue = LocalDate.now().plusMonths(1);
        empruntService.returnEmprunt(id, dateRetourEffectue);

        // Augmenter le stock du livre
        empruntService.incrementStock(id);
    }

    @PostMapping("/payer-amende/{id}")
    public void payerAmende(@PathVariable Long id) {
        empruntService.payerAmende(id);
    }
}