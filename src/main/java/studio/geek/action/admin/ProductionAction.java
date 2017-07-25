package studio.geek.action.admin;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studio.geek.entity.Production;
import studio.geek.exception.ErrorException;
import studio.geek.service.ProductionService;
import studio.geek.util.Page;
import studio.geek.util.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/production")
public class ProductionAction {



    @Resource
    private ProductionService productionService;

    /**
     * 首先需要判断name是否存在，不存在就可以通过name增加项目
     *
     * @param file
     * @param production
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result saveProduction(@RequestParam(value = "file", required = false) MultipartFile file,
                                 Production production) {
        Result result = new Result();
        if (productionService.saveProduction(file, production) == false) {
            throw new ErrorException("数据库插入异常");
        }
        result.setSuccess(true);
        result.setData("添加成功");
        return result;
    }

    /**
     * 删除
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public Result deleteProduction(@PathVariable String name) {
        Result result = new Result();
        if (productionService.deleteProduction(name) == false) {
            throw new ErrorException("删除失败");
        }
        result.setSuccess(true);
        result.setData("删除成功");
        return result;
    }


    /**
     * 通过名字批量删除成员
     *
     * @param names
     * @return
     */
    @RequestMapping(value = "/names", method = RequestMethod.DELETE)
    public Result deleteProductions(@RequestParam List<String> names) {
        Result result = new Result();
        if (productionService.deleteProductions(names) == false) {
            throw new ErrorException("删除失败");
        }
        result.setSuccess(true);
        result.setData("删除成功");
        return result;
    }

    /**
     * 通过名字查询项目
     *
     * @param name
     * @param order
     * @return
     */
    @RequestMapping(value = "/name/{name}/{currentPage}", method = RequestMethod.GET)
    public Result listProductionsByName(@PathVariable String name,
                                        @PathVariable String currentPage,
                                        @RequestParam(defaultValue = "name", required = false) String order,
                                        @RequestParam(defaultValue = "ASC", required = false) String orderType) {
        Page page = new Page();
        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }

        page.getParameters().put("name", name);
        page.getParameters().put("order", order);
        page.getParameters().put("orderType", orderType);
        page.setCurrentPage(page.checkCurrentPage(currentPage));
        List<Production> productions = productionService.listProductionsByName(page);
        if (productions.isEmpty()) {
            throw new ErrorException("not found.");
        }
        Result result = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listProductions", productions);
        result.setSuccess(true);
        result.setData(map);
        return result;
    }

    /**
     * 用于后台管理界面，列出所有项目
     *
     * @param currentPage
     * @param order
     * @return
     */
    @RequestMapping(value = "/listProductions/{currentPage}", method = RequestMethod.GET)
    public Result listProductions(@PathVariable String currentPage,
                                  @RequestParam(defaultValue = "name", required = false) String order,
                                  @RequestParam(defaultValue = "ASC", required = false) String orderType) {


        Page page = new Page();
        System.out.println(order);
        if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
            throw new ErrorException("你的排序方式怕是有问题哦");
        }
        page.getParameters().put("order", order);
        page.setCurrentPage(page.checkCurrentPage(currentPage));
        Result result = new Result();
        List<Production> productions = productionService.listProductions(page);
        if (productions.isEmpty()) {
            throw new ErrorException("not found");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("listProductions", productionService.listProductions(page));
        result.setSuccess(true);
        result.setData(map);
        return result;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.PUT)
    public Result updateProduction(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @PathVariable String name,
                                   Production production) {
        Result result = new Result();
        System.out.println("put");
        if (productionService.updateProduction(file, production, name) == false) {
            throw new ErrorException("修改失败");
        }
        result.setSuccess(true);
        result.setData("修改成功");
        return result;
    }
}
