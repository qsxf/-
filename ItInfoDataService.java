package com.spring.ItInfoData;

import com.spring.bean.ItAssetCable;
import com.spring.bean.ItAssetNetequipment;
import com.spring.bean.ItAssetPanel;
import com.spring.repairAsset.RepairAssetDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItInfoDataService {
    @Resource
    private ItInfoDataDao itInfoDataDao;


    /**
     *
     *机房信息
     *
     *
     *
     */
    public Map selectItRoom(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);
        List<Map<String, Object>> list = itInfoDataDao.selectItRoom(page, limit, param,uname,user);
        Map map = itInfoDataDao.itRoomCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;

    }

    public List<Map<String,Object>> exlItRoom(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);

        return itInfoDataDao.exlItRoom(param,uname,user);
    }

    public Map addItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String crName){
        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addItRoom(roomName, location, floor, roomnum, area, cabinetnum, crName);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    public Map updetItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String upName,String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetItRoom(roomName, location, floor, roomnum, area, cabinetnum, upName, id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delItRoom(String id){
        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delItRoom(ids[i]);
            }
            //itInfoDataDao.delItRoom(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;

    }

    /**
     *
     *机柜信息
     *
     *
     *
     */




    public Map selectCabinet(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);

        List<Map<String, Object>> list = itInfoDataDao.selectCabinet(page, limit, param,uname,user);
        Map map = itInfoDataDao.cabinetCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlCabinet(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);
        return itInfoDataDao.exlCabinet(param,uname,user);
    }

    public Map addCabinet(String carName,String carUhihg,String crName){

        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addCabinet(carName, carUhihg, crName);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map updetCabinet(String carName,String carUhihg,String upName,String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetCabinet(carName, carUhihg, upName, id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delCabinet(String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delCabinet(ids[i]);
            }
            //itInfoDataDao.delCabinet(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }





    /**
     *
     *配线架
     *
     *
     *
     */

    public Map selectDistribution(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);

        List<Map<String, Object>> list = itInfoDataDao.selectDistribution(page, limit, param,uname,user);
        Map map = itInfoDataDao.DistributionCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlDistribution(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);

        return itInfoDataDao.exlDistribution(param,uname,user);
    }

    public Map addDistribution(String disName,String crName){

        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addDistribution(disName, crName);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map updetDistribution(String disName,String upName,String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetDistribution(disName, upName, id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delDistribution(String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delDistribution(ids[i]);
            }
            //itInfoDataDao.delDistribution(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }



    /**
     *
     *光纤、网线信息
     *
     *
     *
     */
    public Map selectCable(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);

        List<Map<String, Object>> list = itInfoDataDao.selectCable(page, limit, param,uname,user);
        Map map = itInfoDataDao.cableCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlCable(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);
        return itInfoDataDao.exlCable(param,uname,user);
    }

    public Map addCable(ItAssetCable itAssetCable){

        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addCable(itAssetCable);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map updetCable(ItAssetCable itAssetCable){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetCable(itAssetCable);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delCable(String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delCable(ids[i]);
            }
            //itInfoDataDao.delCable(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     *
     *面板信息
     *
     *
     *
     */

    public Map selectPanel(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);

        List<Map<String, Object>> list = itInfoDataDao.selectPanel(page, limit, param,uname,user);
        Map map = itInfoDataDao.PanelCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlPanel(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);
        return itInfoDataDao.exlPanel(param,uname,user);
    }

    public Map addPanel(ItAssetPanel itAssetPanel){

        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addPanel(itAssetPanel);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map updetPanel(ItAssetPanel itAssetPanel){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetPanel(itAssetPanel);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delPanel(String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delPanel(ids[i]);
            }
            //itInfoDataDao.delPanel(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     *
     *网络设备信息
     *
     *
     *
     */
    public Map selectNetequipment(Integer page, Integer limit, String param,String uname){
        Map redMap=new HashMap();
        List user = itInfoDataDao.selectUser(uname);

        List<Map<String, Object>> list = itInfoDataDao.selectNetequipment(page, limit, param,uname,user);
        Map map = itInfoDataDao.netCount(param,uname,user);

        redMap.put("code", 0);
        redMap.put("msg", "");
        redMap.put("count", map.get("COUNTS"));
        redMap.put("data", list);
        return redMap;
    }

    public List<Map<String,Object>> exlNetequipment(String param,String uname){
        List user = itInfoDataDao.selectUser(uname);
        return itInfoDataDao.exlNetequipment(param,uname,user);
    }

    public Map addNetequipment(ItAssetNetequipment tan){

        Map map = new HashMap();
        String code = "0";
        String msg = "新增成功！";


        try {
            itInfoDataDao.addNetequipment(tan);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "新增失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map updetNetequipment(ItAssetNetequipment tan){

        Map map = new HashMap();
        String code = "0";
        String msg = "修改成功！";


        try {
            itInfoDataDao.updetNetequipment(tan);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "修改失败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    public Map delNetequipment(String id){

        Map map = new HashMap();
        String code = "0";
        String msg = "删除成功！";


        try {
            String[] ids= id.split(",");
            for (int i=0 ;i<ids.length;i++){
                itInfoDataDao.delNetequipment(ids[i]);
            }
            //itInfoDataDao.delNetequipment(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg = "删除败！";
        }

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

}
