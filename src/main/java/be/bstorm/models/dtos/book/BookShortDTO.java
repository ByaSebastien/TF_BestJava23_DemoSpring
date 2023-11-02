package be.bstorm.models.dtos.book;

import be.bstorm.models.entities.Book;
import lombok.Data;

@Data
public class BookShortDTO {

    private Long id;
    private String title;

//    public BookShortDTO(Book book){
//        this.id = book.getId();
//        this.title = book.getTitle();
//    }

    public static BookShortDTO fromEntity(Book book){
        BookShortDTO bookShortDTO = new BookShortDTO();
        bookShortDTO.setId(book.getId());
        bookShortDTO.setTitle(book.getTitle());
        return bookShortDTO;
    }
}
