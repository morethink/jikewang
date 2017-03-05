package studio.geek.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import studio.geek.util.JsonUtil;
import studio.geek.util.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by think on 2017/2/10.
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("interceptor");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(
                statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        BoundSql boundSql = statementHandler.getBoundSql();
        // 原始的SQL语句
        String sql = boundSql.getSql();
        System.out.println("未改造SQL语句" + sql);
        if (id.matches(".+list.+")) {
            System.out.println("启动Mybatis分页拦截器");
//            BoundSql boundSql = statementHandler.getBoundSql();
//            // 原始的SQL语句
//            String sql = boundSql.getSql();
            // 查询总条数的SQL语句
            String countSql = "select count(*) from (" + sql + ")a";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();

            Page page = (Page) boundSql.getParameterObject();

            JsonUtil.prettyPrint(page);

            if (rs.next()) {
                page.init(rs.getInt(1));
            }
            JsonUtil.prettyPrint(page);
            // 改造后带分页查询的SQL语句
            String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();

            System.out.println("改造后带分页查询的SQL语句" + pageSql);

            metaObject.setValue("delegate.boundSql.sql", pageSql);

            JsonUtil.prettyPrint(boundSql.getParameterObject());
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        System.out.println(properties.get("parameterInterceptor"));
    }
}
