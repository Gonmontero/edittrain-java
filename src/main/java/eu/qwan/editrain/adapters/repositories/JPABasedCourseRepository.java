package eu.qwan.editrain.adapters.repositories;

import eu.qwan.editrain.adapters.repositories.records.CourseRecordMapper;
import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JPABasedCourseRepository implements CourseRepository {
    private final JpaCourseRepository jpaCourseRepository;

    public JPABasedCourseRepository(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

    @Override
    public List<Course> findAll() {
        return CourseRecordMapper.toCourseList(jpaCourseRepository.findAll());
    }

    @Override
    public Course save(Course course) {
        return CourseRecordMapper.toCourse(jpaCourseRepository.save(CourseRecordMapper.toCourseRecord(course)));
    }

    @Override
    public Optional<Course> findById(String id) {
        return jpaCourseRepository.findById(id).map(CourseRecordMapper::toCourse);
    }
}
