package studio.geek.action;//package studio.geek;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import studio.geek.util.JsonUtil;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberActionTest extends BaseTest {

    @Test
    public void testSaveMember() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("avatarFile",
                new FileInputStream(new File(("E:\\Synchronization\\images\\IDR_THEME_NTP_BACKGROUND.png"))));
        String responseString = mockMvc.perform(
                post("/member/memberId/12")    //请求的url,请求的方法是get
//                fileUpload("/member/memberId/5")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("id", "201521")         //添加参数
                        .param("name", "201521")         //添加参数
                        .param("sex", "男")         //添加参数
                        .param("image", "201521")         //添加参数
                        .param("direction", "201521")         //添加参数
                        .param("introduction", "201521")         //添加参数
                        .param("company", "201521")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);


    }

    @Test
    public void testUploadAvatar() throws Exception {

//        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", new FileInputStream(new File(("E:\\Synchronization\\images\\IDR_THEME_NTP_BACKGROUND.png"))));
//        mockMvc.perform(fileUpload("/member/memberId/5")
//                        .file(mockMultipartFile)
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
////                .param("type", "img")
//        ).andDo(print()).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testDeleteMember() throws Exception {
        String responseString = mockMvc.perform(
                delete("/member/memberId/22")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
//                        .param("memberId", "12")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testDeleteMembes() throws Exception {
        String responseString = mockMvc.perform(
                delete("/member/memberIds")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
                        .param("memberIds", "1,2,3")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testAllOldMembers() throws Exception {
        String responseString = mockMvc.perform(
                get("/home/allOldMembers")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
//                        .param("name", "李文浩")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }


    @Test
    public void testAllCurrentMembers() throws Exception {
        String responseString = mockMvc.perform(
                get("                                                                                          ")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
//                        .param("name", "李文浩")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }


    @Test
    public void testListMembersById() throws Exception {
        String responseString = mockMvc.perform(
                get("/member/memberId/2015210992/1").characterEncoding("utf-8")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON_UTF8)  //数据的格式
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
//                        .param("name", "李文浩")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testListMembersByName() throws Exception {
        String responseString = mockMvc.perform(
                get("/member/name/李/1").characterEncoding("utf-8")   //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
                        .param("order", "name")         //添加参数
                        .param("orderType", "DESC")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }

    @Test
    public void testListCurrentMembers() throws Exception {
        String responseString = mockMvc.perform(
                get("/member/listCurrentMembers/1")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
                        .param("order", "name")         //添加参数
                        .param("orderType", "DESC")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);

    }

    @Test
    public void testListOldMembers() throws Exception {
        String responseString = mockMvc.perform(
                get("/member/listOldMembers/3")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_JSON)  //数据的格式
                        .param("order", "name")         //添加参数
                        .param("orderType", "DESC")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);

    }

    @Test
    public void testUpdateMember() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("avatarFile",
                new FileInputStream(new File(("E:\\Synchronization\\images\\IDR_THEME_NTP_BACKGROUND.png"))));
        String responseString = mockMvc.perform(
//                put("/member/memberId/5")    //请求的url,请求的方法是get
                fileUpload("/member/memberId/5")
                        .file(mockMultipartFile).param("type", "img")
//                        .contentType(MediaType.MULTIPART_FORM_DATA)  //数据的格式
                        .param("memberId", "5")         //添加参数
                        .param("name", "201521")         //添加参数
                        .param("sex", "1")         //添加参数
                        .param("image", "201521")         //添加参数
                        .param("direction", "201521")         //添加参数
                        .param("introduction", "201521")         //添加参数
                        .param("company", "201521")         //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = ");
        JsonUtil.prettyPrint(responseString);
    }
}