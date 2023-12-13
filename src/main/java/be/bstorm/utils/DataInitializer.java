package be.bstorm.utils;

import be.bstorm.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookService bookService;
    @Override
    public void run(String... args) throws Exception {

        bookService.getByCriteria("guerre","desc");
    }
}
