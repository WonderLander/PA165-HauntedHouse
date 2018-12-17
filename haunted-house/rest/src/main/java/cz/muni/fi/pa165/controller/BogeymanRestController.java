package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.exception.NotFoundException;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lukas Sadlek
 */
@RestController
@RequestMapping(value = "bogeymen")
public class BogeymanRestController {
    @Autowired
    private BogeymanFacade bogeymanFacade;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BogeymanDto> getBogeymen() {
        return bogeymanFacade.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody BogeymanCreateDto bogeymanDto) {
        bogeymanFacade.create(bogeymanDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BogeymanDto bogeyman(@PathVariable long id) {
        BogeymanDto bogeymanDto = bogeymanFacade.findById(id);
        if (bogeymanDto == null) {
            throw new NotFoundException("No bogeyman with id: " + id);
        }

        return bogeymanDto;
    }

    @RequestMapping(value = "edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BogeymanDto update(@RequestBody BogeymanDto bogeymanDto) {
        if (bogeymanFacade.findById(bogeymanDto.getId()) == null) {
            throw new NotFoundException("No bogeyman with id: " + bogeymanDto.getId());
        }
        bogeymanFacade.update(bogeymanDto);
        return bogeymanFacade.findById(bogeymanDto.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void delete(@PathVariable("id") long id) {
        try {
            bogeymanFacade.delete(bogeymanFacade.findById(id));
        } catch (Exception ex) {
            throw new NotFoundException("Could not delete bogeyman with id: " + id);
        }
    }
}
