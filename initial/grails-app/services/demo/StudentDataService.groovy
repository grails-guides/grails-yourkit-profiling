package demo

import grails.gorm.services.Service
import groovy.transform.CompileStatic

@CompileStatic
@Service(Student)
interface StudentDataService {
    List<Student> findAll()
    List<Student> findByGradeLessThan(BigDecimal grade)
    int count()
    Student save(String name, BigDecimal grade)
}