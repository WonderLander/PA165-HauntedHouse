package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.config.ServiceConfig;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Simple tests for facade layer - one test per method
 * @author Lukas Sadlek
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class CommentFacadeTest {
    @InjectMocks
    @Autowired
    private CommentFacadeImpl commentFacade;

    @Mock
    private CommentService commentService;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private Comment mockComment;

    @Mock
    private CommentDto mockCommentDto;

    @Mock
    private CommentCreateDto mockCommentCreateDto;

    @Mock
    private House house;

    @Mock
    private HouseDto houseDto;

    private List<Comment> mockComments;
    private List<CommentDto> mockCommentDtos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockComments = Collections.singletonList(mockComment);
        mockCommentDtos = Collections.singletonList(mockCommentDto);
    }

    @Test
    public void testFindById() {
        when(commentService.findById(1L)).thenReturn(mockComment);
        when(beanMappingService.mapTo(mockComment, CommentDto.class)).thenReturn(mockCommentDto);
        CommentDto commentDto = commentFacade.findById(1L);
        Assert.assertNotNull(commentDto);
        Assert.assertEquals(commentDto.getAuthor(), mockCommentDto.getAuthor());
        verify(commentService).findById(1L);
    }

    @Test
    public void testFindAll() {
        when(commentService.findAll()).thenReturn(mockComments);
        when(beanMappingService.mapTo(mockComments, CommentDto.class)).thenReturn(mockCommentDtos);
        List<CommentDto> commentDtoList = commentFacade.findAll();

        Assert.assertEquals(commentDtoList.size(), 1);
        Assert.assertEquals(commentDtoList.get(0).getAuthor(), mockCommentDto.getAuthor());
        verify(commentService).findAll();
    }

    @Test
    public void testFindByAuthor() {
        when(commentService.findByAuthor("anonym")).thenReturn(mockComments);
        when(beanMappingService.mapTo(mockComments, CommentDto.class)).thenReturn(mockCommentDtos);
        List<CommentDto> commentDtoList = commentFacade.findByAuthor("anonym");

        Assert.assertEquals(commentDtoList.size(), 1);
        Assert.assertEquals(commentDtoList.get(0).getText(), mockCommentDto.getText());
        verify(commentService).findByAuthor("anonym");
    }

    @Test
    public void testFindByHouse() {
        when(commentService.findByHouse(house)).thenReturn(mockComments);
        when(beanMappingService.mapTo(mockComments, CommentDto.class)).thenReturn(mockCommentDtos);
        when(beanMappingService.mapTo(houseDto, House.class)).thenReturn(house);
        List<CommentDto> commentDtoList = commentFacade.findByHouse(houseDto);

        Assert.assertEquals(commentDtoList.size(), 1);
        Assert.assertEquals(commentDtoList.get(0).getAuthor(), mockCommentDto.getAuthor());
        verify(commentService).findByHouse(house);
    }

    @Test
    public void testGetHouse() {
        when(commentService.getHouse(mockComment)).thenReturn(house);
        when(beanMappingService.mapTo(house, HouseDto.class)).thenReturn(houseDto);
        when(beanMappingService.mapTo(mockCommentDto, Comment.class)).thenReturn(mockComment);

        HouseDto houseDto1 = commentFacade.getHouse(mockCommentDto);
        Assert.assertEquals(houseDto1.getName(), house.getName());
        verify(commentService).getHouse(mockComment);
    }

    @Test
    public void testIsCommentedHouseHaunted() {
        when(commentService.isCommentedHouseHaunted(mockComment)).thenReturn(true);
        when(beanMappingService.mapTo(mockCommentDto, Comment.class)).thenReturn(mockComment);

        Assert.assertTrue(commentFacade.isCommentedHouseHaunted(mockCommentDto));
        verify(commentService).isCommentedHouseHaunted(mockComment);
    }

    @Test
    public void testCreate() {
        when(beanMappingService.mapTo(mockCommentCreateDto, Comment.class)).thenReturn(mockComment);
        commentFacade.create(mockCommentCreateDto);
        verify(commentService).create(mockComment);
    }

    @Test
    public void testDelete() {
        when(beanMappingService.mapTo(mockCommentDto, Comment.class)).thenReturn(mockComment);
        commentFacade.delete(mockCommentDto);
        verify(commentService).delete(mockComment);
    }

    @Test
    public void testUpdate() {
        when(beanMappingService.mapTo(mockCommentDto, Comment.class)).thenReturn(mockComment);
        commentFacade.update(mockCommentDto);
        verify(commentService).update(mockComment);
    }

    //NOTE: purpose of the tests on theFacade layer is not to control again whether the function on service layer
    //sorts the list properly -- this is tested on service layer

    @Test
    public void testFindAllSorted() {
        when(commentService.findAllSortedByDate()).thenReturn(mockComments);
        when(beanMappingService.mapTo(mockComments, CommentDto.class)).thenReturn(mockCommentDtos);
        List<CommentDto> commentDtoList = commentFacade.findAllSortedByDate();
        Assert.assertEquals(commentDtoList, mockCommentDtos);
        verify(commentService).findAllSortedByDate();

        when(commentService.findAllSortedByAuthor()).thenReturn(mockComments);
        commentDtoList = commentFacade.findAllSortedByAuthor();
        Assert.assertEquals(commentDtoList, mockCommentDtos);
        verify(commentService).findAllSortedByAuthor();
    }

    @Test
    public void findMostCommentedAbility() {
        List<Ability> abilities = Collections.singletonList(Mockito.mock(Ability.class));
        when(commentService.findMostCommentedAbility()).thenReturn(abilities);
        List<AbilityDto> abilityDtos = Collections.singletonList(Mockito.mock(AbilityDto.class));
        when(beanMappingService.mapTo(Collections.singletonList(new Ability()), AbilityDto.class))
                .thenReturn(abilityDtos);
        List<AbilityDto> abilityDtoList = commentFacade.findMostCommentedAbility();
        Assert.assertEquals(abilityDtoList, abilityDtos);
        verify(commentService).findMostCommentedAbility();
    }
}
