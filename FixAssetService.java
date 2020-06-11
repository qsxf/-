package com.spring.fixAsset;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产入库service层
 */
@Service
public class FixAssetService {

    @Resource
    private FixAssetDao fixAssetDao;



    public Map selectFixAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param2,
                              String param3,String param4,String param5,String param6,String param7,String param8,
                              String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                              String param13p,String param13l,String param14,String param15,String param16,String param17,
                              String param18,String param19) {
        Map redMap = new HashMap();
        List user = fixAssetDao.selectUser(uname);
        List<Map<String, Object>> list = fixAssetDao.selectFixAsset(page, limit, param, bzDate,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                param13p,param13l, param14,param15,param16,param17,
                param18,param19);
        Map map = fixAssetDao.countStock(param, bzDate,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                param13p,param13l, param14,param15,param16,param17,
                param18,param19);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upFixAsset(String id,String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                             String typename,String num,String unit,String postingdate,
                             String purchasedate,String warranty,String servicelife,String attribution,
                             String respman,String price,String location,String uname,String usedesc) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            fixAssetDao.updateFixById(id,assetsid,assetsname,assetclass1,assetclass2,brand,
                    typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,
                    respman,price,location,uname,usedesc);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map delFixAsset(String id){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                fixAssetDao.delFixAsset(ids[i]);
            }
           //fixAssetDao.delFixAsset(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlFixAsset(String param,String bzDate,String uname){
        List user = fixAssetDao.selectUser(uname);
        return fixAssetDao.exlFixAsset(param, bzDate,uname,user);
    }

    public Map addAllocationAsset(String zcbh,String dbrq,String dbbh,String dbnum,String sqr,
                                  String phone,String sqgs,String sqbm,String dbdz,String zrr,
                                  String dbqk,String uname,String id) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            fixAssetDao.addAllocation(zcbh,dbrq,dbbh,dbnum,sqr,phone,sqgs,sqbm,dbdz,zrr,dbqk,uname);
            fixAssetDao.upAssetLocation(id,dbdz,sqgs,sqbm);
            if (dbdz.equals("仓库")){
                fixAssetDao.upAssetStatus(id,"01");
            }else{
                fixAssetDao.upAssetStatus(id,"03");
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map addRepairAsset(String bxbh,String zcbh,String bxsj,String bzr,String gzlx,
                              String gzdj,String gznr,String uname,String ID,String wxje) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            fixAssetDao.addRepair(bxbh,zcbh,bxsj,bzr,gzlx,gzdj,gznr,uname,wxje);
            fixAssetDao.upAssetStatus(ID,"02");
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map addDepreciationAsset(String depreciationId,String zcbh,String depredate,String depremethod,String serviceLife,
                                    String netrate,String depremonths,String initialnetworth,String deprenowmonth,String depreall,String networth,String uname,String ID) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            fixAssetDao.addDepreciation(depreciationId,zcbh,depredate,depremethod,serviceLife,netrate,depremonths,initialnetworth,deprenowmonth,depreall,networth,uname);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public Map addDumpAsset(String bfbh,String zcbh,String bfsj,String bfyy,String uname,String ID) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            fixAssetDao.addDump(bfbh,zcbh,bfsj,bfyy,uname);
            fixAssetDao.upAssetStatus(ID,"04");
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
