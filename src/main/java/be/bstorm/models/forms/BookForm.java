package be.bstorm.models.forms;

import be.bstorm.models.entities.Book;
import lombok.Data;

@Data
public class BookForm {

    private String title;
    private String description;

//    Methode qui transforme un bookForm en un Book (Entity)
//    En gardant les valeur qu'il y avait dans le bookForm
//    Mapper
    public Book toEntity(){
        Book book = new Book();
        book.setTitle(this.title);
        book.setDescription(this.description);
        return book;
    }

//    Methode qui transforme un Book(entity) en un BookFrom
//    En gardant les valeur qu'il y avait dans le book(entity)
//    Mapper
    public static BookForm fromEntity(Book book){
        BookForm bookForm = new BookForm();
        bookForm.setTitle(book.getTitle());
        bookForm.setDescription(book.getDescription());
        return bookForm;
    }
}
