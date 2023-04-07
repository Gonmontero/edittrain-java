package eu.qwan.editrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseRecord, String> {
}
