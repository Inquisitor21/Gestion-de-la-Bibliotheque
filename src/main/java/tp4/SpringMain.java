package tp4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp4.model.*;
import tp4.service.DocumentService;
import tp4.service.EmpruntService;
import tp4.service.UtilisateurService;
import tp4.service.dto.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringMain implements CommandLineRunner {

    private final UtilisateurService utilisateurService;
    private final DocumentService documentService;
    private final EmpruntService empruntService;

    public SpringMain(
            UtilisateurService utilisateurService,
            DocumentService documentService,
            EmpruntService empruntService) {
        this.utilisateurService = utilisateurService;
        this.documentService = documentService;
        this.empruntService = empruntService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }

    @Override
    public void run(String... args) {



        // CREATION DES DONNEES POUR LES TESTS SUR COMMAND LINE
        Utilisateur toto = Utilisateur.createUtilisateur("Emprunteur", "Coutelen", "Thomas", "123 rue Pomme", "111-111-1111", "thomas_coutelen@gmail.com", "Emprunteur");
        EmprunteurDTO emprunteurToto = toto.toEmprunteurDTO();
        utilisateurService.addUtilisateur(emprunteurToto, "Emprunteur");

        Utilisateur francois = Utilisateur.createUtilisateur("Prepose", "Lacoursière", "François", "456 rue Banane", "222-222-2222", "francois_lacoursiere@gmail.com", "Prepose");
        PreposeDTO preposeFrancois = francois.toPreposeDTO();
        utilisateurService.addUtilisateur(preposeFrancois, "Prepose");

        documentService.createDocument("Livre", Livre.createLivre("Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Bloomsbury", 1998, 251, "Fantasy", "978-0-7475-3849-0"));
        documentService.createDocument("CD", CD.createCD("The Dark Side of the Moon", "Pink Floyd", "Harvest, EMI", 1973, "Progressive Rock", "Pink Floyd"));
        documentService.createDocument("DVD", DVD.createDVD("The Shawshank Redemption", "Frank Darabont", "Castle Rock Entertainment", 1994, "Drama", "Frank Darabont"));
        documentService.createDocument("ManuelScolaire", ManuelScolaire.createManuelScolaire("Java Programming", "Joyce Farrell", "Cengage Learning", 2021, 1152, "Informatique", "978-0357356025", 5));



        // Test LivreService
        System.out.println("\n\n\n=== Test LivreService ===");

        System.out.println("\n1. getLivreById");
        LivreDTO livreDTO = (LivreDTO) documentService.getDocumentById(1L, "Livre");
        System.out.println(livreDTO.toString());

        System.out.println("\n2. findByTitleContaining");
        documentService.findBy("Livre", "title", "Harry").forEach(System.out::println);

        System.out.println("\n3. findByAuthorContaining");
        documentService.findBy("Livre", "author", "Rowling").forEach(System.out::println);

        System.out.println("\n4. findByPublicationYear");
        documentService.findBy("Livre", "publicationYear", "1998").forEach(System.out::println);

        System.out.println("\n5. findByGenreContaining");
        documentService.findBy("Livre", "genre", "Fantasy").forEach(System.out::println);

        System.out.println("\n6. updateLivre");
        LivreDTO updatedLivreDTO = new LivreDTO();
        updatedLivreDTO.setId(1L);
        updatedLivreDTO.setAuthor("Updated");
        updatedLivreDTO.setPublisher("Updated");
        updatedLivreDTO.setPublicationYear(9999);
        updatedLivreDTO.setPages(9999);
        updatedLivreDTO.setGenre("Updated");
        updatedLivreDTO.setIsbn("Updated");
        updatedLivreDTO.setTitle("Updated");
        documentService.updateDocument("Livre", updatedLivreDTO);
        System.out.println(documentService.getDocumentById(1L, "Livre").toString());

        System.out.println("\n7. deleteLivre");
        documentService.deleteDocument(1L, "Livre");



        // Test CDService
        System.out.println("\n\n\n=== Test CDService ===");

        System.out.println("\n1. getCDs");
        CDDTO cdDTO = (CDDTO) documentService.getDocumentById(2L, "CD");
        System.out.println(cdDTO.toString());

        System.out.println("\n2. findByTitleContaining");
        documentService.findBy("CD", "title","The Dark Side").forEach(System.out::println);

        System.out.println("\n3. findByAuthorContaining");
        documentService.findBy("CD", "author","Pink Floyd").forEach(System.out::println);

        System.out.println("\n4. findByPublicationYear");
        documentService.findBy("CD", "publicationYear","1973").forEach(System.out::println);

        System.out.println("\n5. findByGenreContaining");
        documentService.findBy("CD", "genre","Progressive Rock").forEach(System.out::println);

        System.out.println("\n6. updateCD");
        CDDTO updatedCDDTO = new CDDTO();
        updatedCDDTO.setId(2L);
        updatedCDDTO.setAuthor("Updated");
        updatedCDDTO.setPublisher("Updated");
        updatedCDDTO.setPublicationYear(9999);
        updatedCDDTO.setGenre("Updated");
        updatedCDDTO.setArtist("Updated");
        updatedCDDTO.setTitle("Updated");
        documentService.updateDocument("CD", updatedCDDTO);
        System.out.println(documentService.getDocumentById(2L, "CD").toString());

        System.out.println("\n7. deleteCD");
        documentService.deleteDocument(2L, "CD");



        // Test DVDService
        System.out.println("\n\n\n=== Test DVDService ===");

        System.out.println("\n1. getDVD");
        DVDDTO dvdDTO = (DVDDTO) documentService.getDocumentById(3L, "DVD");
        System.out.println(dvdDTO.toString());

        System.out.println("\n2. findByTitleContaining");
        documentService.findBy("DVD", "title","The Shawshank Redemption").forEach(System.out::println);

        System.out.println("\n3. findByAuthorContaining");
        documentService.findBy("DVD", "author","Frank Darabont").forEach(System.out::println);

        System.out.println("\n4. findByPublicationYear");
        documentService.findBy("DVD", "publicationYear","1994").forEach(System.out::println);

        System.out.println("\n5. findByGenreContaining");
        documentService.findBy("DVD", "genre","Drama").forEach(System.out::println);

        System.out.println("\n6. updateDVD");
        DVDDTO updatedDVDDTO = new DVDDTO();
        updatedDVDDTO.setId(3L);
        updatedDVDDTO.setAuthor("Updated");
        updatedDVDDTO.setPublisher("Updated");
        updatedDVDDTO.setPublicationYear(9999);
        updatedDVDDTO.setGenre("Updated");
        updatedDVDDTO.setDirector("Updated");
        updatedDVDDTO.setTitle("Updated");
        documentService.updateDocument("DVD", updatedDVDDTO);
        System.out.println(documentService.getDocumentById(3L, "DVD").toString());

        System.out.println("\n7. deleteDVD");
        documentService.deleteDocument(3L, "DVD");



        // Test ManuelScolaireService
        System.out.println("\n\n\n=== Test ManuelScolaireService ===");

        System.out.println("\n1. getManuelScolaire");
        ManuelScolaireDTO manuelScolaireDTO = (ManuelScolaireDTO) documentService.getDocumentById(4L, "ManuelScolaire");
        System.out.println(manuelScolaireDTO.toString());

        System.out.println("\n2. findByTitleContaining");
        documentService.findBy("ManuelScolaire", "title","Java Programming").forEach(System.out::println);

        System.out.println("\n3. findByAuthorContaining");
        documentService.findBy("ManuelScolaire", "author","Joyce Farrell").forEach(System.out::println);

        System.out.println("\n4. findByPublicationYear");
        documentService.findBy("ManuelScolaire", "publicationYear","2021").forEach(System.out::println);

        System.out.println("\n5. findByGenreContaining");
        documentService.findBy("ManuelScolaire", "genre","Informatique").forEach(System.out::println);

        System.out.println("\n6. updateManuel");
        ManuelScolaireDTO updatedManuelScolaireDTO = new ManuelScolaireDTO();
        updatedManuelScolaireDTO.setId(4L);
        updatedManuelScolaireDTO.setAuthor("Updated");
        updatedManuelScolaireDTO.setPublisher("Updated");
        updatedManuelScolaireDTO.setPublicationYear(9999);
        updatedManuelScolaireDTO.setPages(9999);
        updatedManuelScolaireDTO.setGenre("Updated");
        updatedManuelScolaireDTO.setNiveauSecondaire(999);
        updatedManuelScolaireDTO.setTitle("Updated");
        documentService.updateDocument("ManuelScolaire", updatedManuelScolaireDTO);
        System.out.println(documentService.getDocumentById(4L, "ManuelScolaire").toString());

        System.out.println("\n7. deleteManuel");
        documentService.deleteDocument(4L, "ManuelScolaire");



        // Test EmprunteurService
        System.out.println("\n\n\n=== Test EmprunteurService ===");

        System.out.println("\n1. getEmprunteurById");
        EmprunteurDTO emprunteurDTO = (EmprunteurDTO) utilisateurService.getUtilisateurById(1L, "Emprunteur");
        System.out.println(emprunteurDTO.toString());

        System.out.println("\n2. updateEmprunteur");
        emprunteurDTO.setPrenom("Updated");
        emprunteurDTO.setNom("Updated");
        emprunteurDTO.setAdresse("Updated");
        emprunteurDTO.setTelephone("Updated");
        emprunteurDTO.setEmail("Updated");
        utilisateurService.updateUtilisateur(emprunteurDTO, "Emprunteur");
        System.out.println(utilisateurService.getUtilisateurById(emprunteurDTO.getId(), "Emprunteur"));

        System.out.println("\n3. deleteEmprunteur");
        utilisateurService.deleteUtilisateur(emprunteurDTO.getId(), "Emprunteur");



        // Test PreposeService
        System.out.println("\n\n\n=== Test PreposeService ===");

        System.out.println("\n1. getPreposeById");
        PreposeDTO preposeDTO = (PreposeDTO) utilisateurService.getUtilisateurById(2L, "Prepose");
        System.out.println(preposeDTO.toString());

        System.out.println("\n2. updatePrepose");
        preposeDTO.setPrenom("Updated");
        preposeDTO.setNom("Updated");
        preposeDTO.setAdresse("Updated");
        preposeDTO.setTelephone("Updated");
        preposeDTO.setEmail("Updated");
        preposeDTO.setRole("Updated");
        utilisateurService.updateUtilisateur(preposeDTO, "Prepose");
        System.out.println(utilisateurService.getUtilisateurById(preposeDTO.getId(), "Prepose"));

        System.out.println("\n3. deletePrepose");
        utilisateurService.deleteUtilisateur(preposeDTO.getId(), "Prepose");



        // RECREATION DES DONNEES ADDITIONNELLES POUR LES TESTS SUR LE WEB ET SUR COMMAND LINE
        Utilisateur vazgen = Utilisateur.createUtilisateur("Emprunteur", "Markrayan", "Vazgen", "456 rue Banane", "333-333-3333", "vazgen_markaryan@gmail.com", "Emprunteur");
        EmprunteurDTO emprunteurVazgen = vazgen.toEmprunteurDTO();
        utilisateurService.addUtilisateur(emprunteurVazgen, "Emprunteur");
        
        Utilisateur john = Utilisateur.createUtilisateur("Emprunteur", "Doe", "John", "777 rue Cerise", "444-444-4444", "john_doe@gmail.com", "Emprunteur");
        EmprunteurDTO emprunteurJohn = john.toEmprunteurDTO();
        utilisateurService.addUtilisateur(emprunteurJohn, "Emprunteur");

        documentService.createDocument("Livre", Livre.createLivre("Stalker", "Arkadi et Boris Strougatski", "Denoël", 1972, 256, "Science-fiction", "978-2-207-30347-4"));
        documentService.createDocument("ManuelScolaire", ManuelScolaire.createManuelScolaire("Mathématiques", "Jean-Pierre Mercier", "Chenelière Éducation", 2016, 400, "Mathématiques", "978-2-7650-3124-7", 5));
        documentService.createDocument("CD", CD.createCD("Greatest Hits", "Queen", "EMI, Parlophone", 1981, "Rock", "Queen"));



        // TESTS SUR LE COMMAND LINE
        System.out.println("\n\n\n=== Test Command Line ===\n");

        // Emprunter des documents
        empruntService.addEmprunt(utilisateurService.getUtilisateurById(3L, "Emprunteur").getId(), ((LivreDTO) documentService.getDocumentById(5L, "Livre")).getId(), LocalDate.now(), LocalDate.now().plusWeeks(2));
        empruntService.addEmprunt(utilisateurService.getUtilisateurById(3L, "Emprunteur").getId(), ((ManuelScolaireDTO) documentService.getDocumentById(6L, "ManuelScolaire")).getId(), LocalDate.now(), LocalDate.now().plusWeeks(2));
        empruntService.addEmprunt(utilisateurService.getUtilisateurById(3L, "Emprunteur").getId(), ((CDDTO) documentService.getDocumentById(7L, "CD")).getId(), LocalDate.now(), LocalDate.now().plusWeeks(2));

        // Retourner des documents
        empruntService.returnEmprunt(1L, LocalDate.now().plusMonths(1)); // Cet emprunt est retourné en retard
        empruntService.returnEmprunt(2L, LocalDate.now().plusDays(6)); // Cet emprunt est retourné au temps

        // Print emprunts
        List<EmpruntDTO> emprunts = empruntService.getEmpruntsByEmprunteurId(utilisateurService.getUtilisateurById(3L, "Emprunteur").getId());
        printEmprunts(emprunts, utilisateurService.getUtilisateurById(3L, "Emprunteur").getId(), empruntService, utilisateurService, LocalDate.now());

        //Pour ne pas polluer la BD les emprunts sont supprimés à la fin des Tests Sur Command Line.
        empruntService.deleteAllEmprunts();
    }

    public static void printEmprunts(List<EmpruntDTO> emprunts, Long emprunteurID, EmpruntService empruntService, UtilisateurService utilisateurService, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateurById(emprunteurID, "Emprunteur");
        System.out.println("Emprunts de " + utilisateur.getPrenom() + ":");
        System.out.println("Date d'aujourd'hui: " + date.format(formatter) + "\n");
        int increment = 1;
        Map<String, Integer> lateFees = empruntService.getEmprunteurFees(emprunteurID);
        for (EmpruntDTO chaqueEmprunt : emprunts) {
            System.out.println(increment + ") " + chaqueEmprunt.getDocument().getTitle() + " de: " + chaqueEmprunt.getDocument().getAuthor());
            System.out.println("Date d'emprunt: " + chaqueEmprunt.getDateEmprunt().format(formatter));
            System.out.println("Date de retour: " + chaqueEmprunt.getDateRetourPrevue().format(formatter));
            if (chaqueEmprunt.getDateRetourEffectue() != null) {
                System.out.println("Date de retour effective: " + chaqueEmprunt.getDateRetourEffectue().format(formatter));
            } else {
                System.out.println("Date de retour effective: Pas encore retourné");
                long daysLate = ChronoUnit.DAYS.between(chaqueEmprunt.getDateRetourPrevue().plusDays(1), date);
                if (daysLate > 0) {
                    System.out.println("Nombre de jours dépassant la date de retour: " + daysLate);
                }
            }
            Integer fee = lateFees.get(chaqueEmprunt.getDocument().getTitle());
            System.out.println("Frais de retard: " + (fee != null ? fee + "$ (1$ par jour)" : "Non applicable"));
            System.out.println();
            increment++;
        }
    }
}