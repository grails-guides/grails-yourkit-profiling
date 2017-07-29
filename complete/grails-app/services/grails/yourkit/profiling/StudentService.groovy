package grails.yourkit.profiling

class StudentService {

    static final int LARGE_NUMBER = 5000
    static final int boundary = 100

    Random random = new Random()

    void insertStudents() {
        LARGE_NUMBER.times {
            Student student = new Student(name: produceRandomName(), grade: random.nextInt(boundary)).save()
        }
    }

    void deleteStudents() {
        List<Student> students = Student.findAllByGradeLessThan(90)
        for (s in students) {
            s.delete(flush: true)
        }
    }

    protected String produceRandomName() {
        String randomName = "Name" + random.nextInt(2*LARGE_NUMBER)
        randomName
    }
}

