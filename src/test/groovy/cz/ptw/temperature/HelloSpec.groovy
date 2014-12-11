package cz.ptw.temperature

/**
 * Description
 *
 * @author Lukáš Cír, FG Forrest a.s. (c) 2014
 *      11.12.14 15:19 
 */
class HelloSpec extends spock.lang.Specification {
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 3
        "Kirk"   | 4
        "Scotty" | 6
    }

}
