package studio.geek.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author 李文浩
 * @version 2017/3/9.
 */

@ControllerAdvice(basePackages ="studio.geek.action.home")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
    public JsonpAdvice(){
        super("callback","jsonp");
    }
}
