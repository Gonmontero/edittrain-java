package eu.qwan.editrain.adapters.repositories;

import eu.qwan.editrain.adapters.repositories.records.CourseRecordMapper;
import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.CourseRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JPABasedCourseRepo implements CourseRepo {
    private final CourseRepository courseRepository;

    public JPABasedCourseRepo(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return CourseRecordMapper.toCourseList(courseRepository.findAll());
    }

    @Override
    public Course save(Course course) {
        return CourseRecordMapper.toCourse(courseRepository.save(CourseRecordMapper.toCourseRecord(course)));
    }

    @Override
    public Optional<Course> findById(String id) {
        return courseRepository.findById(id).map(CourseRecordMapper::toCourse);
    }
}
