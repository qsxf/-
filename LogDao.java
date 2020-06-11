package com.spring.log;


import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class LogDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;
    /**
     * 添加日志信息
     *
     * @return
     */
    public int addLog(String OPER_TYPE,String OPER_PAGE,String OPER_TABLE,String DESCR,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO IT_ASSET_LOG(OPER_TYPE,OPER_PAGE,OPER_TABLE,DESCR,CREATE_TIME,CREATE_NAME) \n" +
                "VALUES(?,?,?,?,SYSDATE,?)");
        return itsmJdbcTemplate.update(sql.toString(),OPER_TYPE,OPER_PAGE,OPER_TABLE,DESCR,uname);

    }


}
