package com.spring.assetStock;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetStockService {

    @Resource
    private AssetStockDao stockAssetDao;



    public Map selectStockAsset(Integer page, Integer limit, String param, String bzDate1, String bzDate2,String uname,String param2,
                                String param3,String param4,String param5,String param6,String param7,String param8,
                                String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                                String param13p,String param13l,String param14,String param15,String param17,
                                String param19) {
        Map redMap = new HashMap();
        List user = stockAssetDao.selectUser(uname);
        List<Map<String, Object>> list = stockAssetDao.selectStockAsset(page, limit, param, bzDate1, bzDate2,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                param13p,param13l, param14,param15,param17,
                param19);
        Map map = stockAssetDao.countStockAsset(param, bzDate1,bzDate2,uname,user,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                param13p,param13l, param14,param15,param17,
                param19);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upStockAsset(String id,String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                            String typename,String num,String unit,String postingdate,
                            String purchasedate,String warranty,String servicelife,String attribution,
                            String respman,String price,String location,String usedesc,String uname) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            stockAssetDao.updateStockById(id,assetsid,assetsname,assetclass1,assetclass2,brand,
                    typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,
                    respman,price,location,usedesc,uname);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map delStockAsset(String bxcode){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= bxcode.split(",");
            for (int i=0 ;i<ids.length;i++){
                stockAssetDao.delStorkById(ids[i]);
            }
           // stockAssetDao.delStorkById(bxcode);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


    public List<Map<String,Object>> exlStockAsset(String param,String bzDate1,String bzDate2,String uname){
        List user = stockAssetDao.selectUser(uname);
        return stockAssetDao.exlStockAsset(param, bzDate1,bzDate2,uname,user);
    }

    public List<Map<String,Object>> selYJFL(){
        return stockAssetDao.selYJFL();
    }
    public List<Map<String,Object>> selEJFL(){
        return stockAssetDao.selEJFL();
    }
    public List<Map<String,Object>> selBM(){
        return stockAssetDao.selBM();
    }
    public List<Map<String,Object>> selDW(){
        return stockAssetDao.selDW();
    }
    public List<Map<String,Object>> selMBLX(){
        return stockAssetDao.selMBLX();
    }
    public List<Map<String,Object>> selGZDJ(){
        return stockAssetDao.selGZDJ();
    }
    public List<Map<String,Object>> selPDBM(String uname){
        return stockAssetDao.selPDBM(uname);
    }
    public List<Map<String,Object>> selGSGS(String uname){
        List user = stockAssetDao.selectUser(uname);
        return stockAssetDao.selGSGS(uname,user);
    }
    public Map addStockAsset(String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                             String typename,String num,String unit,String postingdate,
                             String purchasedate,String warranty,String servicelife,String attribution,
                             String respman,String price,String location,String usedesc,String uname,String loca) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            stockAssetDao.addStock(assetsid,assetsname,assetclass1,assetclass2,brand,
                    typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,
                    respman,price,location,usedesc,uname,loca);
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }
/*
* 查询存放地点
* */
    public List<Map<String,Object>> selCFDD(String company){
        return stockAssetDao.selCFDD(company);
    }
    /*
     * 根据姓名查询公司
     * */
    public List<Map<String,Object>> selGS(String uname){
        return stockAssetDao.selGS(uname);
    }
}
