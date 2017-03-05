package studio.geek.action;
/**
 * Created by think on 2017/1/19.
 */

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhengcanrui on 16/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})


//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional

@WebAppConfiguration
public class CandidateActionTest {


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
    public void testSp() {
        System.out.println("dd");
    }

    @Test
    public void testListCandidates() throws Exception {
        String responseString = mockMvc.perform(
                get("/candidate/listCandidates/1")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
//                        .param("order","name")
//                        .param("orderType","DESC")
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串


        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testSaveCandidate() throws Exception {
    String responseString = mockMvc.perform(
            post("/candidate/saveCandidate")    //请求的url,请求的方法是get
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                    .param("name", "201521")         //添加参数
                    .param("candidateId", "201521")         //添加参数
                    .param("image", "201521")         //添加参数
                    .param("direction", "201521")         //添加参数
                    .param("introduction", "201521")         //添加参数
                    .param("company", "201521")         //添加参数
    ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
            .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串


JsonUtil.prettyPrint(responseString);
    }
}
