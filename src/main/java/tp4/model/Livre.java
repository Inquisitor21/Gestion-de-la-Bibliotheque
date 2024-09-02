package tp4.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Livre")

public class Livre extends Document {
	@Column(name = "isbn")
	private String isbn;

	public static Livre createLivre(String title, String author, String publisher, int publicationYear, int pages, String genre, String isbn) {
		Livre livre = new Livre();
		livre.setTitle(title);
		livre.setAuthor(author);
		livre.setPublisher(publisher);
		livre.setPublicationYear(publicationYear);
		livre.setPages(pages);
		livre.setGenre(genre);
		livre.setIsbn(isbn);
		return livre;
	}
}