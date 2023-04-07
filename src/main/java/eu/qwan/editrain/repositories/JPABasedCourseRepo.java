package eu.qwan.editrain.repositories;

import eu.qwan.editrain.services.Course;
import eu.qwan.editrain.services.CourseRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static eu.qwan.editrain.repositories.CourseRecordMapper.*;

@Component
public class JPABasedCourseRepo implements CourseRepo {
    private CourseRepository courseRepository;

    public JPABasedCourseRepo(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return toCourseList(courseRepository.findAll());
    }

    @Override
    public Course save(Course course) {
        return toCourse(courseRepository.save(toCourseRecord(course)));
    }

    @Override
    public Optional<CourseRecord> findById(String id) {
        return courseRepository.findById(id);
    }
}
