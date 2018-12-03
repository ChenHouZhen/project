package com.chenhz.elastic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.chenhz.elastic.model.Book;
import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book,String> {

    Page<Book> findByAuthor(String author, Pageable pageable);

    List<Book> findByTitle(String title);

    Book save(Book book);
}
