package studio.geek.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 分页对应的实体类
 */
public class Page {
    /**
     * 总条数
     */

    @JsonIgnore
    private int totalNumber;
    /**
     * 当前第几页
     */

    private int currentPage;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 每页显示条数
     */

    private int pageSize = 5;
    /**
     * 数据库中limit的参数，从第几条开始取
     */
    @JsonIgnore
    private int dbIndex;
    /**
     * 数据库中limit的参数，一共取多少条
     */
    @JsonIgnore
    private int dbNumber;

    @JsonIgnore
    Map<String, String> parameters = new HashMap<String, String>();


    /**
     * 根据当前对象中属性值计算并设置相关属性值
     */
    public void init(int totalNumber) {

        setTotalNumber(totalNumber);
        // 计算总页数
        int totalPageTemp = this.totalNumber / this.pageSize;
        int plus = (this.totalNumber % this.pageSize) == 0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if (totalPageTemp <= 0) {
            totalPageTemp = 1;
        }

        this.totalPage = totalPageTemp;

        // 设置当前页数
        // 总页数小于当前页数，应将当前页数设置为总页数

        if (this.totalPage < this.currentPage) {
            this.currentPage = this.totalPage;
        }

        // 当前页数小于1设置为1
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }

        // 设置limit的参数
        this.dbIndex = (this.currentPage - 1) * this.pageSize;

//        this.dbNumber = Page.pageSize;
        this.dbNumber = this.pageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

//    public static int getPageSize() {
//        return pageSize;
//    }
//
//    public static void setPageSize(int pageSize) {
//        Page.pageSize = pageSize;
//    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public int checkCurrentPage(String currentPage) {

        Pattern pattern = Pattern.compile("[0-9]{1,9}");

        if (!pattern.matcher(currentPage).matches()) {
            return 1;
        } else {
            return Integer.valueOf(currentPage);
        }
    }
}
