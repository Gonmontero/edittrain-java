package eu.qwan.editrain.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseRecord, String> {
}
