package studio.geek.action.admin;

import org.springframework.web.bind.annotation.*;
import studio.geek.entity.Candidate;
import studio.geek.exception.ErrorException;
import studio.geek.service.CandidateService;
import studio.geek.util.Page;
import studio.geek.util.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/candidate")
public class CandidateAction {

    @Resource(name = "candidateService")
    private CandidateService candidateService;

    @RequestMapping(value = "/listCandidates/{currentPage}", method = RequestMethod.GET)
    public Result listCandidates(@PathVariable String currentPage,
                                 @RequestParam(defaultValue = "candidate_id", required = false) String order,
                                 @RequestParam(defaultValue = "ASC", required = false) String orderType) {
        Page page = new Page();

        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("orderType", orderType);

        System.out.println(order);

        if (order == null || order.equals("candidateId")) {
            System.out.println("ORDER BY candidate_id");
            page.getParameters().put("order", "candidate_id");
        } else
            page.getParameters().put("order", order);

        page.setCurrentPage(page.checkCurrentPage(currentPage));

// TODO ${parameters.order} 能成功排序，但是#{parameters.order}不能成功排序

        List<Candidate> candidates = candidateService.listCandidates(page);
        if (candidates.isEmpty()) {
            throw new ErrorException("not found.");
        }

        Result result = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listCandidates", candidates);
        result.setSuccess(true);

        result.setData(map);
        return result;
    }
}
