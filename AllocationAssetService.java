package com.spring.allocationAsset;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/12.
 */
@Service
public class AllocationAssetService {

    @Resource
    private AllocationAssetDao allocationAssetDao;

    public Map selectAllocationAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                     String param3,String param4,String param5,String param6,String param7,String param8,
                                     String param9,String param10,String param11) {
        Map redMap = new HashMap();
        List user = allocationAssetDao.selectUser(uname);
        List<Map<String, Object>> list = allocationAssetDao.selectAllocation(page, limit, param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11);
        Map map = allocationAssetDao.selCount(param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upAllocationAsset(String bxcode,String bxdate,String num,String bzName,String phone,String sqgs,String sqbm,String dbdz,String zrr,String dbqk,String id,String uname) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            allocationAssetDao.upRepairAsset(bxcode,bxdate,num,bzName,phone,sqgs,sqbm,dbdz,zrr,dbqk,id,uname);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }


    public Map delAllocationAsset(String bxcode){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= bxcode.split(",");
            for (int i=0 ;i<ids.length;i++){
                allocationAssetDao.delRepairAsset(ids[i]);
            }
            //allocationAssetDao.delRepairAsset(bxcode);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlAllocationAsset(String param,String bzDate,String uname){
        List user = allocationAssetDao.selectUser(uname);
        return allocationAssetDao.exlRepairAsset(param, bzDate,uname,user);
    }
}
