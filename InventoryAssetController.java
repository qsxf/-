package com.spring.inventoryAsset;

import com.spring.repairAsset.RepairAssetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inventoryAsset")
public class InventoryAssetController {
    @Resource
    private InventoryAssetService inventoryAssetService;


    @RequestMapping("/selectInventoryAsset.do")
    @ResponseBody
    public Map selectRepairAsset(Integer page, Integer limit, String param, String bzDate,String uname,String param1,String param2,
                                 String param3,String param4,String param5,String param6,String param7,String param8,
                                 String param9p,String param9l,String param10p,String param10l,String param11) {

        return inventoryAssetService.selectRepairAsset(page, limit, param, bzDate,uname,param1,param2,
                param3,param4,param5,param6,param7,param8,
                param9p,param9l,param10p,param10l,param11);

    }
    @RequestMapping("/upInventoryAsset.do")
    @ResponseBody
    public Map upRepairAsset(String sjpdsj,String sjjssj,String pcs,String uname,String ID,String param,String bz,String zsl) {

        return inventoryAssetService.upRepairAsset(sjpdsj,sjjssj,pcs,uname,ID,param,bz,zsl);

    }
    @RequestMapping("/delInventoryAsset.do")
    @ResponseBody
    public Map delRepairAsset(String bxcode){
        return inventoryAssetService.delRepairAsset(bxcode);
    }
    @RequestMapping("/exlInventoryAsset.do")
    @ResponseBody
    public List<Map<String,Object>> exlRepairAsset(String param,String bzDate,String uname){
        return inventoryAssetService.exlRepairAsset(param, bzDate,uname);
    }
    @RequestMapping("/selectAsset.do")
    @ResponseBody
    public Map selectFixAsset( String param,String uname,String pdbh) {

        return inventoryAssetService.selectFixAsset(param,uname,pdbh);

    }

    @RequestMapping("/addInventoryAsset.do")
    @ResponseBody
    public Map addInventoryAsset(String pdbh,String jhkssj,String jhjssj,String sjkssj,String sjjssj,String pdr,String yps,String pcs, String bz, String zdbm, String uname) {

        return inventoryAssetService.addInventoryAsset(pdbh,jhkssj,jhjssj,sjkssj,sjjssj,pdr,yps,pcs,bz,zdbm,uname);

    }



    @RequestMapping("/selectLicense.do")
    @ResponseBody
    public Map selectItRoom(Integer page, Integer limit, String param){
        return inventoryAssetService.selectLicense(page, limit, param);
    }

    @RequestMapping("/addLicense.do")
    @ResponseBody
    public Map addItRoom(String LICENSENUM,String LICENSECOMPANY,String ASSETS_ID,String uname){
        return inventoryAssetService.addLicense(LICENSENUM,LICENSECOMPANY,ASSETS_ID,uname);
    }
    @RequestMapping("/updetLicense.do")
    @ResponseBody
    public Map updetItRoom(String LICENSENUM,String LICENSECOMPANY,String uname,String ID){
        return inventoryAssetService.updetLicense(LICENSENUM,LICENSECOMPANY,uname,ID);
    }
    @RequestMapping("/delLicense.do")
    @ResponseBody
    public Map delItRoom(String id){
        return inventoryAssetService.delLicense(id);

    }

    @RequestMapping("/addInventoryAssetLog.do")
    @ResponseBody
    public Map addInventoryAssetLog(String param,String INVENTORYASSET,String uname) {

        return inventoryAssetService.addInventoryAssetLog(param,INVENTORYASSET,uname);

    }

    @RequestMapping("/selectInventoryAssetLog.do")
    @ResponseBody
    public Map selectInventoryAssetLog(String INVENTORYASSET,String uname){
        return inventoryAssetService.selectInventoryAssetLog(INVENTORYASSET, uname);
    }

    /*
     * 查询有无此盘点单号
     * */
    @RequestMapping("/selInventoryid.do")
    @ResponseBody
    public List<Map<String,Object>> selInventoryid(String inventoryid,String uname){
        return inventoryAssetService.selInventoryid(inventoryid,uname);
    }
}
