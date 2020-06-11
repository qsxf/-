package com.spring.repairAsset;

import com.spring.bean.ItAssetDepreciation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairAssetService {
    @Resource
    private RepairAssetDao repairAssetDao;

    public Map selectRepairAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,String param3,String param4,String param5,String param6) {
        Map redMap = new HashMap();
        List<Map<String, Object>> user = repairAssetDao.selectUser(uname);
        List<Map<String, Object>> list = repairAssetDao.selectRepairAsset(page, limit, param,bzDate,uname,user,param1,param2,param3,param4,param5,param6);
        Map map = repairAssetDao.countRepairAsset(param, bzDate,uname,user,param1,param2,param3,param4,param5,param6);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upRepairAsset(String zccode,String bxcode, String bxdate, String bzName, String bzType, String gzLve, String gznr, String wcdate, String uname,String wxje,String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            repairAssetDao.upRepairAsset(bxcode, bxdate, bzName, bzType, gzLve, gznr, wcdate, uname,wxje,id);
            if (!StringUtils.isBlank(wcdate)){
                repairAssetDao.upAssetStatus(zccode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map delRepairAsset(String bxcode){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= bxcode.split(",");
            for (int i=0 ;i<ids.length;i++){
                repairAssetDao.delRepairAsset(ids[i]);
            }
            //repairAssetDao.delRepairAsset(bxcode);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlRepairAsset(String param,String bzDate,String uname){
        List user = repairAssetDao.selectUser(uname);
        return repairAssetDao.exlRepairAsset(param, bzDate,uname,user);
    }



    /**
     *
     *
     *资产折旧
     *
     *
     *
     */


    public Map selectDepreciation(Integer page, Integer limit,String param){
        Map redMap = new HashMap();

        List<Map<String, Object>> list = repairAssetDao.selectDepreciation(page, limit, param);
        Map map = repairAssetDao.countDepreciation(param);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }


    public List<Map<String,Object>> exlDepreciation(String param){
        return repairAssetDao.exlDepreciation(param);
    }

    public Map updetDepreciation(ItAssetDepreciation iad) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            repairAssetDao.updetDepreciation(iad);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delDepreciation(String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                repairAssetDao.delDepreciation(ids[i]);
            }
            //repairAssetDao.delDepreciation(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }



    /**
     *
     *
     *报废清单
     *
     */


    public Map selectDumpAsset(Integer page, Integer limit,String param,String datas,String uname,String param2,
                               String param3,String param4,String param5,String param6,String param7,String param8,
                               String param9,String param10){

        Map redMap = new HashMap();
        List user = repairAssetDao.selectUser(uname);
        List<Map<String, Object>> list = repairAssetDao.selectDumpAsset(page, limit, param,datas,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10);
        Map map = repairAssetDao.countDumpAsset(param,datas,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlDumpAsset(String param,String datas,String uname){
        List user = repairAssetDao.selectUser(uname);
      return repairAssetDao.exlDumpAsset(param, datas,uname,user);
    }

    public Map upDumpAsset(String reason,String dumpDate,String duid,String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            repairAssetDao.upDumpAsset(reason, dumpDate,duid, id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delDumpAsset(String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                repairAssetDao.delDumpAsset(ids[i]);
            }
            //repairAssetDao.delDumpAsset(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


}
