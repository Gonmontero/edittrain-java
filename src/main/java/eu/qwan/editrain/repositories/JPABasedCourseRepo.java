package eu.qwan.editrain.repositories;

import eu.qwan.editrain.services.CourseRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JPABasedCourseRepo implements CourseRepo {
    private CourseRepository courseRepository;

    public JPABasedCourseRepo(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseRecord> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseRecord save(CourseRecord course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<CourseRecord> findById(String id) {
        return courseRepository.findById(id);
    }
}
