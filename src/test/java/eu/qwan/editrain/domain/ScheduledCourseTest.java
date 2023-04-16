package eu.qwan.editrain.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static eu.qwan.editrain.domain.builders.Builder.build;
import static eu.qwan.editrain.domain.builders.ScheduledCourseBuilder.aValidScheduledCourse;
import static eu.qwan.editrain.domain.builders.StudentBuilder.aValidStudent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class ScheduledCourseTest {
    @Nested
    class Enrollments {
        @Test
        public void areEmptyInitially() {
            var course = build(aValidScheduledCourse());
            var student = build(aValidStudent());
            assertThat(course.enrollments(), is(empty()));
        }
    }
}
