package com.spring.assetStock;


import org.apache.hadoop.hive.ql.exec.U;
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
@RequestMapping("/stockAsset")
public class AssetStockController {

    @Resource
    private AssetStockService stockAssetService;

    /*
    * 查找库存信息
    * */
    @RequestMapping("/selectStockAsset.do")
    @ResponseBody
    public Map selectStockAsset(Integer page, Integer limit, String param, String bzDate1, String bzDate2,String uname,String param2,
                                String param3,String param4,String param5,String param6,String param7,String param8,
                                String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                                String param13p,String param13l,String param14,String param15,String param17,
                                 String param19) {

        return stockAssetService.selectStockAsset(page, limit, param, bzDate1,bzDate2,uname,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10,param11p,param11l,param12p,param12l,
                param13p,param13l, param14,param15,param17,
                param19);

    }
    /*
    * 修改库存信息
    * */
    @RequestMapping("/upStockAsset.do")
    @ResponseBody
    public Map upRepairAsset(String ID,String ASSETS_ID,String ASSETS_NAME,String ASSETCLASS_1,String ASSETCLASS_2,String BRAND,
                             String TYPE_NAME,String NUM,String UNIT,String POSTINGDATE,
                             String PURCHASEDATE,String WARRANTY,String SERVICELIFE,String ATTRIBUTION,
                             String RESPMAN,String PRICE,String COMPANY,String USEDESC,String uname) {

        return stockAssetService.upStockAsset(ID,ASSETS_ID,ASSETS_NAME,ASSETCLASS_1,ASSETCLASS_2,BRAND,
                TYPE_NAME,NUM,UNIT,POSTINGDATE,PURCHASEDATE,WARRANTY,SERVICELIFE,ATTRIBUTION,
                RESPMAN,PRICE,COMPANY,USEDESC,uname);

    }
    /*
    * 增加库存信息
    * */
    @RequestMapping("/addStockAsset.do")
    @ResponseBody
    public Map addRepairAsset(String ASSETS_ID,String ASSETS_NAME,String ASSETCLASS_1,String ASSETCLASS_2,String BRAND,
                              String TYPE_NAME,String NUM,String UNIT,String POSTINGDATE,
                              String PURCHASEDATE,String WARRANTY,String SERVICELIFE,String ATTRIBUTION,
                              String RESPMAN,String PRICE,String COMPANY,String USEDESC,String uname,String LOCATION) {

        return stockAssetService.addStockAsset(ASSETS_ID,ASSETS_NAME,ASSETCLASS_1,ASSETCLASS_2,BRAND,
                TYPE_NAME,NUM,UNIT,POSTINGDATE,PURCHASEDATE,WARRANTY,SERVICELIFE,ATTRIBUTION,
                RESPMAN,PRICE,COMPANY,USEDESC,uname,LOCATION);

    }
    /*
    * 删除库存信息
    * */
    @RequestMapping("/delStockAsset.do")
    @ResponseBody
    public Map delRepairAsset(String ID){
        return stockAssetService.delStockAsset(ID);
    }
    /*
    * 导出库存信息
    * */
    @RequestMapping("/exlStockAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlRepairAsset(String param, String bzDate1, String bzDate2,String uname){
        return stockAssetService.exlStockAsset(param, bzDate1,bzDate2,uname);
    }
/*
* 一级分类
* */
    @RequestMapping("/selYJFL.do")
    @ResponseBody
    public List<Map<String,Object>> selYJFL(){
        return stockAssetService.selYJFL();
    }

    /*
    * 二级分类
    * */
    @RequestMapping("/selEJFL.do")
    @ResponseBody
    public List<Map<String,Object>> selEJFL(){
        return stockAssetService.selEJFL();
    }
/*
* 查找部门
* */
    @RequestMapping("/selBM.do")
    @ResponseBody
    public List<Map<String,Object>> selBM(){
        return stockAssetService.selBM();
    }
/*
* 查找存放地点
* */
    @RequestMapping("/selCFDD.do")
    @ResponseBody
    public List<Map<String,Object>> selCFDD(String company){
        return stockAssetService.selCFDD(company);
    }
    /*
     * 查找单位
     * */
    @RequestMapping("/selDW.do")
    @ResponseBody
    public List<Map<String,Object>> selDW(){
        return stockAssetService.selDW();
    }
    /*
     * 查找面板类型
     * */
    @RequestMapping("/selMBLX.do")
    @ResponseBody
    public List<Map<String,Object>> selMBLX(){
        return stockAssetService.selMBLX();
    }
    /*
     * 查找故障等级
     * */
    @RequestMapping("/selGZDJ.do")
    @ResponseBody
    public List<Map<String,Object>> selGZDJ(){
        return stockAssetService.selGZDJ();
    }
    /*
     * 查找盘点部门
     * */
    @RequestMapping("/selPDBM.do")
    @ResponseBody
    public List<Map<String,Object>> selPDBM(String uname){
        return stockAssetService.selPDBM(uname);
    }
    /*
     * 查找归属公司
     * */
    @RequestMapping("/selGSGS.do")
    @ResponseBody
    public List<Map<String,Object>> selGSGS(String uname){
        return stockAssetService.selGSGS(uname);
    }
    /*
     * 通过名称查找公司
     * */
    @RequestMapping("/selGS.do")
    @ResponseBody
    public List<Map<String,Object>> selGS(String uname){
        return stockAssetService.selGS(uname);
    }
}
