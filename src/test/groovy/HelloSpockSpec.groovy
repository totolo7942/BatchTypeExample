import org.apache.commons.validator.routines.UrlValidator
import org.openjdk.jol.info.ClassLayout
import org.openjdk.jol.info.GraphLayout
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

import java.util.regex.Matcher
import java.util.regex.Pattern

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

    def "Url split"() {
        expect:
        String url="https://www.11st.co.kr/test"
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        String protocol = matcher.group(1);
        String domain = matcher.group(2);
        String port = matcher.group(3);
        String uri = matcher.group(4);
        System.out.println(url);
        System.out.println("protocol: " + (protocol != null ? protocol : ""));
        System.out.println("domain: " + (domain != null ? domain : ""));
        System.out.println("port: " + (port != null ? port : ""));
        System.out.println("uri: " + (uri != null ? uri : ""));
        System.out.println();

        //2번째 방식 URL로 분리
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(url)) {
            URL urls = new URL(url);
            System.out.println("protocol: " + urls.getProtocol());
            System.out.println("domain: " + urls.getHost());
            System.out.println("port: " + urls.getPort());
            System.out.println("uri: " + urls.getPath());
        } else {
            throw new HttpClientErrorException.BadRequest("URL is Not Valid");
        }
    }

    def "URL을 정규표현식으로 분리하자"(){
        given:
        def url ="https://www.11st.co.kr/test"

        when:
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        def domain = matcher.group(2);

        then:
        println(domain)
        domain.contains("www.11st.co.kr")
    }

    def "URL을 사용하여 간단하게 domain을 분리하자" (){
        given:
        def url ="https://www.11st.co.kr/test"

        when:
        URL urls = new URL(url);
        def domain =  urls.getHost();

        then:
        UrlValidator urlValidator = new UrlValidator();
        urlValidator.isValid(url)
        domain.contains("www.11st.co.kr")

    }

}
