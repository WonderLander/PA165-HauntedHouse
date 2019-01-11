package cz.muni.fi.pa165.validator;

import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class HouseCreateDtoValidator implements Validator {

    private HouseFacade houseFacade;


    public HouseCreateDtoValidator(HouseFacade houseFacade) {
        this.houseFacade = houseFacade;
    }

    @Override
    public boolean supports(Class<?> classy) {
        return HouseCreateDto.class.isAssignableFrom(classy);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HouseCreateDto house = (HouseCreateDto)target;
        HouseDto h;
        try {
            h = houseFacade.findHouseByName(house.getName());
        }catch (Exception e){
            h=null;
        }
        if(h!=null){
            errors.rejectValue("name", "invalid.name", "Name is already in database");
        }
    }
}
