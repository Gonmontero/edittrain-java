package eu.qwan.editrain.services;

import eu.qwan.editrain.repositories.CourseRecord;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    List<Course> findAll();

    CourseRecord save(CourseRecord course);

    Optional<CourseRecord> findById(String id);
}
