package studio.geek.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.JsonObject;
import org.junit.Test;
import studio.geek.entity.Candidate;
import studio.geek.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by think on 2017/2/3.
 */
public class JsonUtilTest {


    @Test
    public void testJson() {

//
//        System.out.println(get(jsonStr, "gname"));
        System.out.println(JsonUtil.toJson(new HashMap<String, Object>()));

        List<String> list = new ArrayList<String>();
        list.add("dd");
        list.add("d");
        System.out.println(JsonUtil.toJson(list));

        Candidate candidate = null;
        String json = "{\n" +
                "  \"id\" : \"2015210992\",\n" +
                "  \"name\" : \"李文浩\",\n" +
                "  \"school\" : \"重庆邮电大学\",\n" +
                "  \"major\" : \"信息管理与信息系统\",\n" +
                "  \"direction\" : \"后台\"\n" +
                "}";
        candidate = JsonUtil.toPOJO(json, Candidate.class);
        System.out.println(candidate);

        json = "[ {\n" +
                "  \"id\" : \"2015210991\",\n" +
                "  \"name\" : \"凌龙\",\n" +
                "  \"school\" : \"重庆邮电大学\",\n" +
                "  \"major\" : \"信息管理与信息系统\",\n" +
                "  \"direction\" : \"后台\"\n" +
                "}, {\n" +
                "  \"id\" : \"2015210992\",\n" +
                "  \"name\" : \"李文浩\",\n" +
                "  \"school\" : \"重庆邮电大学\",\n" +
                "  \"major\" : \"信息管理与信息系统\",\n" +
                "  \"direction\" : \"后台\"\n" +
                "} ]";


        List<Candidate> candidates = JsonUtil.toList(json, new TypeReference<List<Candidate>>() {
        });

//        //测试toMap()方法
//        for (Candidate candidate1 : candidates)
//            System.out.println("fff" + candidate1.getDirection() + candidate1.getSchool());
//
//        //测试getValue()方法
//        candidates = JsonUtil.fromJson(JsonUtil.get(json, "candidates"), new TypeToken<List<Candidate>>() {
//        }.getType());
//        for (Candidate candidate1 : candidates)
//            System.out.println(candidate1.getDirection() + candidate1.getSchool());

    }

    @Test
    public void testJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dd", "dd");
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.get("dd"));


    }


    @Test
    public void testJackson() {
        String json = "{\n" +
                "  \"candidates\": [\n" +
                "    {\n" +
                "      \"id\": \"2015210991\",\n" +
                "      \"name\": \"凌龙\",\n" +
                "      \"school\": \"重庆邮电大学\",\n" +
                "      \"major\": \"信息管理与信息系统\",\n" +
                "      \"direction\": \"后台\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2015210992\",\n" +
                "      \"name\": \"李文浩\",\n" +
                "      \"school\": \"重庆邮电大学\",\n" +
                "      \"major\": \"信息管理与信息系统\",\n" +
                "      \"direction\": \"后台\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
//        List<Candidate> candidates = JsonUtil.fromJson(JsonUtil.get(json, "candidates"), new TypeToken<List<Candidate>>() {
//        }.getType());
//        List<String> list = new ArrayList<String>();
//        list.add("dd");
//        list.add("d");
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(candidates));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
