import org.openjdk.jol.info.ClassLayout
import org.openjdk.jol.info.GraphLayout
import spock.lang.Specification

class HelloSpockSpec extends Specification {

    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }

    def "object memory allocated to jvm "(){
        expect:
        int[] arr = new int[0];
        List<String> test = new ArrayList<>();
        for( int i=0; i <12; i++) {
            test.add(String.valueOf(i));
        }
        System.out.println(ClassLayout.parseInstance(test).toString());

        System.out.println(GraphLayout.parseInstance(test).toFootprint());
    }

}
