package eu.qwan.editrain.services;

import eu.qwan.editrain.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    List<Course> findAll();

    Course save(Course course);

    Optional<Course> findById(String id);
}
