package eu.qwan.editrain.repositories;

import eu.qwan.editrain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
