package com.spring.penaltyAsset;


import com.spring.assetStock.AssetStockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资产罚则controller层
 */
@Controller
@RequestMapping("/penaltyAsset")
public class PenaltyAssetController {

    @Resource
    private PenaltyAssetService penaltyAssetService;

    @RequestMapping("/selectPenaltyAsset.do")
    @ResponseBody
    public Map selectPenaltyAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                  String param3,String param4,String param5,String param6p,String param6l,String param7,String param8) {

        return penaltyAssetService.selectPenaltyAsset(page, limit, param, bzDate,uname,param1,param2,
                param3,param4,param5,param6p,param6l,param7,param8);

    }
    @RequestMapping("/upPenaltyAsset.do")
    @ResponseBody
    public Map upPenaltyAsset(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                              String PENAL,String REMARK,String uname,String ID) {

        return penaltyAssetService.upPenaltyAsset(PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                PENAL,REMARK,uname,ID);

    }
    @RequestMapping("/addPenaltyAsset.do")
    @ResponseBody
    public Map addPenaltyAsset(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                               String PENAL,String REMARK,String uname) {

        return penaltyAssetService.addPenaltyAsset(PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                PENAL,REMARK,uname);

    }
    @RequestMapping("/delPenaltyAsset.do")
    @ResponseBody
    public Map delPenaltyAsset(String ID){
        return penaltyAssetService.delPenaltyAsset(ID);
    }
    @RequestMapping("/exlPenaltyAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlPenaltyAsset(String param, String bzDate,String uname){
        return penaltyAssetService.exlPenaltyAsset(param, bzDate,uname);
    }

}
