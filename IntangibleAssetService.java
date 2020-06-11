package com.spring.intangibleAsset;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntangibleAssetService {

    @Resource
    private IntangibleAssetDao intangibleAssetDao;



    public Map selectIntangibleAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                     String param3,String param4,String param5,String param6,String param7p,String param7l,String param8p,String param8l,
                                     String param9,String param10,String param11,String param12,
                                     String param13,String param14,String param15p,String param15l,String param16,String param17) {
        Map redMap = new HashMap();
        List user = intangibleAssetDao.selectUser(uname);
        List<Map<String, Object>> list = intangibleAssetDao.selectIntangibleAsset(page, limit, param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7p,param7l,param8p,param8l,
                param9,param10,param11,param12,param13,param14,param15p,param15l,param16,param17);
        Map map = intangibleAssetDao.countIntangibleAsset(param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7p,param7l,param8p,param8l,
                param9,param10,param11,param12,param13,param14,param15p,param15l,param16,param17);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upIntangibleAsset(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                                 String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                                 String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                                 String uname,String ID,String TYPE_NAME,String UNIT,String STATUS,String LOCATION) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            intangibleAssetDao.updateIntangibleById(ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                    LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,ID,TYPE_NAME,UNIT,STATUS,LOCATION);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map delIntangibleAsset(String bxcode){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= bxcode.split(",");
            for (int i=0 ;i<ids.length;i++){
                intangibleAssetDao.delIntangibleById(ids[i]);
            }
           // intangibleAssetDao.delIntangibleById(bxcode);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlIntangibleAsset(String param,String bzDate,String uname){
        List user = intangibleAssetDao.selectUser(uname);
        return intangibleAssetDao.exlIntangibleAsset(param, bzDate,uname,user);
    }


    public Map addIntangibleAsset(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                             String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                             String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                             String uname,String TYPE_NAME,String UNIT,String STATUS,String LOCATION) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            intangibleAssetDao.addIntangible(ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                    LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,TYPE_NAME,UNIT,STATUS,LOCATION);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public List<Map<String,Object>> selWXDW(){
        return intangibleAssetDao.selWXDW();
    }
    public List<Map<String,Object>> selWXZT(){
        return intangibleAssetDao.selWXZT();
    }
    public List<Map<String,Object>> selWXCFDD(String uname){
        return intangibleAssetDao.selWXCFDD(uname);
    }
}
