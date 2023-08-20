package com.dzhanrafetov.melifera.service;


import com.dzhanrafetov.melifera.dto.UserDto;
import com.dzhanrafetov.melifera.dto.converters.UserDtoConverter;
import com.dzhanrafetov.melifera.dto.requests.CreateUserRequest;
import com.dzhanrafetov.melifera.exception.DuplicateEntryException;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.ConfirmationToken;
import com.dzhanrafetov.melifera.model.User;
import com.dzhanrafetov.melifera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, ConfirmationTokenService confirmationTokenService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public List<UserDto> getInActiveUsers() {
        return userDtoConverter.convert(userRepository.findUserByIsActiveFalse());
    }

    public List<UserDto> getActiveUsers() {

        return userDtoConverter.convert(userRepository.findUserByIsActiveTrue());
    }

    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto getUser() {
        User user = findUserById(getCurrentUser().getId());
        return userDtoConverter.convert(user);
    }


    public UserDto createUser(CreateUserRequest userRequest) {
        validateUniqueUsernameAndEmail(userRequest.getUsername(), userRequest.getMail());

        User user = createUserFromRequest(userRequest);
        userRepository.save(user);
        verificationByUser(user.getId());

        return userDtoConverter.convert(user);
    }

    private void validateUniqueUsernameAndEmail(String username, String email) {
        if (userRepository.existsUserByUsername(username) || userRepository.existsUserByMail(email)) {
            throw new DuplicateEntryException("Username or email already exists");
        }
    }

    private User createUserFromRequest(CreateUserRequest userRequest) {
        return new User(
                userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getMail(),
                LocalDateTime.now(),
                false
        );
    }


    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = findUserByUsername(username);
        return userDtoConverter.convert(user);
    }

    public void deactivateUser(Long id) {
        changeActivateUser(id, false);
    }

    public void activateUser(Long id) {
        changeActivateUser(id, true);

    }


    public void deleteUser(Long id) {
        if (doesUserExists(id)) {
            confirmationTokenService.deleteConfirmationtoken(id);
            userRepository.deleteById(id);

        } else {
            throw new NotFoundException("Deletion error! " +
                    "User couldn't be found by following id:  " + id);
        }
    }

    private void changeActivateUser(Long id, Boolean isActive) {
        User userRequest = findUserById(id);
        User updatedUser = new User(
                userRequest.getId(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getMail(),
                userRequest.getCreationTime()
                , isActive);
        userRepository.save(updatedUser);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User couldn't found by id: " + id));
    }

    private User findUserByMail(String mail) {
        return userRepository.findByMail(mail).orElseThrow(() -> new NotFoundException("User couldn't found by mail: " + mail));

    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User couldn't found by username: " + username));

    }

    private boolean doesUserExists(Long id) {
        return userRepository.existsById(id);
    }

    public Boolean isUserIdExist(Long id) {
        return userRepository.existsById(id);
    }


    @Value("${confirmation.url}")
    private String appUrl;

    public void verificationByUser(Long id) {
        User user = findUserById(id);

        String token = UUID.randomUUID().toString();
        if (!user.getActive()) {
            ConfirmationToken confirmationToken = new ConfirmationToken(token, user);
            confirmationTokenService.saveConfirmationToken(confirmationToken);

            String link = appUrl + "/v1/user/confirm?token=" + token;
            emailService.sendMail(user.getMail(), "Confirm mail", link);

        } else {
            throw new IllegalStateException("email already confirmed");
        }
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));


        activateUser(confirmationToken.getUser().getId());
        confirmationTokenService.deleteConfirmationtoken(confirmationToken.getUser().getId());  // Delete the confirmation token

        return "Your account is confirmed";

    }


    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return getUserByUsername(currentUsername);
        } else {
            throw new NotFoundException("user not found");
        }

    }


}
