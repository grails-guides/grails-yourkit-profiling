package grails.yourkit.profiling

class StudentOptimizedService {

    static final int LARGE_NUMBER = 5000
    static final int boundary = 100

    Random random = new Random()

    void insertStudents() {
        LARGE_NUMBER.times {
            Student student = new Student(name: produceRandomName(), grade: random.nextInt(boundary)).save()
        }
    }

    void deleteStudents() {
        Student.executeUpdate("delete Student where grade <= 90")
    }

    protected String produceRandomName() {
        String randomName = "Name" + random.nextInt(2*LARGE_NUMBER)
        randomName
    }
}

