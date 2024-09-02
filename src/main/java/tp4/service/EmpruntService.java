package tp4.service;

import org.springframework.stereotype.Service;
import tp4.model.Document;
import tp4.model.Emprunt;
import tp4.model.Emprunteur;
import tp4.presentation.exception.CopiesEpuiseesException;
import tp4.repository.DocumentRepository;
import tp4.repository.EmpruntRepository;
import tp4.repository.vides.EmprunteurRepository;
import tp4.service.dto.EmpruntDTO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmpruntService extends Emprunt {

    private final EmpruntRepository empruntRepository;
    private final EmprunteurRepository emprunteurRepository;
    private final DocumentRepository<Document, Long> documentRepository;
    private final UtilisateurService utilisateurService;

    public EmpruntService(EmpruntRepository empruntRepository,
                          EmprunteurRepository emprunteurRepository,
                          DocumentRepository<Document, Long> documentRepository,
                          UtilisateurService utilisateurService)
    {
        this.empruntRepository = empruntRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
	    this.utilisateurService = utilisateurService;
    }

    public List<EmpruntDTO> getEmpruntsByEmprunteurId(Long emprunteurId) {
        return empruntRepository.getEmpruntsByEmprunteurId(emprunteurId).stream()
                .map(EmpruntDTO::toDTO)
                .collect(Collectors.toList());
    }
    
    public void decrementStock(Long id) {
        empruntRepository.decrementStock(id);
    }

    public void incrementStock(Long id) {
        Long documentId = empruntRepository.getDocumentIdByEmpruntId(id);
        empruntRepository.incrementStock(documentId);
    }

    public void addEmprunt(Long emprunteurId, Long documentId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        Emprunteur emprunteur = emprunteurRepository.findById(emprunteurId)
                .orElseThrow(() -> new IllegalArgumentException("Empunteur avec ID: " + emprunteurId + " n'existe pas"));
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document avec ID: " + documentId + " n'existe pas"));

        // Vérifie si le nombre d'emprunts actifs est inférieur au stock du document
        if (document.getStock() != 0 ) {
            Emprunt emprunt = new Emprunt();
            emprunt.setEmprunteur(emprunteur);
            emprunt.setDocument(document);
            emprunt.setDateEmprunt(dateEmprunt);
            emprunt.setDateRetourPrevue(dateRetourPrevue);
            emprunt.setCopiesLouees(emprunt.getCopiesLouees() + 1); // Incrémente le nombre de copies louées

            empruntRepository.save(emprunt);
        } else {
            throw new CopiesEpuiseesException();
        }
    }

    public void returnEmprunt(Long empruntId, LocalDate dateRetourEffectue) {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);

        if (emprunt == null) {
            throw new IllegalArgumentException("Emprunt avec ID: " + empruntId + " n'existe pas");
        }

        emprunt.setDateRetourEffectue(dateRetourEffectue);

        if (dateRetourEffectue.isAfter(emprunt.getDateRetourPrevue())) {
            long daysLate = ChronoUnit.DAYS.between(emprunt.getDateRetourPrevue(), dateRetourEffectue);
            int fraisRetard = (int) daysLate; //1$ par jour de retard
            emprunt.setFraisRetard(fraisRetard);
        }

        empruntRepository.save(emprunt);
    }

    public void payerAmende(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);

        if (emprunt == null) {
            throw new IllegalArgumentException("Emprunt avec ID: " + empruntId + " n'existe pas");
        }

        emprunt.setFraisRetard(0);
        empruntRepository.save(emprunt);
    }

    public Map<String, Integer> getEmprunteurFees(Long emprunteurID) {
        Map<String, Integer> lateFees = new HashMap<>();

        List<Emprunt> emprunts = empruntRepository.getEmpruntsByEmprunteurId(utilisateurService.getUtilisateurById(emprunteurID, "Emprunteur").getId());

        for (Emprunt emprunt : emprunts) {
            if (emprunt.getDateRetourEffectue() != null && emprunt.getDateRetourEffectue().isAfter(emprunt.getDateRetourPrevue())) {
                int daysLate = (int) ChronoUnit.DAYS.between(emprunt.getDateRetourPrevue(), emprunt.getDateRetourEffectue());
                lateFees.put(emprunt.getDocument().getTitle(), daysLate);
            }
        }

        return lateFees;
    }

    public void deleteAllEmprunts() {
        empruntRepository.deleteAll();
    }
}