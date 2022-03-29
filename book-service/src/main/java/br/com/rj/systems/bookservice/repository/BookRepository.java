package br.com.rj.systems.bookservice.repository;

import br.com.rj.systems.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
