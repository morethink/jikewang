import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author 李文浩
 * @version 2017/2/19.
 */
public class RegularExpressionTest {

    @Test
    public void testRegularExpression() {

        Pattern pattern = Pattern.compile("^\\d{10}$");
        System.out.println(pattern.matcher("2015210992").matches());
//        if (member.getSex().equals("男") ||
//                member.getSex().equals("女") ||
//                member.getSex() == null ||
//                member.getSex().equals("")) {
//            System.out.println("dd");
////            throw new ErrorException("你的性别可能是有点奇怪啊");
//        }
    }
}
