package demo

class StudentController {

    static final int LARGE_NUMBER = 20000
    static scaffold = Student
    StudentOptimizedService studentOptimizedService

    def insert() {
        studentOptimizedService.insertStudents(LARGE_NUMBER)
        def count = Student.count
        render "Student Count: $count"
    }

    def delete() {
        studentOptimizedService.deleteStudents()
        def count = Student.count
        render "Student Count: $count"
    }

    def print() {
        String result = studentOptimizedService.printStudents()
        render result
    }

    def import25kStudents() {
        studentOptimizedService.saveExcelStudents("studentImport-25krows.xlsx")
        def count = Student.count
        render "Student Count: $count"
    }

    def import75kStudents() {
        studentOptimizedService.saveExcelStudents("studentImport-75krows.xlsx")
        def count = Student.count
        render "Student Count: $count"
    }

}
