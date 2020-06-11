package com.spring.penaltyAsset;



import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PenaltyAssetService {

    @Resource
    private PenaltyAssetDao penaltyAssetDao;



    public Map selectPenaltyAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                  String param3,String param4,String param5,String param6p,String param6l,String param7,String param8) {
        Map redMap = new HashMap();
        List user = penaltyAssetDao.selectUser(uname);
        List<Map<String, Object>> list = penaltyAssetDao.selectPenaltyAsset(page, limit, param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6p,param6l,param7,param8);
        Map map = penaltyAssetDao.countPenaltyAsset(param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6p,param6l,param7,param8);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upPenaltyAsset(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                            String PENAL,String REMARK,String uname,String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            penaltyAssetDao.updatePenaltyById(PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                    PENAL,REMARK,uname,id);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map delPenaltyAsset(String bxcode){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= bxcode.split(",");
            for (int i=0 ;i<ids.length;i++){
                penaltyAssetDao.delPenaltyById(ids[i]);
            }
            //penaltyAssetDao.delPenaltyById(bxcode);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlPenaltyAsset(String param,String bzDate,String uname){
        List user = penaltyAssetDao.selectUser(uname);
        return penaltyAssetDao.exlPenaltyAsset(param, bzDate,uname,user);
    }

    public Map addPenaltyAsset(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                             String PENAL,String REMARK,String uname) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            penaltyAssetDao.addPenalty(PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                    PENAL,REMARK,uname);
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
