package studio.geek.action.admin;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studio.geek.entity.Member;
import studio.geek.exception.ErrorException;
import studio.geek.service.MemberService;
import studio.geek.util.JsonUtil;
import studio.geek.util.Page;
import studio.geek.util.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberAction {

    @Resource
    private MemberService memberService;


    /**
     * 首先需要判断学号是否存在，不存在就可以通过id增加成员
     *
     * @param file
     * @param member
     * @return
     */
    @RequestMapping(value = "/memberId", method = RequestMethod.POST)
//    public Result saveMember(@RequestParam(value = "file", required = false) MultipartFile file,
    public Result saveMember(@RequestPart(value = "file", required = false) MultipartFile file,
                             Member member) {


        Result result = new Result();
        if (memberService.saveMember(file, member) == false) {
            throw new ErrorException("数据库插入异常");
        }
        result.setSuccess(true);
        result.setData("添加成功");
        JsonUtil.prettyPrint(member);

        return result;
    }

    /**
     * 通过学号删除成员
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/memberId/{memberId}", method = RequestMethod.DELETE)
    public Result deleteMember(@PathVariable String memberId) {

        System.out.println("delete");
        Result result = new Result();
        if (memberService.deleteMember(memberId) == false) {
            throw new ErrorException("删除失败");
        }

        result.setSuccess(true);
        result.setData("删除成功");
        return result;
    }


    /**
     * 通过学号批量删除成员
     *
     * @param memberIds
     * @return
     */
    @RequestMapping(value = "/memberIds", method = RequestMethod.DELETE)
    public Result deleteMembers(@RequestParam List<String> memberIds) {
        Result result = new Result();
        if (memberService.deleteMembers(memberIds) == false) {
            throw new ErrorException("删除失败");
        }

        result.setSuccess(true);
        result.setData("删除成功");

        return result;
    }

    /**
     * 通过id查询成员
     *
     * @param memberId
     * @param order
     * @return
     */
    @RequestMapping(value = "/memberId/{memberId}/{currentPage}",
            method = RequestMethod.GET)
    public Result listMembersById(@PathVariable String memberId,
                                  @PathVariable String currentPage,
                                  @RequestParam(defaultValue = "memberId", required = false) String order,
                                  @RequestParam(defaultValue = "ASC", required = false) String orderType) {

        Page page = new Page();
        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("memberId", memberId);
        if (order.equals("memberId")) {
            page.getParameters().put("order", "member_id");
        } else
            page.getParameters().put("order", order);


        page.getParameters().put("orderType", orderType);

        page.setCurrentPage(page.checkCurrentPage(currentPage));


        List<Member> members = memberService.listMembersById(page);

        if (members.isEmpty()) {
            throw new ErrorException("not found.");
        }
        Result result = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listMembers", members);

        System.out.println(memberId);

        System.out.println("listMembersById");

        result.setSuccess(true);

        result.setData(map);
        return result;
    }

    /**
     * 通过名字查询成员
     *
     * @param name
     * @param order
     * @return
     */
    @RequestMapping(value = "/name/{name}/{currentPage}", method = RequestMethod.GET)
    public Result listMembersByName(@PathVariable String name,
                                    @PathVariable String currentPage,
                                    @RequestParam(defaultValue = "memberId", required = false) String order,
                                    @RequestParam(defaultValue = "ASC", required = false) String orderType) {

        Page page = new Page();

        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("orderType", orderType);

        page.getParameters().put("name", name);

        if (order.equals("memberId")) {
            page.getParameters().put("order", "member_id");
        } else
            page.getParameters().put("order", order);

        page.setCurrentPage(page.checkCurrentPage(currentPage));

        List<Member> members = memberService.listMembersByName(page);

        if (members.isEmpty()) {
            throw new ErrorException("not found.");
        }
        Result result = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listMembers", members);
        result.setSuccess(true);
        result.setData(map);
        return result;
    }

    /**
     * 用于后台管理界面，列出所有未毕业成员
     *
     * @param currentPage
     * @param order
     * @return
     */
    @RequestMapping(value = "/listCurrentMembers/{currentPage}", method = RequestMethod.GET)
    public Result listCurrentMembers(@PathVariable String currentPage,
                                     @RequestParam(defaultValue = "memberId", required = false) String order,
                                     @RequestParam(defaultValue = "ASC", required = false) String orderType) {


        Page page = new Page();

        System.out.println(order);

        if (order.equals("memberId")) {
            page.getParameters().put("order", "member_id");
        } else
            page.getParameters().put("order", order);

        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("orderType", orderType);

        page.setCurrentPage(page.checkCurrentPage(currentPage));

        Result result = new Result();
        List<Member> members = memberService.listCurrentMembers(page);
        if (members.isEmpty()) {
            throw new ErrorException("not found");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listMembers", members);
        result.setSuccess(true);
        result.setData(map);

        return result;
    }

    /**
     * 用于后台管理界面，列出所有已经毕业成员
     *
     * @param currentPage
     * @param order
     * @return
     */
    @RequestMapping(value = "/listOldMembers/{currentPage}", method = RequestMethod.GET)
    public Result listOldMembers(@PathVariable String currentPage,
                                 @RequestParam(defaultValue = "memberId", required = false) String order,
                                 @RequestParam(defaultValue = "ASC", required = false) String orderType) {


        Page page = new Page();

        System.out.println(order);

        if (order.equals("memberId")) {
            page.getParameters().put("order", "member_id");
        } else
            page.getParameters().put("order", order);

        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("orderType", orderType);

        page.setCurrentPage(page.checkCurrentPage(currentPage));
        Result result = new Result();

        List<Member> members = memberService.listOldMembers(page);
        if (members.isEmpty()) {
            throw new ErrorException("not found");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listMembers", members);
        result.setSuccess(true);
        result.setData(map);
        return result;
    }

    /**
     * 通过以前的学号查询到id后更改学号
     *
     * @param file
     * @param member
     * @return
     */

    @RequestMapping(value = "/memberId/{originalMemberId}", method = RequestMethod.PUT)
    public Result updateMember(@RequestParam(value = "file", required = false) MultipartFile file,
                               @PathVariable String originalMemberId,
                               Member member) {
        JsonUtil.prettyPrint(member);
        Result result = new Result();
        if (memberService.updateMember(file, member, originalMemberId) == false) {
            throw new ErrorException("修改失败");
        }
        System.out.println("success");
        result.setSuccess(true);
        result.setData("修改成功");
        return result;
    }
}
