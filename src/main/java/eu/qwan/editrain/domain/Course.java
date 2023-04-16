package eu.qwan.editrain.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Course {
    private String id;
    private String name;
    private String description;
    private String teacher;

    public static CourseBuilder aValidCourse() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}
