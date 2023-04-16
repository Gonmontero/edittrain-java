package eu.qwan.editrain.adapters.repositories;

import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class CourseRepositoryTests {
    @Autowired
    JpaCourseRepository jpaCourseRepository;

    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        jpaCourseRepository.deleteAll();
    }

    @Nested
    class ContentsOfRepository {
        @Test
        public void isEmptyInitially() {
            assertThat(courseRepository.findAll(), is(empty()));
        }

        @Test
        public void containsSavedCourses() {
            var course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepository.save(course);
            assertThat(courseRepository.findAll(), is(List.of(course)));
        }
    }
    @Nested
    class AccessingCoursesById {
        @Test
        public void returnsNothingWhenRepositoryIsEmpty() {
            assertThat(courseRepository.findById("some-id"), is(Optional.empty()));
        }
        @Test
        public void returnsASavedCourseWhenAvailable() {
            var course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepository.save(course);
            assertThat(courseRepository.findById(course.getId()), is(Optional.of(course)));
        }
    }
}
