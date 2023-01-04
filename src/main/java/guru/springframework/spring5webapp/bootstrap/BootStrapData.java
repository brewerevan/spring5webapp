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

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        Publisher bob = new Publisher("Bob's Books", "street", "city", "state", "12345");
        publisherRepository.save(bob);

        eric.getBooks().add(ddd); // Add ddd to eric's books
        ddd.getAuthors().add(eric); // add eric to book's authors


        ddd.setPublisher(bob);

        authorRepository.save(eric); // save eric into the DB
        bookRepository.save(ddd); // save ddd into the DB

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J22EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod); // don't forget to save rod
        bookRepository.save(noEJB); // don't forget to save noEJB

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
    }
}
