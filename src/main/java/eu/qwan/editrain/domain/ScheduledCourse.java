package eu.qwan.editrain.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ScheduledCourse {
    private final String id;
    private final String name;
    private final String description;
    private final String teacher;
    private final LocalDate on;
    private final int maximumParticipants;

    public ScheduledCourse(String id, String name, String description, String teacher, LocalDate on, int maximumParticipants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.on = on;
        this.maximumParticipants = maximumParticipants;
    }

    public List<Object> enrollments() {
        return Collections.emptyList();
    }
}
