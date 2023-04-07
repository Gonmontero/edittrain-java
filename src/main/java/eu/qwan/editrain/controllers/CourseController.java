package eu.qwan.editrain.controllers;

import eu.qwan.editrain.model.EdiTrainException;
import eu.qwan.editrain.repositories.CourseRecord;
import eu.qwan.editrain.repositories.CourseRecordMapper;
import eu.qwan.editrain.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static eu.qwan.editrain.repositories.CourseRecordMapper.*;

class ErrorResponse {
    public final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }
}

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseRecord>> getCourses() {
        return ResponseEntity.ok(toCourseRecordList(courseService.findAll()));
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseRecord>> createCourse(@RequestBody @Valid CourseRecord body) {
        var course= courseService.create(toCourse(body)).map(CourseRecordMapper::toCourseRecord);
        System.out.println(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseRecord>> updateCourse(@RequestBody @Valid CourseRecord body) {
        if (body.getId() == null || body.getId().isBlank()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        courseService.update(body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = EdiTrainException.class)
    public ResponseEntity<ErrorResponse> handleEdiTrainExceptions(EdiTrainException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
