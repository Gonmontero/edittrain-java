package eu.qwan.editrain.adapters.repositories.records;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.qwan.editrain.adapters.repositories.records.CourseRecord.aValidCourseRecord;
import static eu.qwan.editrain.adapters.repositories.records.CourseRecordMapper.*;
import static eu.qwan.editrain.domain.builders.Builder.build;
import static eu.qwan.editrain.domain.builders.CourseBuilder.aValidCourse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CourseRecordMapperTests {
    @Nested
    class FromRecordToCourse {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = aValidCourseRecord().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            var course = build(aValidCourse().withId("some-id").withName("some-name").withDescription("some-descriptio").taughtBy("some-teacher"));
            assertThat(toCourse(record),equalTo(course));
        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = aValidCourseRecord().name("course1").build();
            var course2 = aValidCourseRecord().name("course2").build();
            assertThat(toCourseList(List.of(course1, course2)),
                    equalTo(List.of(toCourse(course1), toCourse(course2))));

        }
    }
    @Nested
    class FromCourseToRecord {
        @Test
        public void takesAllAttributesOverToCourse() {
            var record = aValidCourseRecord().id("some-id").name("some-name").description("some-descriptio").teacher("some-teacher").build();
            var course = build(aValidCourse().withId("some-id").withName("some-name").withDescription("some-descriptio").taughtBy("some-teacher"));
            assertThat(toCourseRecord(course),equalTo(record));

        }
        @Test
        public void mappingAListMapsAllElementsInAList() {
            var course1 = build(aValidCourse().withName("course1"));
            var course2 = build(aValidCourse().withName("course2"));
            assertThat(toCourseRecordList(List.of(course1, course2)),
                    equalTo(List.of(toCourseRecord(course1), toCourseRecord(course2))));

        }
    }
}
