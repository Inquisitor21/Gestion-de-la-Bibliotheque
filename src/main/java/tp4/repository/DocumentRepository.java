package tp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp4.model.Document;

import java.util.List;

public interface DocumentRepository<T extends Document, ID> extends JpaRepository<T, ID> {
    
    @Query("SELECT t FROM #{#entityName} t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<T> findByTitleContaining(@Param("title") String title);

    @Query("SELECT t FROM #{#entityName} t WHERE LOWER(t.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<T> findByAuthorContaining(@Param("author") String author);

    @Query("SELECT t FROM #{#entityName} t WHERE t.publicationYear = :year")
    List<T> findByPublicationYear(@Param("year") int year);

    @Query("SELECT t FROM #{#entityName} t WHERE LOWER(t.genre) = LOWER(:genre)")
    List<T> findByGenreContaining(@Param("genre") String genre);
}