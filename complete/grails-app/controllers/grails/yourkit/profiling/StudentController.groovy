package grails.yourkit.profiling

class StudentController {

    static scaffold = Student
    StudentService studentService

    def insert() {
        studentService.insertStudents()
        def count = Student.count
        render "Student Count: $count"
    }

    def delete() {
        studentService.deleteStudents()
        def count = Student.count
        render "Student Count: $count"
    }

}
