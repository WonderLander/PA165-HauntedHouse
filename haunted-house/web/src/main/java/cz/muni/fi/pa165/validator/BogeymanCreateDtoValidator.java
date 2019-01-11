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
//        {
//            errors.rejectValue("name", "Name must not be empty.");
//        }
        if (bogeymanCreateDto.getHauntStartTime() == null) return;
//        {
//            errors.rejectValue("hauntStartTime",
//                    "Time must be in the specified format and have valid value.");
//        }
        if (bogeymanCreateDto.getHauntEndTime() == null) return;
//        {
//            errors.rejectValue("hauntEndTime", "Time must be in the specified format and have valid value.");
//        }
        if ((bogeymanCreateDto.getHauntStartTime() != null)&&(bogeymanCreateDto.getHauntStartTime().after(bogeymanCreateDto.getHauntEndTime()))) {
            errors.rejectValue("hauntStartTime", null, "Start time must be after end time.");
        }
    }
}
