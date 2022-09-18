package io.github.rkeeves.bootmvcformcrud.repository;

import io.github.rkeeves.bootmvcformcrud.entity.Account;
import io.github.rkeeves.bootmvcformcrud.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByAuthor(Account author);

    @Modifying
    @Query("UPDATE Todo todo SET todo.completed = true WHERE todo.id = :id AND todo.author = :author")
    void markAsCompleteByIdAndAuthor(@Param("id") Long id, @Param("author") Account author);

    @Modifying
    @Query("DELETE FROM Todo todo WHERE todo.id = :id AND todo.author = :author")
    void deleteByIdAndAuthor(@Param("id") Long id, @Param("author") Account author);
}
