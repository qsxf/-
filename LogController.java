package com.spring.log;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资产罚则controller层
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;


    @RequestMapping("/addlog.do")
    @ResponseBody
    public Map addPenaltyAsset(String operType,String operPage,String operTable,String descr,String createName) {

        return logService.addLog(operType,operPage,operTable,descr,createName);

    }

}
