package eu.qwan.editrain.domain.builders;

import eu.qwan.editrain.domain.Course;

public class CourseBuilder implements Builder<Course> {
    Course.CourseBuilder builder = Course.builder();
    public static CourseBuilder aValidCourse() {
        return new CourseBuilder().withId("some-id").withName("Design Fundamentals").withDescription("Some description").taughtBy("teacher@editrain.eu");
    }

    public CourseBuilder taughtBy(String teacher) {
        builder = builder.teacher(teacher);
        return this;
    }

    public CourseBuilder withDescription(String description) {
        builder = builder.description(description);
        return this;
    }

    public CourseBuilder withName(String name) {
        builder = builder.name(name);
        return this;
    }

    public CourseBuilder withId(String id) {
        builder = builder.id(id);
        return this;
    }

    @Override
    public Course build() {
        return builder.build();
    }
}
