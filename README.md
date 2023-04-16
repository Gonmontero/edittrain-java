# README #

EdiTrain - Hexagonification Exercise

(c) copyright 2022 QWAN - Quality Without a Name - www.qwan.eu

## Homework for implementing ddd

In this exercise, we will focus on the domain logic only (the "inside of the hexagon"). The objective of this exercise
is to implement the _Enroll_ command on ScheduledCourse, which results in a _StudentEnrolled_ domain event. The
ScheduledCourse is an aggregate (there is not much behaviour or structure yet, you will add this as part of the
exercise).

Implement the Enroll command as a method on the ScheduledCourse class. Use Test Driven Development, in baby steps, with
small commits. We have created a ScheduledCourse class with a ScheduledCourseTest and a Student class as a starting
point.

A student can be enrolled for a scheduled course. The date of enrollment is important to keep. The following business
rules and constraints hold for enrolling:
The student cannot enroll for a course they are already enrolled for
The number of places is limited
The student cannot enroll after the actual date on which the course has been scheduled

Start with the normal behaviour of enrolling. How can you see if enrolling has been successful? Hint: the
ScheduledCourse aggregate already has an ‘enrollments’ method that should return all currently enrolled students.

An enrollment is a combination of a student and the date of enrollment. How do you represent enrollments internally in
the ScheduledCourse aggregate?

Change the enroll method so that it returns an instance of the StudentEnrolled domain event. You will need to create a
class for this. What data needs to be in the event?

Implement the “The student cannot enroll for a course they are already enrolled for” business rule. How can you observe
that enrolling has failed? The caller of the enroll method wants to know if the command has succeeded or failed (because
eventually, we want to return an appropriate response to the user). How can you have the enroll method indicate a
failure? (there are multiple ways of doing this).

## Calling the service

Add a course:
```shell
curl -i -X POST localhost:8080/courses --data '{"name":"OO Essentials","description":"Entry level","teacher":"rob@editrain.eu"}' -H "Content-Type: application/json"
```

Get all courses:
```shell
curl localhost:8080/courses
```

Update a course (name and description only):
```shell
curl -i -X PUT localhost:8080/courses --data '{"id":"<id>","name":"OO Essentials part I","description":"Entry level","teacher":"rob@editrain.eu"}' -H "Content-Type: application/json"
```
