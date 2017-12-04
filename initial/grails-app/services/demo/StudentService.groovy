package demo

import groovy.util.logging.Slf4j
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.grails.plugins.excelimport.ExcelImportService
import groovy.transform.CompileStatic
import grails.gorm.transactions.Transactional

@Slf4j
@CompileStatic
class StudentService {

    StudentDataService studentDataService

    static final int boundary = 100
    static final BigDecimal A_GRADE = 90
    static final Map CONFIG_STUDENT_COLUMN_MAP = [
            sheet: 'Sheet1',
            startRow: 1,
            columnMap: [
                    'A': 'name',
                    'B': 'grade'
            ]
    ]

    Random random = new Random()

    @Transactional
    void insertStudents(int numberOfStudents) {
        numberOfStudents.times {
            BigDecimal grade = random.nextInt(boundary) as BigDecimal
            studentDataService.save(produceRandomName(), grade)
        }
    }

    //tag::deleteStudents[]
    @Transactional
    void deleteStudentsWithGradleLessThanA() {
        List<Student> students = studentDataService.findByGradeLessThan(A_GRADE)
        log.info '#{} students with less than A', students.size()
        for (Student s in students) {
            s.delete(flush: true)
        }
    }
    //end::deleteStudents[]

    //tag::htmlUnorderedListOfStudents[]
    String htmlUnorderedListOfStudents() {
        List<Student> students = studentDataService.findAll()
        String result = '<ul>'
        for (s in students) {
            result += "<li>${s.toString()}</li>"
        }
        result += '</ul>'
        result
    }
    //end::htmlUnorderedListOfStudents[]

    @Transactional
    void saveExcelStudents(String fileName) {
        List<Map> studentData = importStudents(fileName)
        for (Map s in studentData) {
            studentDataService.save(s.name as String, s.grade as BigDecimal)
        }
    }

    //tag::importStudents[]
    protected List<Map> importStudents(String fileName) {
        Workbook workbook = WorkbookFactory.create(new File(fileName))
        ExcelImportService excelImportService = new ExcelImportService()
        excelImportService.convertColumnMapConfigManyRows(workbook, CONFIG_STUDENT_COLUMN_MAP) as List<Map>
    }
    //end::importStudents[]

    protected String produceRandomName() {
        "Name${random.nextInt(2*boundary)}"
    }
}
