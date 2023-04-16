package eu.qwan.editrain.domain.builders;

import eu.qwan.editrain.domain.Student;

public class StudentBuilder implements Builder<Student> {
    private String name;
    private String id;

    public static StudentBuilder aValidStudent() {
        return new StudentBuilder().withId("123").withName("Sammy Student");
    }

    private StudentBuilder withId(String id) {
        this.id = id;
        return this;
    }

    private StudentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Student build() {
        return new Student(id, name);
    }
}
