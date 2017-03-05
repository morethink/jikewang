package studio.geek.action; /**
 * Created by think on 2017/1/19.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import studio.geek.entity.Candidate;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhengcanrui on 16/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml","classpath:applicationContext.xml"})

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional

@WebAppConfiguration
public class GeekTest {
    //记得配置log4j.properties ,的命令行输出水平是debug
//    protected Log logger= LogFactory.getLog(TestBase.class);

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void test() throws Exception {
        String responseString = mockMvc.perform(
                get("/candidate/listCandidates")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("pcode", "root")         //添加参数
                        .param("pcodes", "roots")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);
    }

    @Test
    public void testRequestBody() throws Exception {
        String re = mockMvc.perform(
                get("/candidate/listCandidates")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();

        Gson gson = new Gson();
////        List<Candidate> list = new ArrayList<>();
//        Map<String,String> map = new HashMap();
//        map = gson.fromJson(re, map.getClass());
//        System.out.println(map);
//        System.out.println();
//        Type listType = new TypeToken<ArrayList<Candidate>>(){}.getType();
//       ArrayList list =gson.fromJson(map.get("candidates"), listType);
//        System.out.println(list);

        Type listType = new TypeToken<ArrayList<Candidate>>() {
        }.getType();
        ArrayList list = gson.fromJson("[{id=2015210991, name=凌龙, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}, {id=2015210992, name=李文浩, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}]", listType);

        String responseString = mockMvc.perform(
                get("/candidate/query")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
//                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
                        .param("candidates", "[{id=2015210991, name=凌龙, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}, {id=2015210992, name=李文浩, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}]")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);
    }

    @Test
    public void testtt() {
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<Candidate>>() {
        }.getType();
        ArrayList<Candidate> list = gson.fromJson("[{id=2015210991, name=凌龙, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}, {id=2015210992, name=李文浩, school=重庆邮电大学, major=信息管理与信息系统, direction=后台}]", listType);
        for (Candidate can : list)
            System.out.println(can);
    }
}
