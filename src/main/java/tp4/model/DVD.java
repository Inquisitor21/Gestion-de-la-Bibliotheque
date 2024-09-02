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
@DiscriminatorValue("DVD")

public class DVD extends Document {
	@Column(name = "director")
	private String director;

	public static DVD createDVD(String title, String author, String publisher, int publicationYear, String genre, String director) {
		DVD dvd = new DVD();
		dvd.setTitle(title);
		dvd.setAuthor(author);
		dvd.setPublisher(publisher);
		dvd.setPublicationYear(publicationYear);
		dvd.setGenre(genre);
		dvd.setDirector(director);
		return dvd;
	}
}