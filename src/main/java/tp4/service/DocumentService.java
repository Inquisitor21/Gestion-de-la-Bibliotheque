package tp4.service;

import org.springframework.stereotype.Service;
import tp4.model.CD;
import tp4.model.DVD;
import tp4.model.Livre;
import tp4.model.ManuelScolaire;
import tp4.repository.vides.CDRepository;
import tp4.repository.vides.DVDRepository;
import tp4.repository.vides.LivreRepository;
import tp4.repository.vides.ManuelScolaireRepository;
import tp4.service.dto.CDDTO;
import tp4.service.dto.DVDDTO;
import tp4.service.dto.LivreDTO;
import tp4.service.dto.ManuelScolaireDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final CDRepository cdRepository;
    private final DVDRepository dvdRepository;
    private final LivreRepository livreRepository;
    private final ManuelScolaireRepository manuelScolaireRepository;

    public DocumentService(CDRepository cdRepository,
                           DVDRepository dvdRepository,
                           LivreRepository livreRepository,
                           ManuelScolaireRepository manuelScolaireRepository)
    {
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.livreRepository = livreRepository;
        this.manuelScolaireRepository = manuelScolaireRepository;
    }

    public List<LivreDTO> getLivres() {
        List<Livre> livres =  livreRepository.findAll();
        return livres.stream()
                .map(LivreDTO::toDTO)
                .collect(Collectors.toList());
    }

    public void createDocument(String documentType, Object entity) {
        switch (documentType) {
            case "Livre":
                Livre livre = (Livre) entity;
                livreRepository.save(livre);
                break;
            case "CD":
                CD cd = (CD) entity;
                cdRepository.save(cd);
                break;
            case "DVD":
                DVD dvd = (DVD) entity;
                dvdRepository.save(dvd);
                break;
            case "ManuelScolaire":
                ManuelScolaire manuelScolaire = (ManuelScolaire) entity;
                manuelScolaireRepository.save(manuelScolaire);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type: " + documentType);
        }
    }

    public Object getDocumentById(Long id, String documentType) {
        switch (documentType) {
            case "Livre":
                Livre livre = livreRepository.findById(id).orElse(null);
	            assert livre != null;
	            return LivreDTO.toDTO(livre);
            case "CD":
                CD cd = cdRepository.findById(id).orElse(null);
	            assert cd != null;
	            return CDDTO.toDTO(cd);
            case "DVD":
                DVD dvd = dvdRepository.findById(id).orElse(null);
	            assert dvd != null;
	            return DVDDTO.toDTO(dvd);
            case "ManuelScolaire":
                ManuelScolaire manuelScolaire = manuelScolaireRepository.findById(id).orElse(null);
	            assert manuelScolaire != null;
	            return ManuelScolaireDTO.toDTO(manuelScolaire);
            default:
                throw new IllegalArgumentException("Invalid document type: " + documentType);
        }
    }

    public void updateDocument(String documentType, Object dto) {
        switch (documentType) {
            case "Livre":
                LivreDTO livreDTO = (LivreDTO) dto;
                Livre livre = LivreDTO.toEntity(livreDTO);
                if (livreRepository.existsById(livre.getId())) {
                    livreRepository.save(livre);
                }
                break;
            case "CD":
                CDDTO cdDTO = (CDDTO) dto;
                CD cd = CDDTO.toEntity(cdDTO);
                if (cdRepository.existsById(cd.getId())) {
                    cdRepository.save(cd);
                }
                break;
            case "DVD":
                DVDDTO dvdDTO = (DVDDTO) dto;
                DVD dvd = DVDDTO.toEntity(dvdDTO);
                if (dvdRepository.existsById(dvd.getId())) {
                    dvdRepository.save(dvd);
                }
                break;
            case "ManuelScolaire":
                ManuelScolaireDTO manuelScolaireDTO = (ManuelScolaireDTO) dto;
                ManuelScolaire manuelScolaire = ManuelScolaireDTO.toEntity(manuelScolaireDTO);
                if (manuelScolaireRepository.existsById(manuelScolaire.getId())) {
                    manuelScolaireRepository.save(manuelScolaire);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid document type: " + documentType);
        }
    }

    public void deleteDocument(Long id, String documentType) {
        Object document = getDocumentById(id, documentType);
        if (document == null) {
            System.out.println("Le " + documentType + " n'existe pas.");
            return;
        }

        switch (documentType) {
            case "Livre":
	            livreRepository.findById(id).ifPresent(livreRepository::delete);
	            break;
            case "CD":
	            cdRepository.findById(id).ifPresent(cdRepository::delete);
	            break;
            case "DVD":
	            dvdRepository.findById(id).ifPresent(dvdRepository::delete);
	            break;
            case "ManuelScolaire":
	            manuelScolaireRepository.findById(id).ifPresent(manuelScolaireRepository::delete);
	            break;
            default:
                throw new IllegalArgumentException("Invalid document type: " + documentType);
        }
        System.out.println("Le " + documentType + " a été supprimé avec succès.");
    }

    public List<?> findBy(String documentType, String searchType, String value) {
        Map<String, DocumentSearchFunction> searchFunctions = new HashMap<>();

        if ("Livre".equals(documentType)) {
            searchFunctions.put("title", dtoValue -> LivreDTO.toDTOs(livreRepository.findByTitleContaining(value)));
            searchFunctions.put("author", dtoValue -> LivreDTO.toDTOs(livreRepository.findByAuthorContaining(value)));
            searchFunctions.put("publicationYear", dtoValue -> LivreDTO.toDTOs(livreRepository.findByPublicationYear(Integer.parseInt(value))));
            searchFunctions.put("genre", dtoValue -> LivreDTO.toDTOs(livreRepository.findByGenreContaining(value)));
        } else if ("CD".equals(documentType)) {
            searchFunctions.put("title", dtoValue -> CDDTO.toDTOs(cdRepository.findByTitleContaining(value)));
            searchFunctions.put("author", dtoValue -> CDDTO.toDTOs(cdRepository.findByAuthorContaining(value)));
            searchFunctions.put("publicationYear", dtoValue -> CDDTO.toDTOs(cdRepository.findByPublicationYear(Integer.parseInt(value))));
            searchFunctions.put("genre", dtoValue -> CDDTO.toDTOs(cdRepository.findByGenreContaining(value)));
        } else if ("DVD".equals(documentType)) {
            searchFunctions.put("title", dtoValue -> DVDDTO.toDTOs(dvdRepository.findByTitleContaining(value)));
            searchFunctions.put("author", dtoValue -> DVDDTO.toDTOs(dvdRepository.findByAuthorContaining(value)));
            searchFunctions.put("publicationYear", dtoValue -> DVDDTO.toDTOs(dvdRepository.findByPublicationYear(Integer.parseInt(value))));
            searchFunctions.put("genre", dtoValue -> DVDDTO.toDTOs(dvdRepository.findByGenreContaining(value)));
        } else if ("ManuelScolaire".equals(documentType)) {
            searchFunctions.put("title", dtoValue -> ManuelScolaireDTO.toDTOs(manuelScolaireRepository.findByTitleContaining(value)));
            searchFunctions.put("author", dtoValue -> ManuelScolaireDTO.toDTOs(manuelScolaireRepository.findByAuthorContaining(value)));
            searchFunctions.put("publicationYear", dtoValue -> ManuelScolaireDTO.toDTOs(manuelScolaireRepository.findByPublicationYear(Integer.parseInt(value))));
            searchFunctions.put("genre", dtoValue -> ManuelScolaireDTO.toDTOs(manuelScolaireRepository.findByGenreContaining(value)));
        }

        DocumentSearchFunction searchFunction = searchFunctions.get(searchType);
        if (searchFunction == null) {
            throw new IllegalArgumentException("Invalid search type: " + searchType);
        }

        return searchFunction.apply(value);
    }
}