package eu.qwan.editrain.repositories;

import eu.qwan.editrain.services.CourseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static eu.qwan.editrain.repositories.CourseRecordMapper.toCourse;
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
            CourseRecord course = new CourseRecord(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepo.save(course);
            assertThat(courseRepo.findAll(), is(List.of(toCourse(course))));
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
            CourseRecord course = new CourseRecord(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
            courseRepo.save(course);
            assertThat(courseRepo.findById(course.getId()), is(Optional.of(course)));
        }
    }
}
