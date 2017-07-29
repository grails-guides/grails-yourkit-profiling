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
}
