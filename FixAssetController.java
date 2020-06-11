package com.spring.fixAsset;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资产入库controller层
 */
@Controller
@RequestMapping("/fixAsset")
public class FixAssetController {

    @Resource
    private FixAssetService fixAssetService;

    @RequestMapping("/selectFixAsset.do")
    @ResponseBody
    public Map selectFixAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param2,
                              String param3,String param4,String param5,String param6,String param7,String param8,
                              String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                              String param13p,String param13l,String param14,String param15,String param16,String param17,
                              String param18,String param19) {

        return fixAssetService.selectFixAsset(page, limit, param, bzDate,uname,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                 param13p,param13l, param14,param15,param16,param17,
                param18,param19);

    }
    @RequestMapping("/upFixAsset.do")
    @ResponseBody
    public Map upFixAsset(String ID,String ASSETS_ID,String ASSETS_NAME,String ASSETCLASS_1,String ASSETCLASS_2,String BRAND,
                             String TYPE_NAME,String NUM,String UNIT,String POSTINGDATE,
                             String PURCHASEDATE,String WARRANTY,String SERVICELIFE,String ATTRIBUTION,
                             String RESPMAN,String PRICE,String COMPANY,String uname,String USEDESC) {

        return fixAssetService.upFixAsset(ID,ASSETS_ID,ASSETS_NAME,ASSETCLASS_1,ASSETCLASS_2,BRAND,
                TYPE_NAME,NUM,UNIT,POSTINGDATE,PURCHASEDATE,WARRANTY,SERVICELIFE,ATTRIBUTION,
                RESPMAN,PRICE,COMPANY,uname,USEDESC);

    }

    @RequestMapping("/delFixAsset.do")
    @ResponseBody
    public Map delFixAsset(String id){
        return fixAssetService.delFixAsset(id);
    }
    @RequestMapping("/exlFixAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlFixAsset(String param,String bzDate,String uname){
        return fixAssetService.exlFixAsset(param, bzDate,uname);
    }

    @RequestMapping("/addAllocationAsset.do")
    @ResponseBody
    public Map addAllocationAsset(String zcbh,String dbrq,String dbbh,String dbnum,String sqr,
                             String phone,String sqgs,String sqbm,String dbdz,String zrr,
                             String dbqk,String uname,String ID) {

        return fixAssetService.addAllocationAsset(zcbh,dbrq,dbbh,dbnum,sqr,phone,sqgs,sqbm,dbdz,zrr,dbqk,uname,ID);

    }

    @RequestMapping("/addRepairAsset.do")
    @ResponseBody
    public Map addRepairAsset(String bxbh,String zcbh,String bxsj,String bzr,String gzlx,
                                  String gzdj,String gznr,String uname,String ID,String wxje) {

        return fixAssetService.addRepairAsset(bxbh,zcbh,bxsj,bzr,gzlx,gzdj,gznr,uname,ID,wxje);

    }

    @RequestMapping("/addDepreciationAsset.do")
    @ResponseBody
    public Map addDepreciationAsset(String depreciationId,String zcbh,String depredate,String depremethod,String serviceLife,
                              String netrate,String depremonths,String initialnetworth,String deprenowmonth,String depreall,String networth,String uname,String ID) {
        return fixAssetService.addDepreciationAsset(depreciationId,zcbh,depredate,depremethod,serviceLife,netrate,depremonths,initialnetworth,deprenowmonth,depreall,networth,uname,ID);

    }

    @RequestMapping("/addDumpAsset.do")
    @ResponseBody
    public Map addDumpAsset(String bfbh,String zcbh,String bfsj,String bfyy,String uname,String ID) {
        return fixAssetService.addDumpAsset(bfbh,zcbh,bfsj,bfyy,uname,ID);

    }
}
