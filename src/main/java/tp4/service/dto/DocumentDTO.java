package tp4.service.dto;

import lombok.*;
import org.springframework.beans.BeanUtils;
import tp4.model.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class DocumentDTO {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private int pages;
    private String genre;
    private int stock = 3;

    public static <T extends Document> T toEntity(DocumentDTO dto, Class<T> documentClass) {
        try {
            T document = documentClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, document);
            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <D extends DocumentDTO, T extends Document> D toDTO(T document, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(document, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}