package com.sukhee.eacourse.springboot.eaproject.Service.Auth;
import com.sukhee.eacourse.springboot.eaproject.Domain.Authority;
import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with username: "+ username+ " not found");
        }
        String userName = user.get().getEmail();
        String password = user.get().getPassword();
        List<SimpleGrantedAuthority> grantedAuthorities = mapAuthorityToSimpleGrantedAuthority(user.get().getAuthorities());
        return new org.springframework.security.core.userdetails.User(userName, password, grantedAuthorities);
    }

    private List<SimpleGrantedAuthority> mapAuthorityToSimpleGrantedAuthority(Set<Authority> authorities) {
        return authorities.stream().map(Authority::getAuthority).map(SimpleGrantedAuthority::new).toList();
    }
}