package eu.qwan.editrain.adapters.repositories.records;

import eu.qwan.editrain.domain.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseRecordMapper {
    public static Course toCourse(CourseRecord record) {
        return Course.builder()
                .id(record.getId())
                .name(record.getName())
                .description(record.getDescription())
                .teacher(record.getTeacher())
                .build();
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
