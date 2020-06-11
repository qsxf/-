package com.spring.ItInfoData;

import com.spring.bean.ItAssetCable;
import com.spring.bean.ItAssetNetequipment;
import com.spring.bean.ItAssetPanel;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ItInfoDataDao {
    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;


    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
    /**
     *
     *机房信息
     *
     *
     *
     */
    public List<Map<String,Object>> selectItRoom(Integer page, Integer limit, String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from (SELECT tt.*,ROWNUM ROOMS FROM( SELECT r.* FROM IT_ASSET_ITROOM r WHERE DELETE_TYPE='0'   ");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (ITROOM_NAME LIKE ? OR LOCATION LIKE ? or COMPANY LIKE ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.ROOMS >?  ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map itRoomCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS FROM IT_ASSET_ITROOM r WHERE DELETE_TYPE=?   ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (ITROOM_NAME LIKE ? OR LOCATION LIKE ?  or COMPANY LIKE ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlItRoom(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * FROM IT_ASSET_ITROOM r WHERE DELETE_TYPE=?  ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (ITROOM_NAME LIKE ? OR LOCATION LIKE ? or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY CREATE_TIME DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }




    public int addItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String crName){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" INSERT INTO IT_ASSET_ITROOM(ITROOM_NAME,LOCATION,FLOOR,ROOMNUM,AREA,CABINETNUM,CREATE_NAME,COMPANY)VALUES(?,?,?,?,?,?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");

        listpar.add(roomName);
        listpar.add(location);
        listpar.add(floor);
        listpar.add(roomnum);
        listpar.add(area);
        listpar.add(cabinetnum);
        listpar.add(crName);
        listpar.add(crName);
        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }


    public int updetItRoom(String roomName,String location,String floor,String roomnum,String area,String cabinetnum,String upName,String id){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  UPDATE IT_ASSET_ITROOM SET ITROOM_NAME=?,LOCATION=?,FLOOR=?,ROOMNUM=?,AREA=?,CABINETNUM=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE ID=? ");

        listpar.add(roomName);
        listpar.add(location);
        listpar.add(floor);
        listpar.add(roomnum);
        listpar.add(area);
        listpar.add(cabinetnum);
        listpar.add(upName);
        listpar.add(id);

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }
    public int delItRoom(String id){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" UPDATE IT_ASSET_ITROOM SET  DELETE_TYPE='1' WHERE ID=?  ");



        return itsmJdbcTemplate.update(sql.toString(),id);
    }

/**
 *
 *机柜信息
 *
 *
 *
 */



public List<Map<String,Object>> selectCabinet(Integer page, Integer limit, String param,String uname,List user){
    StringBuilder sql = new StringBuilder();
    List listpar=new ArrayList();
    sql.append("   SELECT * from (SELECT tt.*,ROWNUM ROMS FROM( SELECT r.* FROM IT_ASSET_CABINET r WHERE DELETE_TYPE='0'   ");
    if (user.isEmpty()){
        sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)   ");
        listpar.add(uname);
    }
    if(!StringUtils.isBlank(param)){
        sql.append(" AND (CABINET_NAME LIKE ?  or COMPANY LIKE ?)");
        listpar.add("%"+param+"%");
        listpar.add("%"+param+"%");
    }

    sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.ROMS >?  ");
    listpar.add(page*limit);
    listpar.add((page-1)*limit);

    return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
}

    public Map cabinetCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT COUNT(*) COUNTS FROM IT_ASSET_CABINET r WHERE DELETE_TYPE=?   ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (CABINET_NAME LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlCabinet(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * FROM IT_ASSET_CABINET r WHERE DELETE_TYPE=? ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (CABINET_NAME LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY CREATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }




    public int addCabinet(String carName,String carUhihg,String crName){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" INSERT INTO IT_ASSET_CABINET (CABINET_NAME,CABINET_UHIHG,CREATE_NAME,COMPANY)VALUES(?,?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");

        listpar.add(carName);
        listpar.add(carUhihg);
        listpar.add(crName);
        listpar.add(crName);
        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }


    public int updetCabinet(String carName,String carUhihg,String upName,String id){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  UPDATE IT_ASSET_CABINET SET CABINET_NAME=?,CABINET_UHIHG=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE ID=? ");

        listpar.add(carName);
        listpar.add(carUhihg);
        listpar.add(upName);
        listpar.add(id);

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }
    public int delCabinet(String id){

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE IT_ASSET_CABINET SET DELETE_TYPE='1' WHERE ID=?  ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }



    /**
     *
     *配线架
     *
     *
     *
     */

    public List<Map<String,Object>> selectDistribution(Integer page, Integer limit, String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT * FROM (SELECT tt.*,ROWNUM ROMS FROM( SELECT r.* FROM IT_ASSET_DISTRIBUTION r WHERE DELETE_TYPE='0'    ");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (DISTRIBUTION_NAME LIKE ?  or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?) dd WHERE  dd.ROMS >? ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map DistributionCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT COUNT(*) COUNTS FROM IT_ASSET_DISTRIBUTION r WHERE DELETE_TYPE=?   ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (DISTRIBUTION_NAME LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlDistribution(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * FROM IT_ASSET_DISTRIBUTION r WHERE DELETE_TYPE=?   ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (DISTRIBUTION_NAME LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        sql.append("  ORDER BY CREATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int addDistribution(String disName,String crName){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  INSERT INTO IT_ASSET_DISTRIBUTION (DISTRIBUTION_NAME,CREATE_NAME,COMPANY) VALUES(?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");

        listpar.add(disName);
        listpar.add(crName);
        listpar.add(crName);
        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int updetDistribution(String disName,String upName,String id){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  UPDATE IT_ASSET_DISTRIBUTION SET DISTRIBUTION_NAME=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE ID=? ");

        listpar.add(disName);
        listpar.add(upName);
        listpar.add(id);

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int delDistribution(String id){

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE IT_ASSET_DISTRIBUTION SET DELETE_TYPE='1' WHERE ID=?  ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }



    /**
     *
     *光纤、网线信息
     *
     *
     *
     */
    public List<Map<String,Object>> selectCable(Integer page, Integer limit, String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT * from (SELECT tt.*,ROWNUM ROMS FROM( SELECT r.* FROM IT_ASSET_CABLE r WHERE DELETE_TYPE='0'  ");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (CABLE_NAME LIKE ?  or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?)dd WHERE dd.ROMS >? ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map cableCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT COUNT(*) COUNTS FROM IT_ASSET_CABLE r WHERE DELETE_TYPE=?   ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (CABLE_NAME LIKE ?  or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlCable(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * FROM IT_ASSET_CABLE r WHERE DELETE_TYPE=? ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (CABLE_NAME LIKE ?  or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY CREATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int addCable(ItAssetCable itAssetCable){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  INSERT INTO  IT_ASSET_CABLE(CABLE_NAME,EQUIPMENT_PORT,RACK_PORT,PANEL_ID,INTERFACE_CLASS,LENGTH,CREATE_NAME,COMPANY)VALUES(?,?,?,?,?,?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");

        listpar.add(itAssetCable.getCableName());
        listpar.add(itAssetCable.getEquipmentPort());
        listpar.add(itAssetCable.getRackPort());
        listpar.add(itAssetCable.getPanelId());
        listpar.add(itAssetCable.getInterfaceClass());
        listpar.add(itAssetCable.getLength());
        listpar.add(itAssetCable.getCreateName());
        listpar.add(itAssetCable.getCreateName());
        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int updetCable(ItAssetCable itAssetCable){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  UPDATE IT_ASSET_CABLE SET CABLE_NAME=?,EQUIPMENT_PORT=?,RACK_PORT=?,PANEL_ID=?,INTERFACE_CLASS=?,LENGTH=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=?   WHERE id=?  ");

        listpar.add(itAssetCable.getCableName());
        listpar.add(itAssetCable.getEquipmentPort());
        listpar.add(itAssetCable.getRackPort());
        listpar.add(itAssetCable.getPanelId());
        listpar.add(itAssetCable.getInterfaceClass());
        listpar.add(itAssetCable.getLength());
        listpar.add(itAssetCable.getUpdateName());
        listpar.add(itAssetCable.getId());

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int delCable(String id){

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE IT_ASSET_CABLE SET DELETE_TYPE='1' WHERE ID=?  ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    /**
     *
     *面板信息
     *
     *
     *
     */

    public List<Map<String,Object>> selectPanel(Integer page, Integer limit, String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * from (SELECT tt.*,ROWNUM ROMS FROM( SELECT r.* FROM IT_ASSET_PANEL r WHERE DELETE_TYPE='0'   ");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (PANEL_ID LIKE ?  or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.ROMS >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map PanelCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT COUNT(*) COUNTS FROM IT_ASSET_PANEL r WHERE DELETE_TYPE= ?   ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (PANEL_ID LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlPanel(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * FROM IT_ASSET_PANEL r WHERE DELETE_TYPE= ? ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (PANEL_ID LIKE ?  or COMPANY LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY CREATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int addPanel(ItAssetPanel itAssetPanel){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  INSERT INTO IT_ASSET_PANEL (PANEL_ID,UNTI_ID,LOCATION,FLOOR,ROOM,TYPE,CREATE_NAME,COMPANY)VALUES(?,?,?,?,?,?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");

        listpar.add(itAssetPanel.getPanelId());
        listpar.add(itAssetPanel.getUntiId());
        listpar.add(itAssetPanel.getLocation());
        listpar.add(itAssetPanel.getFloor());
        listpar.add(itAssetPanel.getRoom());
        listpar.add(itAssetPanel.getType());
        listpar.add(itAssetPanel.getCreateName());
        listpar.add(itAssetPanel.getCreateName());

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int updetPanel(ItAssetPanel itAssetPanel){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" UPDATE IT_ASSET_PANEL SET PANEL_ID=?,UNTI_ID=?,LOCATION=?,FLOOR=?,ROOM=?,TYPE=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE id=?   ");

        listpar.add(itAssetPanel.getPanelId());
        listpar.add(itAssetPanel.getUntiId());
        listpar.add(itAssetPanel.getLocation());
        listpar.add(itAssetPanel.getFloor());
        listpar.add(itAssetPanel.getRoom());
        listpar.add(itAssetPanel.getType());
        listpar.add(itAssetPanel.getUpdateName());
        listpar.add(itAssetPanel.getId());

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int delPanel(String id){

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE IT_ASSET_PANEL SET DELETE_TYPE='1' WHERE ID=?  ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    /**
     *
     *网络设备信息
     *
     *
     *
     */
    public List<Map<String,Object>> selectNetequipment(Integer page, Integer limit, String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * FROM (SELECT tt.*,ROWNUM ROMS FROM( SELECT r.* FROM IT_ASSET_NETEQUIPMENT r WHERE DELETE_TYPE='0'  ");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (EUT_NAME LIKE ? OR EUT_ID LIKE ? or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        sql.append("  ORDER BY UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.ROMS >?  ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map netCount(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT COUNT(*) COUNTS FROM IT_ASSET_NETEQUIPMENT r WHERE DELETE_TYPE=?  ");
        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (EUT_NAME LIKE ? OR EUT_ID LIKE ? or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> exlNetequipment(String param,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT * FROM IT_ASSET_NETEQUIPMENT r WHERE DELETE_TYPE=?  ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" AND (EUT_NAME LIKE ? OR EUT_ID LIKE ? or COMPANY LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        sql.append("  ORDER BY CREATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int addNetequipment(ItAssetNetequipment tan){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" INSERT INTO IT_ASSET_NETEQUIPMENT (EUT_NAME,EUT_ID,EUP_CLASS,STORE_NAME,EUP_MODEL,DRAM_SIZE,FLASH_SIZE,BUY_TIME,  EXITINSUR_TIME,CABINET,U_BIT,STATE_DESCRIPTION,ADMIN,IP,REMARK,CREATE_NAME,COMPANY)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?))  ");

        listpar.add(tan.getEutName());
        listpar.add(tan.getEutId());
        listpar.add(tan.getEupClass());
        listpar.add(tan.getStoreName());
        listpar.add(tan.getEupModel());
        listpar.add(tan.getDramSize());
        listpar.add(tan.getFlashSize());
        listpar.add(tan.getBuyTime());
        listpar.add(tan.getExitinsurTime());
        listpar.add(tan.getCabinet());
        listpar.add(tan.getUBit());
        listpar.add(tan.getStateDescription());
        listpar.add(tan.getAdmin());
        listpar.add(tan.getIp());
        listpar.add(tan.getRemark());
        listpar.add(tan.getCreateName());
        listpar.add(tan.getCreateName());
        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int updetNetequipment(ItAssetNetequipment tan){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" UPDATE IT_ASSET_NETEQUIPMENT SET EUT_NAME=?,EUT_ID=?,EUP_CLASS=?,STORE_NAME=?,EUP_MODEL=?,DRAM_SIZE=?,FLASH_SIZE=?,BUY_TIME=?,  EXITINSUR_TIME=?,CABINET=?,U_BIT=?,STATE_DESCRIPTION=?,ADMIN=?,IP=?,REMARK=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE id=? ");

        listpar.add(tan.getEutName());
        listpar.add(tan.getEutId());
        listpar.add(tan.getEupClass());
        listpar.add(tan.getStoreName());
        listpar.add(tan.getEupModel());
        listpar.add(tan.getDramSize());
        listpar.add(tan.getFlashSize());
        listpar.add(tan.getBuyTime());
        listpar.add(tan.getExitinsurTime());
        listpar.add(tan.getCabinet());
        listpar.add(tan.getUBit());
        listpar.add(tan.getStateDescription());
        listpar.add(tan.getAdmin());
        listpar.add(tan.getIp());
        listpar.add(tan.getRemark());

        listpar.add(tan.getUpdateName());
        listpar.add(tan.getId());

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int delNetequipment(String id){

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE IT_ASSET_NETEQUIPMENT SET DELETE_TYPE='1' WHERE ID=?  ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }

}
