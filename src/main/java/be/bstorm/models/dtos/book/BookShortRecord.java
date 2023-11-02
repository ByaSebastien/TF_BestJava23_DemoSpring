package be.bstorm.models.dtos.book;


import be.bstorm.models.entities.Book;

public record BookShortRecord(Long id, String description) {
    public static BookShortRecord fromEntity(Book book){
        return new BookShortRecord(book.getId(), book.getTitle());
    }
}
