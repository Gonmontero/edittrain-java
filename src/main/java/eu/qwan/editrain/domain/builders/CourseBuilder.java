package eu.qwan.editrain.domain.builders;

import eu.qwan.editrain.domain.Course;

public class CourseBuilder implements Builder<Course> {
    private String id;
    private String name;
    private String description;
    private String taughtBy;

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public static CourseBuilder aValidCourse() {
        return aCourse().withId("some-id").withName("Design Fundamentals").withDescription("Some description").taughtBy("teacher@editrain.eu");
    }

    public CourseBuilder taughtBy(String teacher) {
        this.taughtBy = teacher;
        return this;
    }

    public CourseBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public Course build() {
        return new Course(id, name, description, taughtBy);
    }
}
