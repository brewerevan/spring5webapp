package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

// can load the database with dummy data using this file
@Component // tells spring to detect this as a managed component
public class BootStrapData implements CommandLineRunner { // Spring looks for instances of CommandLineRunner and runs
                                                          // them

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootsrap");

        // create publisher
        Publisher publisher = new Publisher();
        publisher.setName("Publisher");
        publisher.setStreet("Street");
        publisher.setCity("City");
        publisher.setState("State");
        publisher.setZip("12345");

        // save publisher
        publisherRepository.save(publisher);

        // create Author
        Author author = new Author();
        author.setFirstName("FN");
        author.setLastName("LN");

        authorRepository.save(author);

        // create Book
        Book book = new Book();
        book.setTitle("Title");
        book.setIsbn("123456789");
        // add author and publisher to book then save
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        bookRepository.save(book);

        // add book to author
        author.getBooks().add(book);
        authorRepository.save(author);

        // add book to publisher
        publisher.getBooks().add(book);
        publisherRepository.save(publisher);

        // print out statistics
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: "+ bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
    }
}
