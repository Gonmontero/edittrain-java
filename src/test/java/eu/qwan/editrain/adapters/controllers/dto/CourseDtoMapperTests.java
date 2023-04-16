package eu.qwan.editrain.adapters.controllers.dto;

import eu.qwan.editrain.adapters.controllers.dto.CourseDto;
import eu.qwan.editrain.adapters.mappers.CourseDtoMapper;
import eu.qwan.editrain.adapters.repositories.CourseRecord;
import eu.qwan.editrain.domain.Course;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CourseDtoMapperTests {
    @Nested
    class FromRecordToCourse {
        @Test
        public void takesAllAttributesOverToCourse() {
            var dto = CourseDto.aValidCourseDto().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            var course = Course.aValidCourse().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            MatcherAssert.assertThat(CourseDtoMapper.toCourse(dto),equalTo(course));
        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var dto1 = CourseDto.aValidCourseDto().name("course1").build();
            var dto2 = CourseDto.aValidCourseDto().name("course2").build();
            assertThat(CourseDtoMapper.toCourseList(List.of(dto1, dto2)),
                    equalTo(List.of(CourseDtoMapper.toCourse(dto1), CourseDtoMapper.toCourse(dto2))));

        }
    }
    @Nested
    class FromCourseToRecord {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = CourseDto.aValidCourseDto().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            var course = Course.aValidCourse().id("some-id").name("some-name").description("some-description").teacher("some-teacher").build();
            assertThat(CourseDtoMapper.toCourseDto(course),equalTo(record));

        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = Course.aValidCourse().name("course1").build();
            var course2 = Course.aValidCourse().name("course2").build();
            assertThat(CourseDtoMapper.toCourseDtoList(List.of(course1, course2)),
                    equalTo(List.of(CourseDtoMapper.toCourseDto(course1), CourseDtoMapper.toCourseDto(course2))));

        }
    }
}
