package eu.qwan.editrain.adapters.repositories;

import eu.qwan.editrain.adapters.repositories.records.CourseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<CourseRecord, String> {
}
