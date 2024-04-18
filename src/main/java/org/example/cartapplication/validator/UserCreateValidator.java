package org.example.cartapplication.validator;

import org.example.cartapplication.dto.UserCreateRequestDto;
import org.example.cartapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserCreateValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return UserCreateRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateRequestDto dto = (UserCreateRequestDto) target;
        boolean isSame = dto.password().equals(dto.passwordConfirmation());
        if (!isSame) {
            errors.rejectValue("passwordConfirmation", "Passwords are not the same");
        }
        userRepository.findByUserName(dto.username()).ifPresent(e -> errors.rejectValue("username", "This username already exists"));
    }

}
