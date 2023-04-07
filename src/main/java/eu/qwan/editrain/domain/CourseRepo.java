package eu.qwan.editrain.domain;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    List<Course> findAll();

    Course save(Course course);

    Optional<Course> findById(String id);
}
