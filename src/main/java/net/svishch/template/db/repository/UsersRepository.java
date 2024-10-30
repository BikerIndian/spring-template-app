package net.svishch.template.db.repository;

import net.svishch.template.db.entity.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<UserDb, Long> {
    Optional<UserDb> findById(Long id);
    List<UserDb> findAll();
    UserDb findByUsername(String username);
    Optional<UserDb>  findByEmail(String email);
}