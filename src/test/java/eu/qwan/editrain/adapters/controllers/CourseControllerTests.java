package eu.qwan.editrain.adapters.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.qwan.editrain.adapters.controllers.dto.CourseDto;
import eu.qwan.editrain.domain.EdiTrainException;
import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.CourseService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static eu.qwan.editrain.adapters.controllers.dto.CourseDtoMapper.toCourse;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourseControllerTests {
    private CourseService courseService = mock(CourseService.class);

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new CourseController(courseService)).build();

    @Nested
    public class GettingAllCourses {
        @Test
        public void getCourses_ReturnsAnEmptyListWhenNoCoursesArePresent() throws Exception {
            when(courseService.findAll()).thenReturn(emptyList());
            mockMvc.perform(MockMvcJsonRequests.jsonGet("/courses")).andExpect(status().isOk());
        }

        @Test
        public void getCourses_ReturnsAListOfCoursesWhenCoursesExistInRepository() throws Exception {
            var course1 = Course.builder().id("1").name("Course1").description("someDescription1").build();
            var course2 = Course.builder().id("2").name("Course2").description("someDescription2").build();
            when(courseService.findAll()).thenReturn(List.of(course1, course2));
            mockMvc.perform(MockMvcJsonRequests.jsonGet("/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is("1")))
                    .andExpect(jsonPath("$[0].name", is("Course1")))
                    .andExpect(jsonPath("$[0].description", is("someDescription1")))
                    .andExpect(jsonPath("$[1].id", is("2")))
                    .andExpect(jsonPath("$[1].name", is("Course2")))
                    .andExpect(jsonPath("$[1].description", is("someDescription2")));
        }
    }
    @Nested
    public class PostingACourse {
        @Test
        public void savesIt() throws Exception {
            var theCourse = CourseDto.builder().id("someId").name("courseName").description("someDescription").teacher("jack@qwan.eu").build();
            when(courseService.create(toCourse(theCourse))).thenReturn(Optional.of(toCourse(theCourse)));
            mockMvc.perform(MockMvcJsonRequests.jsonPost("/courses", toJson(theCourse)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(theCourse.getId())));
        }

        @Test
        public void returns400WhenDataIsNotValid() throws Exception {
            var theCourse = CourseDto.builder().id("someId").name("").description("someDescription").build();
            mockMvc.perform(MockMvcJsonRequests.jsonPost("/courses", toJson(theCourse)))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    public class PuttingACourse {
        @Test
        public void updatesIt() throws Exception {
            var theCourse = CourseDto.builder().id("someId").name("courseName").description("someDescription").teacher("jack@qwan.eu").build();
            mockMvc.perform(MockMvcJsonRequests.jsonPut("/courses", toJson(theCourse)))
                    .andExpect(status().isNoContent());
            verify(courseService).update(toCourse(theCourse));
        }

        @Test
        public void returns400WhenDataIsNotValid() throws Exception {
            var theCourse = CourseDto.builder().id("someId").name("").description("someDescription").teacher("jack@qwan.eu").build();
            mockMvc.perform(MockMvcJsonRequests.jsonPut("/courses", toJson(theCourse)))
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void returns400WhenIdIsMissing() throws Exception {
            var theCourse = CourseDto.builder().name("name").description("someDescription").teacher("jack@qwan.eu").build();
            mockMvc.perform(MockMvcJsonRequests.jsonPut("/courses", toJson(theCourse)))
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void returns400WithErrorObjectWhenAnEdiTrainExceptionOccurs() throws Exception {
            var theCourse = CourseDto.aValidCourseDto().build();
            doThrow(new EdiTrainException("ERROR")).when(courseService).update(any());
            mockMvc.perform(MockMvcJsonRequests.jsonPut("/courses", toJson(theCourse)))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("error", is("ERROR")));
        }
    }

    private <T> String toJson(T body) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(body);
    }
}
