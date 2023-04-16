package eu.qwan.editrain.adapters.controllers.dto;

import eu.qwan.editrain.domain.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseDtoMapper {
    public static Course toCourse(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .teacher(dto.getTeacher())
                .build();
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
