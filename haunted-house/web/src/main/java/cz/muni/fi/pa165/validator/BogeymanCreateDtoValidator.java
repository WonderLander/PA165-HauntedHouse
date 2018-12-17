package cz.muni.fi.pa165.validator;

import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BogeymanCreateDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BogeymanCreateDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BogeymanCreateDto bogeymanCreateDto = (BogeymanCreateDto) target;
        if (bogeymanCreateDto.getName() == null) return;
        if (bogeymanCreateDto.getDescription().length() < 5) return;
    }
}
