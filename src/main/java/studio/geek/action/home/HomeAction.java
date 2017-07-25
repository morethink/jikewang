package studio.geek.action.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studio.geek.entity.Candidate;
import studio.geek.entity.Member;
import studio.geek.entity.Production;
import studio.geek.exception.ErrorException;
import studio.geek.service.HomeService;
import studio.geek.util.CurrentMember;
import studio.geek.util.Result;

import java.util.List;

/**
 * @author 李文浩
 * @version 2017/2/21.
 */


@RestController
@RequestMapping("/home")
public class HomeAction {


    @Autowired
    private HomeService homeService;


    /**
     * 用于在主页上展示已经毕业的工作室成员。
     *
     * @return
     */
    @RequestMapping(value = "/allOldMembers", method = RequestMethod.GET)
    public Result getAllOldMembers() {

        List<Member> members = homeService.getAllOldMembers();

        if (members.isEmpty()) {
            throw new ErrorException("not found.");
        }

        Result result = new Result();

        result.setSuccess(true);
        result.setData(members);

        return result;
    }

    /**
     * 用于在主页上通过年级展示现在的工作室成员。
     *
     * @return
     */
    @RequestMapping(value = "/currentMembers/{grade}", method = RequestMethod.GET)
    public Result getCurrentMembersByGrade(@PathVariable String grade) {

        List<CurrentMember> currentMembers = homeService.getCurrentMembersByGrade(grade);

        if (currentMembers.isEmpty()) {
            throw new ErrorException("not found.");
        }

        Result result = new Result();

        result.setSuccess(true);
        result.setData(currentMembers);

        return result;
    }


//    @CrossOrigin(origins = "http://www.cnblogs.com")
    @RequestMapping(value = "/allProductions", method = RequestMethod.GET)
    public Result getAllOldProductions() {

        List<Production> productions = homeService.getAllProductions();

        if (productions.isEmpty()) {
            throw new ErrorException("not found.");
        }

        Result result = new Result();

        result.setSuccess(true);
        result.setData(productions);

        return result;
    }

    @RequestMapping(value = "/saveCandidate", method = RequestMethod.POST)
    public Result saveCandidate(Candidate candidate) {
        if (homeService.saveCandidate(candidate) == false) {
            throw new ErrorException("服务器出现问题");
        } else {
            return new Result(true, "欢迎加入极客大家庭");
        }
    }

}
