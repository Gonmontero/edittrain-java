package eu.qwan.editrain.adapters.repositories.records;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class CourseRecord {
    @Id
    private String id;
    private String name;
    private String description;
    private String teacher;

    public static CourseRecordBuilder aValidCourseRecord() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}
