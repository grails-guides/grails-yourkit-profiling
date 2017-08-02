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

    def print() {
        String result = studentService.printStudents()
        render result
    }

    def import25kStudents() {
        studentService.saveExcelStudents("studentImport-25krows.xlsx")
        def count = Student.count
        render "Student Count: $count"
    }

    def import75kStudents() {
        studentService.saveExcelStudents("studentImport-75krows.xlsx")
        def count = Student.count
        render "Student Count: $count"
    }

}
