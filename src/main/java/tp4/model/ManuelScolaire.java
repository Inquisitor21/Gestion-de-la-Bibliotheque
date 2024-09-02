package tp4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manuels_scolaires")

public class ManuelScolaire extends Livre {
    @Column(name = "niveau_secondaire")
    private int niveauSecondaire;

    public static ManuelScolaire createManuelScolaire(String title, String author, String publisher, int publicationYear, int pages, String genre, String isbn, int niveauSecondaire) {
        ManuelScolaire manuelScolaire = new ManuelScolaire();
        manuelScolaire.setTitle(title);
        manuelScolaire.setAuthor(author);
        manuelScolaire.setPublisher(publisher);
        manuelScolaire.setPublicationYear(publicationYear);
        manuelScolaire.setPages(pages);
        manuelScolaire.setGenre(genre);
        manuelScolaire.setIsbn(isbn);
        manuelScolaire.setNiveauSecondaire(niveauSecondaire);
        return manuelScolaire;
    }
}