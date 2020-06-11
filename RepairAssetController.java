package com.spring.repairAsset;

import com.spring.bean.ItAssetDepreciation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/repairAsset")
public class RepairAssetController {
    @Resource
    private RepairAssetService repairAssetService;


    @RequestMapping("/selectRepairAsset.do")
    @ResponseBody
    public Map selectRepairAsset(Integer page, Integer limit, String param, String bzDate,String uname,
                                 String param1,String param2,String param3,String param4,String param5,String param6) {

        return repairAssetService.selectRepairAsset(page, limit, param, bzDate,uname,param1,param2,param3,param4,param5,param6);

    }
    @RequestMapping("/upRepairAsset.do")
    @ResponseBody
    public Map upRepairAsset(String zccode,String bxcode, String bxdate, String bzName, String bzType, String gzLve, String gznr, String wcdate, String uname,String id,String wxje) {

        return repairAssetService.upRepairAsset(zccode,bxcode, bxdate, bzName, bzType, gzLve, gznr, wcdate, uname,wxje,id);

    }
    @RequestMapping("/delRepairAsset.do")
    @ResponseBody
    public Map delRepairAsset(String bxcode){
        return repairAssetService.delRepairAsset(bxcode);
    }
    @RequestMapping("/exlRepairAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlRepairAsset(String param,String bzDate,String uname){
        return repairAssetService.exlRepairAsset(param, bzDate,uname);
    }
    /**
     *
     *
     *资产折旧
     *
     *
     *
     */

    @RequestMapping("/selectDepreciation.do")
    @ResponseBody
    public Map selectDepreciation(Integer page, Integer limit,String param){

        return repairAssetService.selectDepreciation(page, limit, param);
    }

    @RequestMapping("/exlDepreciation.do")
    @ResponseBody
    public List<Map<String,Object>> exlDepreciation(String param){
        return repairAssetService.exlDepreciation(param);
    }
    @RequestMapping("/updetDepreciation.do")
    @ResponseBody
    public Map updetDepreciation(ItAssetDepreciation iad) {
        return repairAssetService.updetDepreciation(iad);
    }
    @RequestMapping("/delDepreciation.do")
    @ResponseBody
    public Map delDepreciation(String id) {
        return repairAssetService.delDepreciation(id);
    }



    /**
     *
     *
     *报废清单
     *
     */

    @RequestMapping("/selectDumpAsset.do")
    @ResponseBody
    public Map selectDumpAsset(Integer page, Integer limit,String param,String datas,String uname,String param2,
                               String param3,String param4,String param5,String param6,String param7,String param8,
                               String param9,String param10){
        return repairAssetService.selectDumpAsset(page, limit, param, datas,uname,param2,
                param3,param4,param5,param6,param7,param8,
                param9,param10);
    }
    @RequestMapping("/exlDumpAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlDumpAsset(String param,String datas,String uname){
        return repairAssetService.exlDumpAsset(param, datas,uname);
    }
    @RequestMapping("/upDumpAsset.do")
    @ResponseBody
    public Map upDumpAsset(String reason,String dumpDate,String upName,String id) {

        return repairAssetService.upDumpAsset(reason, dumpDate,upName, id);
    }
    @RequestMapping("/delDumpAsset.do")
    @ResponseBody
    public Map delDumpAsset(String id) {
        return repairAssetService.delDumpAsset(id);
    }
}
