package com.itao.database;


import com.itao.sender.PageRequestMap;
import com.itao.util.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@Intercepts({    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class PagePlugin implements Interceptor {
    private static String dialect = "";
    private static String pageSqlId = "";

    public PagePlugin() {
    }

    public Object intercept(Invocation ivk) throws Throwable {
        if(ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler)this.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement)this.getValueByFieldName(delegate, "mappedStatement");
            if(mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if(parameterObject instanceof PageRequestMap) {
                    PageRequestMap pr = (PageRequestMap)parameterObject;
                    Connection connection = (Connection)ivk.getArgs()[0];
                    String sql = boundSql.getSql();
                    String countSql = this.createCountSql(sql);
                    PreparedStatement ps = connection.prepareStatement(countSql);
                    delegate.getParameterHandler().setParameters(ps);
                    ResultSet rs = ps.executeQuery();
                    int count = 0;
                    if(rs.next()) {
                        count = rs.getInt(1);
                    }

                    rs.close();
                    pr.put("total", Integer.valueOf(count));
                    String pageSql = this.generatePageSql(sql, pr);
                    this.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }

        return ivk.proceed();
    }

    public String createCountSql(String dataSql) {
        String oldSql = new String(dataSql);
        String sql = this.changeString(oldSql);
        int endIndex = sql.indexOf(" from ");
        if(endIndex == -1) {
            endIndex = sql.indexOf("\tfrom ");
        }

        String sqlBefore = " " + oldSql.substring(endIndex);
        int orderIndex = this.changeString(sqlBefore).toLowerCase().lastIndexOf(" order ");
        if(orderIndex != -1) {
            sqlBefore = sqlBefore.substring(0, orderIndex);
        }

        return "select count(*) " + sqlBefore;
    }

    private String changeString(String str) {
        return str.toLowerCase().replaceAll("\n|\r", " ").replaceAll("\t", " ");
    }

    public static void main(String[] args) {
        String m = "select * from tx_notice where 1 = 1 and Ispublish = 1 \t\t  \t\torder by publishtime desc ";
        PagePlugin p = new PagePlugin();
        System.out.println(p.createCountSql(m));
    }

    private String generatePageSql(String sql, PageRequestMap pr) {
        int pagesize = pr.getInt("pagesize");
        long currResult = pr.getCurrResult();
        StringBuffer pageSql = new StringBuffer();
        if("mysql".equals(dialect)) {
            pageSql.append(sql);
            String sid = pr.getString("sidx");
            String sord = pr.getString("sord");
            //TODO 小写的order by
            if(pageSql.indexOf("order by ") == -1 && pr.jqGridSubmit && !StringUtils.isEmpty(sid) && !StringUtils.isEmpty(sord)) {
                pageSql.append(" order by ").append(sid).append(" ").append(sord);
            }

            pageSql.append(" limit ").append(pagesize).append(" offset ").append(currResult);
        }

        return pageSql.toString();
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties properties) {
        dialect = properties.getProperty("dialect");
        pageSqlId = properties.getProperty("pageSqlId");
    }

    private Field getFieldByFieldName(Object obj, String fieldName) {
        Class superClass = obj.getClass();

        while(superClass != Object.class) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var5) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    private Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = this.getFieldByFieldName(obj, fieldName);
        Object value = null;
        if(field != null) {
            if(field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }

        return value;
    }

    private void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        if(field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }

    }
}