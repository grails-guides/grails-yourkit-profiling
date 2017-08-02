package grails.yourkit.profiling

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.grails.plugins.excelimport.ExcelImportService

class StudentOptimizedService {

    static final int LARGE_NUMBER = 5000
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

    void insertStudents() {
        LARGE_NUMBER.times {
            Student student = new Student(name: produceRandomName(), grade: random.nextInt(boundary)).save()
        }
    }

    void deleteStudents() {
        Student.executeUpdate("delete Student where grade <= ${A_GRADE}")
    }

    String printStudents() {
        List<Student> students = Student.findAllByGradeLessThan(A_GRADE)
        StringBuffer result = new StringBuffer()
        for (s in students) {
            result.append("<p>${s.toString()}<p>")
        }
        result
    }

    void saveExcelStudents(String fileName) {
        List<Map> studentData = importStudents(fileName)
        for (s in studentData) {
            new Student(name: s.name, grade: s.grade).save()
        }
    }

    protected List<Map> importStudents(String fileName) {
        Workbook workbook = WorkbookFactory.create(new File(fileName))
        ExcelImportService excelImportService = new ExcelImportService()
        List<Map> studentData = excelImportService.convertColumnMapConfigManyRows(workbook, CONFIG_STUDENT_COLUMN_MAP)
        studentData
    }

    protected String produceRandomName() {
        String randomName = "Name" + random.nextInt(2*LARGE_NUMBER)
        randomName
    }
}

