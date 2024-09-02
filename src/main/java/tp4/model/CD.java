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
@DiscriminatorValue("CD")

public class CD extends Document {
	@Column(name = "artist")
	private String artist;

	public static CD createCD(String title, String author, String publisher, int publicationYear, String genre, String artist) {
		CD cd = new CD();
		cd.setTitle(title);
		cd.setAuthor(author);
		cd.setPublisher(publisher);
		cd.setPublicationYear(publicationYear);
		cd.setGenre(genre);
		cd.setArtist(artist);
		return cd;
	}
}