package eu.qwan.editrain.adapters.controllers.dto;

import eu.qwan.editrain.domain.Course;

import java.util.List;
import java.util.stream.Collectors;

import static eu.qwan.editrain.domain.builders.Builder.build;
import static eu.qwan.editrain.domain.builders.CourseBuilder.aCourse;

public class CourseDtoMapper {
    public static Course toCourse(CourseDto dto) {
        return build(aCourse()
                .withId(dto.getId())
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .taughtBy(dto.getTeacher()));
    }

    public static CourseDto toCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .teacher(course.getTeacher())
                .build();
    }

    public static List<Course> toCourseList(List<CourseDto> dtos) {
        return dtos.stream().map(CourseDtoMapper::toCourse).collect(Collectors.toList());
    }
    public static List<CourseDto> toCourseDtoList(List<Course> courses) {
        return courses.stream().map(CourseDtoMapper::toCourseDto).collect(Collectors.toList());
    }

}
