package com.sukhee.eacourse.springboot.eaproject.Repository;
import com.sukhee.eacourse.springboot.eaproject.Domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
