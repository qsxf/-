package com.spring.allocationAsset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/12.
 */
@Controller
@RequestMapping("/allocationAsset")
public class AllocationAssetController {

    @Resource
    private AllocationAssetService allocationAssetService;

    @RequestMapping("/selectAllocationAsset.do")
    @ResponseBody
    public Map selectRepairAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                 String param3,String param4,String param5,String param6,String param7,String param8,
                                 String param9,String param10,String param11) {

        return allocationAssetService.selectAllocationAsset(page, limit, param, bzDate,uname,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11);

    }

    @RequestMapping("/upAllocationAsset.do")
    @ResponseBody
    public Map upRepairAsset(String bxcode,String bxdate,String num,String bzName,String phone,String sqgs,String sqbm,String dbdz,String zrr,String dbqk,String ID,String uname) {

        return allocationAssetService.upAllocationAsset(bxcode,bxdate,num,bzName,phone,sqgs,sqbm,dbdz,zrr,dbqk,ID,uname);

    }
    @RequestMapping("/delAllocationAsset.do")
    @ResponseBody
    public Map delRepairAsset(String ID){
        return allocationAssetService.delAllocationAsset(ID);
    }
    @RequestMapping("/exlAllocationAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlRepairAsset(String param, String bzDate,String uname){
        return allocationAssetService.exlAllocationAsset(param, bzDate,uname);
    }
}
