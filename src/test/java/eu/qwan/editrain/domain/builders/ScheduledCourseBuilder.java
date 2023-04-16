package eu.qwan.editrain.domain.builders;

import eu.qwan.editrain.domain.Course;
import eu.qwan.editrain.domain.ScheduledCourse;

import java.time.LocalDate;

public class ScheduledCourseBuilder implements Builder<ScheduledCourse> {
    private Course course;
    private LocalDate on;
    private int maximumParticipants;
    private String id;

    public static ScheduledCourseBuilder aValidScheduledCourse() {
        return new ScheduledCourseBuilder()
                .withId("100")
                .fromCourse(CourseBuilder.aValidCourse())
                .on(LocalDate.of(2023, 1, 31))
                .thatHasMaximumPlaces(4);
    }

    @Override
    public ScheduledCourse build() {
        return new ScheduledCourse(
                id, course.getName(), course.getDescription(), course.getTeacher(), on, maximumParticipants
        );
    }

    public ScheduledCourseBuilder fromCourse(CourseBuilder course) {
        this.course = course.build();
        return this;
    }

    public ScheduledCourseBuilder on(LocalDate on) {
        this.on = on;
        return this;
    }

    public ScheduledCourseBuilder thatHasMaximumPlaces(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
        return this;
    }

    public ScheduledCourseBuilder withId(String id) {
        this.id = id;
        return this;
    }
}
