package studio.geek.util;

/**
 * Created by think on 2017/1/26.
 */
public class Result {

    private boolean success;
    private Object data;

    public Result() {
    }

    public Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
