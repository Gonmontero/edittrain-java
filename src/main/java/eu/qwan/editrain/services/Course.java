package eu.qwan.editrain.services;

import eu.qwan.editrain.repositories.CourseRecord;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String name;
    private String description;
    private String teacher;

    public static CourseBuilder aValidCourse() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}
