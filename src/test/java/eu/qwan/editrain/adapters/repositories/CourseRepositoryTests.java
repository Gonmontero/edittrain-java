package eu.qwan.editrain.adapters.repositories;

import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.CourseRepo;
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
    CourseRepository courseRepository;

    @Autowired
    CourseRepo courseRepo;

    @BeforeEach
    public void setUp() {
        courseRepository.deleteAll();
    }

    @Nested
    class ContentsOfRepository {
        @Test
        public void isEmptyInitially() {
            assertThat(courseRepo.findAll(), is(empty()));
        }

        @Test
        public void containsSavedCourses() {
            var course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepo.save(course);
            assertThat(courseRepo.findAll(), is(List.of(course)));
        }
    }
    @Nested
    class AccessingCoursesById {
        @Test
        public void returnsNothingWhenRepositoryIsEmpty() {
            assertThat(courseRepo.findById("some-id"), is(Optional.empty()));
        }
        @Test
        public void returnsASavedCourseWhenAvailable() {
            var course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepo.save(course);
            assertThat(courseRepo.findById(course.getId()), is(Optional.of(course)));
        }
    }
}
