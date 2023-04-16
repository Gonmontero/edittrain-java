package eu.qwan.editrain.adapters.controllers.dto;

import eu.qwan.editrain.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String id;
    private String name;
    private String description;
    private String teacher;

    public static CourseDtoBuilder aValidCourseDto() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}
