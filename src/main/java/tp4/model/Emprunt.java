package tp4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emprunts")

public class Emprunt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "emprunteur_id")
	private Emprunteur emprunteur;

	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	private LocalDate dateEmprunt;
	private LocalDate dateRetourPrevue;
	private LocalDate dateRetourEffectue;
	private int copiesLouees = 0; // Valeur par défaut
	private int fraisRetard = 0; // Valeur par défaut
}