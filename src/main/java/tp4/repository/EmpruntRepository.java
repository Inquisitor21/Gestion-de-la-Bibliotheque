package tp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tp4.model.Emprunt;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query("SELECT e FROM Emprunt e WHERE e.emprunteur.id = :id")
    List<Emprunt> getEmpruntsByEmprunteurId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Livre l set l.stock = l.stock + 1 where l.id = :id")
    void incrementStock(Long id);

    @Transactional
    @Modifying
    @Query("update Livre l set l.stock = l.stock - 1 where l.id = :id")
    void decrementStock(Long id);

    @Query("SELECT e.document.id FROM Emprunt e WHERE e.id = :id")
    Long getDocumentIdByEmpruntId(Long id);
}