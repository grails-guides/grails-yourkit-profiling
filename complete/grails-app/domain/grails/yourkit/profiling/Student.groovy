package grails.yourkit.profiling

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Student {
    String name
    BigDecimal grade

    String toString() {
        name + "-Grade:$grade"
    }

    static constraints = {
    }
}
