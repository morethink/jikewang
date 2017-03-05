package studio.geek.databind;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李文浩
 * @version 2017/2/23.
 */
public class MyProcessorRegistry implements ApplicationContextAware, BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);

        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();


        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<HandlerMethodArgumentResolver>();

        for (HandlerMethodArgumentResolver resolver : resolvers) {
            newResolvers.add(resolver);
        }
        MyModelAttributeMethodProcessor processor = new MyModelAttributeMethodProcessor(true);
        processor.setApplicationContext(applicationContext);
        newResolvers.add(0, processor);
        requestMappingHandlerAdapter.setArgumentResolvers(Collections.unmodifiableList(newResolvers));
    }
}