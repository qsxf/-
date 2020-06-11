package com.spring.ItInfoData;

import com.spring.bean.ItAssetCable;
import com.spring.bean.ItAssetNetequipment;
import com.spring.bean.ItAssetPanel;
import com.spring.repairAsset.RepairAssetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ItInfoData")
public class ItInfoDataController {
    @Resource
    private ItInfoDataService itInfoDataService;



    /**
     *
     *机房信息
     *
     *
     *
     */
    @RequestMapping("/selectItRoom.do")
    @ResponseBody
    public Map selectItRoom(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectItRoom(page, limit, param,uname);
    }
    @RequestMapping("/exlItRoom.do")
    @ResponseBody
    public List<Map<String,Object>> exlItRoom(String param,String uname){
        return itInfoDataService.exlItRoom(param,uname);
    }
    @RequestMapping("/addItRoom.do")
    @ResponseBody
    public Map addItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String crName){
        return itInfoDataService.addItRoom(roomName, location, floor, roomnum, area, cabinetnum, crName);
    }
    @RequestMapping("/updetItRoom.do")
    @ResponseBody
    public Map updetItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String upName,String id){
        return itInfoDataService.updetItRoom(roomName, location, floor, roomnum, area, cabinetnum, upName, id);
    }
    @RequestMapping("/delItRoom.do")
    @ResponseBody
    public Map delItRoom(String id){
        return itInfoDataService.delItRoom(id);

    }

    /**
     *
     *机柜信息
     *
     *
     *
     */



    @RequestMapping("/selectCabinet.do")
    @ResponseBody
    public Map selectCabinet(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectCabinet(page, limit, param,uname);
    }
    @RequestMapping("/exlCabinet.do")
    @ResponseBody
    public List<Map<String,Object>> exlCabinet(String param,String uname){
        return itInfoDataService.exlCabinet(param,uname);
    }
    @RequestMapping("/addCabinet.do")
    @ResponseBody
    public Map addCabinet(String carName,String carUhihg,String crName){
        return itInfoDataService.addCabinet(carName, carUhihg, crName);
    }
    @RequestMapping("/updetCabinet.do")
    @ResponseBody
    public Map updetCabinet(String carName,String carUhihg,String upName,String id){
        return itInfoDataService.updetCabinet(carName, carUhihg, upName, id);
    }
    @RequestMapping("/delCabinet.do")
    @ResponseBody
    public Map delCabinet(String id){
        return itInfoDataService.delCabinet(id);
    }


    /**
     *
     *配线架
     *
     *
     *
     */
    @RequestMapping("/selectDistribution.do")
    @ResponseBody
    public Map selectDistribution(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectDistribution(page, limit, param,uname);
    }
    @RequestMapping("/exlDistribution.do")
    @ResponseBody
    public List<Map<String,Object>> exlDistribution(String param,String uname){

        return itInfoDataService.exlDistribution(param,uname);
    }
    @RequestMapping("/addDistribution.do")
    @ResponseBody
    public Map addDistribution(String disName,String crName){

        return itInfoDataService.addDistribution(disName, crName);
    }
    @RequestMapping("/updetDistribution.do")
    @ResponseBody
    public Map updetDistribution(String disName,String upName,String id){

        return itInfoDataService.updetDistribution(disName, upName, id);
    }
    @RequestMapping("/delDistribution.do")
    @ResponseBody
    public Map delDistribution(String id){

        return itInfoDataService.delDistribution(id);
    }



    /**
     *
     *光纤、网线信息
     *
     *
     *
     */
    @RequestMapping("/selectCable.do")
    @ResponseBody
    public Map selectCable(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectCable(page, limit, param,uname);
    }
    @RequestMapping("/exlCable.do")
    @ResponseBody
    public List<Map<String,Object>> exlCable(String param,String uname){
        return itInfoDataService.exlCable(param,uname);
    }
    @RequestMapping("/addCable.do")
    @ResponseBody
    public Map addCable(ItAssetCable itAssetCable){
        return itInfoDataService.addCable(itAssetCable);
    }
    @RequestMapping("/updetCable.do")
    @ResponseBody
    public Map updetCable(ItAssetCable itAssetCable){

        return itInfoDataService.updetCable(itAssetCable);
    }
    @RequestMapping("/delCable.do")
    @ResponseBody
    public Map delCable(String id){

        return itInfoDataService.delCable(id);
    }

    /**
     *
     *面板信息
     *
     *
     *
     */
    @RequestMapping("/selectPanel.do")
    @ResponseBody
    public Map selectPanel(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectPanel(page, limit, param,uname);
    }
    @RequestMapping("/exlPanel.do")
    @ResponseBody
    public List<Map<String,Object>> exlPanel(String param,String uname){
        return itInfoDataService.exlPanel(param,uname);
    }
    @RequestMapping("/addPanel.do")
    @ResponseBody
    public Map addPanel(ItAssetPanel itAssetPanel){

        return itInfoDataService.addPanel(itAssetPanel);
    }
    @RequestMapping("/updetPanel.do")
    @ResponseBody
    public Map updetPanel(ItAssetPanel itAssetPanel){

        return itInfoDataService.updetPanel(itAssetPanel);
    }
    @RequestMapping("/delPanel.do")
    @ResponseBody
    public Map delPanel(String id){

        return itInfoDataService.delPanel(id);
    }

    /**
     *
     *网络设备信息
     *
     *
     *
     */
    @RequestMapping("/selectNetequipment.do")
    @ResponseBody
    public Map selectNetequipment(Integer page, Integer limit, String param,String uname){
        return itInfoDataService.selectNetequipment(page, limit, param,uname);
    }
    @RequestMapping("/exlNetequipment.do")
    @ResponseBody
    public List<Map<String,Object>> exlNetequipment(String param,String uname){
        return itInfoDataService.exlNetequipment(param,uname);
    }
    @RequestMapping("/addNetequipment.do")
    @ResponseBody
    public Map addNetequipment(ItAssetNetequipment tan){

        return itInfoDataService.addNetequipment(tan);
    }
    @RequestMapping("/updetNetequipment.do")
    @ResponseBody
    public Map updetNetequipment(ItAssetNetequipment tan){
        return itInfoDataService.updetNetequipment(tan);
    }
    @RequestMapping("/delNetequipment.do")
    @ResponseBody
    public Map delNetequipment(String id){
        return itInfoDataService.delNetequipment(id);
    }
}
