package eu.qwan.editrain.domain.builders;

import eu.qwan.editrain.domain.Course;

public class CourseBuilder implements Builder<Course> {
    Course.CourseBuilder builder = Course.builder();
    public static CourseBuilder aValidCourse() {
        return new CourseBuilder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }

    public CourseBuilder teacher(String teacher) {
        builder = builder.teacher(teacher);
        return this;
    }

    public CourseBuilder description(String description) {
        builder = builder.description(description);
        return this;
    }

    public CourseBuilder name(String name) {
        builder = builder.name(name);
        return this;
    }

    public CourseBuilder id(String id) {
        builder = builder.id(id);
        return this;
    }

    @Override
    public Course build() {
        return builder.build();
    }
}
