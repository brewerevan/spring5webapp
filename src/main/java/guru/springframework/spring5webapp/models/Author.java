package guru.springframework.spring5webapp.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {

    @Id // state that this is the id for the JPA Entity
    @GeneratedValue(strategy = GenerationType.AUTO) // states that the database will be managed by the id
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") // author has many books, book has many authors
    private Set<Book> books = new HashSet<>(); // want to initialize as an empty hashset by default

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    // overriding equals and hashCode for equality checking when using hibernate
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // providing my own toString method for debuggings
    @Override
    public String toString(){
        return "Author{" +
        "id: " + id +
        ", firstName: " + firstName +
        ", lastName: " + lastName +
        "}";
    }
}
