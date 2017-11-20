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

    //tag::delete[]
    def delete() {
        studentOptimizedService.deleteStudents()
        def count = Student.count
        render "Student Count: $count"
    }
    //end::delete[]

    //tag::print[]
    def print() {
        String result = studentOptimizedService.printStudents()
        render result
    }
    //end::print[]

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
