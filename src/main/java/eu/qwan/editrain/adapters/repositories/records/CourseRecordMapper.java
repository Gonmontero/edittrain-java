package eu.qwan.editrain.adapters.repositories.records;

import eu.qwan.editrain.domain.Course;

import java.util.List;
import java.util.stream.Collectors;

import static eu.qwan.editrain.domain.builders.Builder.build;
import static eu.qwan.editrain.domain.builders.CourseBuilder.aCourse;

public class CourseRecordMapper {
    public static Course toCourse(CourseRecord record) {
        return build(aCourse()
                .withId(record.getId())
                .withName(record.getName())
                .withDescription(record.getDescription())
                .taughtBy(record.getTeacher()));
    }

    public static CourseRecord toCourseRecord(Course course) {
        return CourseRecord.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .teacher(course.getTeacher())
                .build();
    }

    public static List<CourseRecord> toCourseRecordList(List<Course> courses) {
        return courses.stream().map(CourseRecordMapper::toCourseRecord).collect(Collectors.toList());
    }

    public static List<Course> toCourseList(List<CourseRecord> records) {
        return records.stream().map(CourseRecordMapper::toCourse).collect(Collectors.toList());
    }
}
