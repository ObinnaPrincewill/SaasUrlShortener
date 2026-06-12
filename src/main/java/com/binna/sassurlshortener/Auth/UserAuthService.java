package com.binna.sassurlshortener.Auth;

import com.binna.sassurlshortener.Entity.UserInfo;
import com.binna.sassurlshortener.Exceptions.ResourceNotFoundException;
import com.binna.sassurlshortener.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;
    //private final PasswordEncoder encoder;

    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<UserInfo> userInfoOptional = repository.findByEmail(email);
        if (userInfoOptional.isEmpty()) throw new ResourceNotFoundException("User Not Found");
        UserInfo user = userInfoOptional.get();
        Set<GrantedAuthority> authorties = user.getRoles()
                .stream()
                .map(item->new SimpleGrantedAuthority(item.name())).collect(Collectors.toSet());


        return new AuthUserDetail(user.getEmail(), user.getPassword(), authorties);
    }
}
