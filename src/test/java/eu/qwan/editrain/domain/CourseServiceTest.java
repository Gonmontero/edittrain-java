package eu.qwan.editrain.domain;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static eu.qwan.editrain.domain.builders.Builder.build;
import static eu.qwan.editrain.domain.builders.CourseBuilder.aValidCourse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    private CourseRepository courseRepository = mock(CourseRepository.class);
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
            Course course = build(aValidCourse());
            when(courseRepository.findAll()).thenReturn(List.of(course));
            var courses = courseService.findAll();
            assertThat(courses, is(List.of(course)));
        }
    }

    @Nested
    class WhenUpdatingACourse {
        @Test
        public void savesChangesInTheRepository() {
            var course = build(aValidCourse());
            var updated = build(aValidCourse().withName("new name").withDescription("updated"));
            when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
            courseService.update(updated);
            verify(courseRepository).save(updated);
        }

        @Test
        public void leavesTeacherUnchanged() {
            var original = build(aValidCourse().taughtBy("original@edutrain.eu"));
            var updated = build(aValidCourse().withDescription("updated").taughtBy("updated@editrain.eu"));
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            courseService.update(updated);
            verify(courseRepository).save(build(aValidCourse().withDescription("updated").taughtBy("original@edutrain.eu")));
        }

        @Test
        public void failsWhenTheCourseDoesNotExist() {
            var course = build(aValidCourse());
            when(courseRepository.findById(course.getId())).thenReturn(Optional.empty());
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(course));
            verify(courseRepository, never()).save(any());
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            var original = build(aValidCourse());
            var updated = build(aValidCourse().withName("updated"));
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            when(courseRepository.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(updated));
        }
    }
}
