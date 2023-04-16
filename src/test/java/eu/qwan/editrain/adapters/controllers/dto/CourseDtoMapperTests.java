package eu.qwan.editrain.adapters.controllers.dto;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.qwan.editrain.adapters.controllers.dto.CourseDto.aValidCourseDto;
import static eu.qwan.editrain.adapters.controllers.dto.CourseDtoMapper.*;
import static eu.qwan.editrain.domain.builders.CourseBuilder.aValidCourse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CourseDtoMapperTests {
    @Nested
    class FromRecordToCourse {
        @Test
        public void takesAllAttributesOverToCourse() {
            var dto = aValidCourseDto().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            var course = aValidCourse().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            MatcherAssert.assertThat(toCourse(dto),equalTo(course));
        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var dto1 = aValidCourseDto().name("course1").build();
            var dto2 = aValidCourseDto().name("course2").build();
            assertThat(toCourseList(List.of(dto1, dto2)),
                    equalTo(List.of(toCourse(dto1), toCourse(dto2))));

        }
    }
    @Nested
    class FromCourseToRecord {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = aValidCourseDto().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            var course = aValidCourse().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            assertThat(toCourseDto(course),equalTo(record));

        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = aValidCourse().name("course1").build();
            var course2 = aValidCourse().name("course2").build();
            assertThat(toCourseDtoList(List.of(course1, course2)),
                    equalTo(List.of(toCourseDto(course1), toCourseDto(course2))));

        }
    }
}
