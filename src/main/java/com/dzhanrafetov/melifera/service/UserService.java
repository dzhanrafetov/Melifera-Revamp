package com.dzhanrafetov.melifera.service;


import com.dzhanrafetov.melifera.configuration.TokenConfig;
import com.dzhanrafetov.melifera.dto.UserDto;
import com.dzhanrafetov.melifera.dto.converters.UserDtoConverter;
import com.dzhanrafetov.melifera.dto.requests.CreateUserRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateUserPasswordAdminRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateUserPasswordRequest;
import com.dzhanrafetov.melifera.exception.DuplicateEntryException;
import com.dzhanrafetov.melifera.exception.InvalidPasswordException;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.ConfirmationToken;
import com.dzhanrafetov.melifera.model.User;
import com.dzhanrafetov.melifera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    private final TokenConfig tokenConfig;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, ConfirmationTokenService confirmationTokenService, EmailService emailService, PasswordEncoder passwordEncoder, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.tokenConfig = tokenConfig;
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

        // Generate and send the confirmation token
        generateAndSendToken(user);

        return userDtoConverter.convert(user);
    }


    public void updateUserPassword(Long userId, UpdateUserPasswordAdminRequest request) {

        User user = findUserById(userId);

        User updatedUser = new User(
                user.getId(),
                user.getUsername(),
                passwordEncoder.encode(request.getNewPassword()),
                user.getMail(),
                user.getCreationTime(),
                user.getActive());

        userRepository.save(updatedUser);


    }


    @Transactional
    public UserDto updateUserPasswordWithOldPassword(UpdateUserPasswordRequest request) {
        User user = findUserById(getCurrentUser().getId());

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            User updatedUser = new User(
                    user.getId(),
                    user.getUsername(),
                    passwordEncoder.encode(request.getNewPassword()),
                    user.getMail(),
                    user.getCreationTime(),
                    user.getActive());
            return userDtoConverter.convert(userRepository.save(updatedUser));

        } else {
                throw new InvalidPasswordException("The Old password is incorrect");
        }
    }



    public String sendPasswordResetLink(String mail) {
        User user = findUserByMail(mail);

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(tokenConfig.tokenExpirationMinutes());
        ConfirmationToken confirmationToken = new ConfirmationToken(token, user, LocalDateTime.now(), expirationTime);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String resetLink = appUrl + "/v1/user/reset-password?token=" + token;  // Update with your actual reset password URL
        emailService.sendMail(user.getMail(), "Password Reset", "Click the following link to reset your password: " + resetLink);

        return "Password reset link has been sent to your email.";
    }


    @Transactional
    public UserDto resetPassword(String token, UpdateUserPasswordRequest updateUserPasswordRequest) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"));

        User user = confirmationToken.getUser();
        String newPassword = passwordEncoder.encode(updateUserPasswordRequest.getNewPassword());

        User updatedUser = new User(
                user.getId(),
                user.getUsername(),
                newPassword,
                user.getMail(),
                user.getCreationTime(),
                user.getActive());

        confirmationTokenService.deleteConfirmationtoken(confirmationToken.getUser().getId());

        return userDtoConverter.convert(userRepository.save(updatedUser));


    }

    public String resendToken(String mail) {
        User user = findUserByMail(mail);

        if (!user.getActive()) {
            generateAndSendToken(user);
            return "Confirmation token has been resent";
        } else {
            return "User is already active";
        }
    }

    @Value("${confirmation.url}")

    private String appUrl;

    public void generateAndSendToken(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(tokenConfig.tokenExpirationMinutes());
        ConfirmationToken confirmationToken = new ConfirmationToken(token, user, LocalDateTime.now(), expirationTime);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String link = appUrl + "/v1/user/confirm?token=" + token;
        emailService.sendMail(user.getMail(), "Confirm mail",
                "Hello, " + user.getUsername() + "\n" + "This is the confirmation link: " + link);
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
