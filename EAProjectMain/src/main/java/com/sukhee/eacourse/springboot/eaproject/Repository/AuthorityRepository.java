package com.sukhee.eacourse.springboot.eaproject.Repository;
import com.sukhee.eacourse.springboot.eaproject.Domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
