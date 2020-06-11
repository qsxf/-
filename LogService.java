package com.spring.log;



import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {

    @Resource
    private LogDao logDao;





    public Map addLog(String OPER_TYPE,String OPER_PAGE,String OPER_TABLE,String DESCR,String uname) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            logDao.addLog(OPER_TYPE,OPER_PAGE,OPER_TABLE,DESCR,uname);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }
}
