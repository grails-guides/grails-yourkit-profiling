package grails.yourkit.profiling

import grails.test.hibernate.HibernateSpec
import grails.test.mixin.TestFor

@TestFor(StudentService)
class StudentServiceSpec extends HibernateSpec {

    List<Class> getDomainClasses() { [Student] }

    def 'test produceRandomName'() {
        when:
        Random random = new Random()
        String name = service.produceRandomName()

        then:
        !name.isEmpty()
    }

    def 'test insertStudents'() {
        when:
        service.insertStudents()
        List<Student> students = Student.list()

        then:
        students.size() == StudentService.LARGE_NUMBER
    }

    def 'test deleteStudents'() {
        given:
        service.insertStudents()

        when:
        service.deleteStudents()
        List<Student> students = Student.list()

        then:
        students.size() < StudentService.LARGE_NUMBER
    }

    def 'test printStudents'() {
        when:
        service.insertStudents()
        String results = service.printStudents()

        then:
        println results
        !results.isEmpty()
    }

    def 'test importStudents'() {
        when:
        List<Map> studentData = service.importStudents("studentImport-3rows.xlsx")
        println studentData.dump()

        then:
        studentData.size() == 3
    }

    def 'test saveExcelStudents'() {
        when:
        service.saveExcelStudents("studentImport-3rows.xlsx")

        then:
        Student.count == 3
    }
}
