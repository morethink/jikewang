import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import studio.geek.util.JsonUtil;
import studio.geek.util.Result;

/**
 * @author 李文浩
 * @version 2017/2/22.
 */
public class RESTTest {



    @Test
    public void testGetForObject(){

        RestTemplate rest = new RestTemplate();
//        JsonUtil.prettyPrint(rest.getForObject("http://127.0.0.1/home/allProductions", Result.class));
        Result result = rest.getForObject("http://127.0.0.1/home/allProductions", Result.class);



        JsonUtil.prettyPrint(result);
    }

}
