package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Entity
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;
}
