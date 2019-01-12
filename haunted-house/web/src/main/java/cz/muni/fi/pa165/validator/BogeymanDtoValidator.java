package cz.muni.fi.pa165.validator;

import cz.muni.fi.pa165.dto.BogeymanDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BogeymanDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BogeymanDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BogeymanDto bogeymanDto = (BogeymanDto) target;
        if (bogeymanDto.getName() == null) return;
//        {
//            errors.rejectValue("name", "Name must not be empty.");
//        }
        if (bogeymanDto.getHauntStartTime() == null) return;
//        {
//            errors.rejectValue("hauntStartTime",
//                    "Time must be in the specified format and have valid value.");
//        }
        if (bogeymanDto.getHauntEndTime() == null) return;
//        {
//            errors.rejectValue("hauntEndTime", "Time must be in the specified format and have valid value.");
//        }
        if ((bogeymanDto.getHauntStartTime() != null) && (bogeymanDto.getHauntStartTime().after(bogeymanDto.getHauntEndTime()))) {
            errors.rejectValue("hauntStartTime", null, "Start time must not be after end time.");
        }
    }
}