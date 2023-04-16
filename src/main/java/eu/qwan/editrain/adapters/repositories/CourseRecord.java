package eu.qwan.editrain.adapters.repositories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class CourseRecord {
    @Id
    private String id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String teacher;

    public static CourseRecordBuilder aValidCourse() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}