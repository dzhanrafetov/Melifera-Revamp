package com.dzhanrafetov.melifera.service;


import com.dzhanrafetov.melifera.dto.UserDetailsDto;
import com.dzhanrafetov.melifera.dto.converters.UsersDetailsDtoConverter;
import com.dzhanrafetov.melifera.dto.requests.CreateUserDetailsRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateUserDetailsRequest;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.User;
import com.dzhanrafetov.melifera.model.UserDetails;
import com.dzhanrafetov.melifera.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsService {
    private final UserDetailsRepository repository;
    private final UserService userService;
    private final UsersDetailsDtoConverter converter;

    public UserDetailsService(UserDetailsRepository repository, UserService userService, UsersDetailsDtoConverter converter) {
        this.repository = repository;
        this.userService = userService;
        this.converter = converter;
    }

    public UserDetailsDto createUserDetails(CreateUserDetailsRequest request) {
        User user = userService.findUserById(userService.getCurrentUser().getId());

    UserDetails userDetails = new UserDetails(
            request.getPhoneNumber(),
            request.getAddress(),
            request.getCity(),
            request.getPostCode(),
            request.getState(),
            request.getCountry(),
            user);

        return converter.convert(repository.save(userDetails));

    }


    public UserDetailsDto updateUserDetails(Long userDetailsId, UpdateUserDetailsRequest request) {

        User user = userService.findUserById(userService.getCurrentUser().getId());
        UserDetails userDetails=findUserDetailsByIdAndUser(userDetailsId,user.getId());

        UserDetails updatedUserDetails = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getPostCode(),
                request.getState(),
                request.getCountry(),
                userDetails.getUser());

        return converter.convert(repository.save(updatedUserDetails));
    }
    public UserDetails findUserDetailsByIdAndUser(Long userd_id, Long user_id) {
        return repository.findUserDetailsByIdAndUserId(userd_id,user_id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("UserDetails couldn't found by id:  " + userd_id));
    }

    public void deleteUserDetails(Long id) {
        findUserDetailsById(id);
        repository.deleteById(id);
    }

    private UserDetails findUserDetailsById(Long userDetailsId) {
        return repository.findById(userDetailsId).orElseThrow(() -> new NotFoundException("User details couldn't be found by following id: " + userDetailsId));
    }

}
