package com.spring.intangibleAsset;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 无形资产controller层
 */
@Controller
@RequestMapping("/intangibleAsset")
public class IntangibleAssetController {

    @Resource
    private IntangibleAssetService intangibleAssetService;

    @RequestMapping("/selectIntangibleAsset.do")
    @ResponseBody
    public Map selectIntangibleAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                     String param3,String param4,String param5,String param6,String param7p,String param7l,String param8p,String param8l,
                                     String param9,String param10,String param11,String param12,
                                    String param13,String param14,String param15p,String param15l,String param16,String param17) {

        return intangibleAssetService.selectIntangibleAsset(page, limit, param, bzDate,uname,param1,param2,
                param3,param4,param5,param6,param7p,param7l,param8p,param8l,
                param9,param10,param11,param12,param13,param14,param15p,param15l,param16,param17);

    }
    @RequestMapping("/upIntangibleAsset.do")
    @ResponseBody
    public Map upIntangibleAsset(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                                 String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                                 String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                                 String uname,String ID,String TYPE_NAME,String UNIT,String STATUS,String LOCATION) {

        return intangibleAssetService.upIntangibleAsset(ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,ID,TYPE_NAME,UNIT,STATUS,LOCATION);

    }
    @RequestMapping("/addIntangibleAsset.do")
    @ResponseBody
    public Map addIntangibleAsset(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                                  String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                                  String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                                  String uname,String TYPE_NAME,String UNIT,String STATUS,String LOCATION) {

        return intangibleAssetService.addIntangibleAsset(ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,TYPE_NAME,UNIT,STATUS,LOCATION);

    }
    @RequestMapping("/delIntangibleAsset.do")
    @ResponseBody
    public Map delIntangibleAsset(String ID){
        return intangibleAssetService.delIntangibleAsset(ID);
    }
    @RequestMapping("/exlIntangibleAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlIntangibleAsset(String param, String bzDate,String uname){
        return intangibleAssetService.exlIntangibleAsset(param, bzDate,uname);
    }

    /*
     * 查找无形资产单位
     * */
    @RequestMapping("/selWXDW.do")
    @ResponseBody
    public List<Map<String,Object>> selWXDW(){
        return intangibleAssetService.selWXDW();
    }

    /*
     * 查找无形资产状态
     * */
    @RequestMapping("/selWXZT.do")
    @ResponseBody
    public List<Map<String,Object>> selWXZT(){
        return intangibleAssetService.selWXZT();
    }
    /*
     * 查找无形资产存放地点
     * */
    @RequestMapping("/selWXCFDD.do")
    @ResponseBody
    public List<Map<String,Object>> selWXCFDD(String uname){
        return intangibleAssetService.selWXCFDD(uname);
    }
}
