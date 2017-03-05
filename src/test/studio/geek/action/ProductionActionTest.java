package studio.geek.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import studio.geek.util.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:dispatcher-servlet.xml"})
@WebAppConfiguration
public class ProductionActionTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }



    @Test
    public void testSaveProduction() throws Exception {
        String responseString = mockMvc.perform(
                post("/production/3")    //请求的url,请求的方法是get
//                post("/production/2")    //请求的url,请求的方法是get
//                post("/production/1")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("effectPicture", "国际处主页")         //添加参数
                        .param("introduction", "国际处主页")         //添加参数
                        .param("url", "国际处主页")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testDeleteProduction() throws Exception {
        String responseString = mockMvc.perform(
                delete("/production/name/5")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }


    @Test
    public void testDeleteProductions() throws Exception {
        String responseString = mockMvc.perform(
                delete("/production/names")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("names", "1,2,3")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }


    @Test
    public void testAllProductions() throws Exception {
        String responseString = mockMvc.perform(
                get("/home/allProductions")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式

        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串

        System.out.println("--------返回的json = " + responseString);

        JsonUtil.prettyPrint(responseString);

    }


    @Test
    public void testListProductionsByName() throws Exception {
        String responseString = mockMvc.perform(
                get("/production/name/我/1").characterEncoding("utf-8")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("name", "国际处主页")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testListProductions() throws Exception {
        String responseString = mockMvc.perform(
                get("/production/listProductions/1")   //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("name", "国际处主页")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testUpdateProduction() throws Exception {
        String responseString = mockMvc.perform(
                put("/production/listMembersById")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("effectPicture", "国际dddddddd处主页")         //添加参数
                        .param("introduction", "国际处主页")         //添加参数
                        .param("url", "国际处主页")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

}