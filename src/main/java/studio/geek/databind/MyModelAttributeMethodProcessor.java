package studio.geek.databind;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

/**
 * @author 李文浩
 * @version 2017/2/23.
 */


public class MyModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public MyModelAttributeMethodProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        MyRequestDataBinder toNullRequestDataBinderBinder = new MyRequestDataBinder(binder.getTarget(), binder.getObjectName());
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(toNullRequestDataBinderBinder, request);
        toNullRequestDataBinderBinder.bind(request.getNativeRequest(ServletRequest.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
