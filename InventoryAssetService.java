package com.spring.inventoryAsset;

import com.spring.repairAsset.RepairAssetDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryAssetService {
    @Resource
    private InventoryAssetDao inventoryAssetDao;

    public Map selectRepairAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                 String param3,String param4,String param5,String param6,String param7,String param8,
                                 String param9p,String param9l,String param10p,String param10l,String param11) {
        Map redMap = new HashMap();
        List user = inventoryAssetDao.selectUser(uname);
        List<Map<String, Object>> list = inventoryAssetDao.selectInventoryAsset(page, limit, param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9p,param9l,param10p,param10l,param11);
        Map map = inventoryAssetDao.countInventoryAsset(param, bzDate,uname,user,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9p,param9l,param10p,param10l,param11);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map upRepairAsset(String sjkssj,String sjjssj,String pcs,String uname,String ID,String param,String bz,String zsl) {
        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            String status = "";

            String[] data= param.split(";");
            if (zsl.equals(data.length+"")){
                status = "1";
            }else if (!zsl.equals(data.length+"")&&data.length!=0){
                status = "2";
            }else {
                status = "0";
            }
            String INVENTORYASSET = "";
            String[] data2 = new String[data.length];
            inventoryAssetDao.upInventoryAsset(sjkssj,sjjssj,data.length+"",uname,ID,bz,status);
            for (int i=0;i<data.length;i++) {
                String[] datas = data[i].split(",");
                String assets_id = datas[0];
                INVENTORYASSET = datas[1];
                String uuname = datas[2];
                data2[i] = assets_id;
                List<Map<String, Object>> listResurt = inventoryAssetDao.selectInventoryAssetLog(INVENTORYASSET, uname, assets_id);
                if (listResurt.isEmpty()) {
                    inventoryAssetDao.addInventoryAssetLog( INVENTORYASSET, uuname, assets_id, "0");
                } else {
                    inventoryAssetDao.updetInventoryAssetLog(INVENTORYASSET, uuname, "0", assets_id);
                }
            }
            inventoryAssetDao.updetInventoryAssetLog1(INVENTORYASSET,uname,data2);
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
                inventoryAssetDao.delInventoryAsset(ids[i]);
            }
            //inventoryAssetDao.delInventoryAsset(bxcode);

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
        List user = inventoryAssetDao.selectUser(uname);
        return inventoryAssetDao.exlInventoryAsset(param, bzDate,uname,user);
    }

    public Map selectFixAsset( String param,String uname,String pdbh) {
        Map redMap = new HashMap();

        List<Map<String, Object>> list = inventoryAssetDao.selectFixAsset( param,uname,pdbh);
        Map map = inventoryAssetDao.countFixAsset(param,uname);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public Map addInventoryAsset(String pdbh,String jhkssj,String jhjssj,String sjkssj,String sjjssj,String pdr,String yps,String pcs, String bz, String zdbm, String uname) {
        Map map = new HashMap();
        String code = "0";
        String msg = "添加成功！";


        try {
            String[] attribu = inventoryAssetDao.sumAttribution(zdbm,uname).get(0).toString().split("=");
            String[] attribu1 = attribu[1].split("}");
            yps = attribu1[0];
            if (yps.equals("null")){
                yps = "0";
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            pdbh = df.format(new Date());
            inventoryAssetDao.addInventoryAsset(pdbh,jhkssj,jhjssj,sjkssj,sjjssj,pdr,yps,pcs,bz,zdbm,uname);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "添加失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }



    public Map selectLicense(Integer page, Integer limit, String param){
        Map redMap=new HashMap();

        List<Map<String, Object>> list = inventoryAssetDao.selectLicense(page, limit, param);
        Map map = inventoryAssetDao.licenseCount(param);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }


    public Map addLicense(String LICENSENUM,String LICENSECOMPANY,String ASSETS_ID,String uname){
        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            inventoryAssetDao.addLicense(LICENSENUM,LICENSECOMPANY,ASSETS_ID,uname);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map updetLicense(String LICENSENUM,String LICENSECOMPANY,String uname,String ID){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            inventoryAssetDao.updetLicense(LICENSENUM,LICENSECOMPANY,uname,ID);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delLicense(String id){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                inventoryAssetDao.delLicense(ids[i]);
            }
            //inventoryAssetDao.delLicense(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map addInventoryAssetLog(String param,String INVENTORYASSET,String uname){
        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";

        try {

            String[] data= param.split(";");
            for (int i=0;i<data.length;i++){
                String[] datas= data[i].split(",");
                String assets_id = datas[0];
                String data2 = datas[1];
                List<Map<String,Object>> listResurt = inventoryAssetDao.selectInventoryAssetLog(INVENTORYASSET,uname,assets_id);
                if (data2 .equals("true")){
                    data2 = "0";
                }else {
                    data2 = "1";
                }
                if (listResurt.isEmpty()){
                    inventoryAssetDao.addInventoryAssetLog(INVENTORYASSET,uname,assets_id,data2);
                }else {
                    inventoryAssetDao.updetInventoryAssetLog(INVENTORYASSET,uname,data2,assets_id);
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map selectInventoryAssetLog(String INVENTORYASSET,String uname){
        Map redMap=new HashMap();

        List<Map<String, Object>> list = inventoryAssetDao.selectInventoryAssetLog1(INVENTORYASSET, uname);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("data", list);
        return redMap;

    }
    /*
     * 查询有无相同盘点编号
     * */
    public List<Map<String,Object>> selInventoryid(String inventoryid,String uname){
        return inventoryAssetDao.selInventoryid(inventoryid,uname);
    }
}
