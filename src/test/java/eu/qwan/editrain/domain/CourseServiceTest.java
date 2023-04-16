package eu.qwan.editrain.domain;

import eu.qwan.editrain.domain.builders.CourseBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    private CourseRepo courseRepository = mock(CourseRepo.class);
    private CourseService courseService = new CourseService(courseRepository);

    @Nested
    class WhenCreatingACourse {
        @Test
        public void savesItInTheRepository() {
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu")).get();
            verify(courseRepository).save(createdCourse);
            assertThat(createdCourse.getId(), is(not("")));
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            when(courseRepository.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu"));
            assertThat(createdCourse, is(Optional.empty()));
        }
    }
    @Nested
    class WhenGettingCourses {
        @Test
        public void returnsAllCoursesFromTheRepository() {
            Course course = CourseBuilder.aValidCourse().build();
            when(courseRepository.findAll()).thenReturn(List.of(course));
            var courses = courseService.findAll();
            assertThat(courses, is(List.of(course)));
        }
    }

    @Nested
    class WhenUpdatingACourse {
        @Test
        public void savesChangesInTheRepository() {
            var course = CourseBuilder.aValidCourse().build();
            var updated = CourseBuilder.aValidCourse().name("new name").description("updated").build();
            when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
            courseService.update(updated);
            verify(courseRepository).save(updated);
        }

        @Test
        public void leavesTeacherUnchanged() {
            var original = CourseBuilder.aValidCourse().teacher("original@edutrain.eu").build();
            var updated = CourseBuilder.aValidCourse().description("updated").teacher("updated@editrain.eu");
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            courseService.update(updated.build());
            verify(courseRepository).save(CourseBuilder.aValidCourse().description("updated").teacher("original@edutrain.eu").build());
        }

        @Test
        public void failsWhenTheCourseDoesNotExist() {
            var course = CourseBuilder.aValidCourse().build();
            when(courseRepository.findById(course.getId())).thenReturn(Optional.empty());
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(course));
            verify(courseRepository, never()).save(any());
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            var original = CourseBuilder.aValidCourse().build();
            var updated = CourseBuilder.aValidCourse().name("updated").build();
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            when(courseRepository.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(updated));
        }
    }
}
