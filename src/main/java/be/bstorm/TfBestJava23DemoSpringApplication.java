package be.bstorm;

import be.bstorm.repositories.BookRepository;
import be.bstorm.services.BookService;
import be.bstorm.services.impl.BookServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TfBestJava23DemoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfBestJava23DemoSpringApplication.class, args);

		//L'injection de dépendance agit un peu comme ça
		//Elle reserve dans un emplacement memoire une instance des composant injectable
		//(@Repository,@Service,@Controller,@Compenent,@Bean,@RestController,@Configuration)
		//Qu'on va pouvoir injecter via le constructeur dans d'autre composant(voir dans BookSericeImpl)

//		BookRepository repo = new...()
//		BookService service = new BookServiceImpl();

	}

}
