package eu.qwan.editrain.adapters.repositories.records;

import eu.qwan.editrain.domain.builders.CourseBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.qwan.editrain.adapters.repositories.records.CourseRecordMapper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CourseRecordMapperTests {
    @Nested
    class FromRecordToCourse {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = CourseRecord.aValidCourseRecord().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            var course = CourseBuilder.aValidCourse().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            assertThat(toCourse(record),equalTo(course));
        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = CourseRecord.aValidCourseRecord().name("course1").build();
            var course2 = CourseRecord.aValidCourseRecord().name("course2").build();
            assertThat(toCourseList(List.of(course1, course2)),
                    equalTo(List.of(toCourse(course1), toCourse(course2))));

        }
    }
    @Nested
    class FromCourseToRecord {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = CourseRecord.aValidCourseRecord().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            var course = CourseBuilder.aValidCourse().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            assertThat(toCourseRecord(course),equalTo(record));

        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = CourseBuilder.aValidCourse().name("course1").build();
            var course2 = CourseBuilder.aValidCourse().name("course2").build();
            assertThat(toCourseRecordList(List.of(course1, course2)),
                    equalTo(List.of(toCourseRecord(course1), toCourseRecord(course2))));

        }
    }
}
