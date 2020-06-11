package com.spring.repairAsset;

import com.spring.bean.ItAssetDepreciation;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RepairAssetDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;

    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selectRepairAsset(Integer page, Integer limit,String param,String bzDate,String uname,List user,String param1,String param2,String param3,String param4,String param5,String param6){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from (SELECT tt.*,rownum rowno FROM( SELECT r.*,s.ASSETS_NAME,s.ASSETS_COMPANY FROM IT_ASSET_REPAIRASSET r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE='0'  and s.del_log=0   " );
        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.REPAIR_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?  or s.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REPORTDATE AND r.REPORTDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REPORTDATE AND r.REPORTDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ASSETS_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param3)){
            sql.append("  and  (  ");
            String[] data= param3.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  s.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param4)){
            sql.append("  and  (  ");
            String[] data= param4.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.REPAIR_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REPAIR_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param5)){
            sql.append("  and  (  ");
            String[] data= param5.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.REPORTER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REPORTER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6)){
            sql.append("  and  (  ");
            String[] data= param6.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  s.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        sql.append("   ORDER BY r.UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countRepairAsset(String param,String bzDate,String uname,List user,String param1,String param2,String param3,String param4,String param5,String param6){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS   FROM IT_ASSET_REPAIRASSET r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE=0  and s.del_log=0  " );
        if (user.isEmpty()){
            sql.append("AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.REPAIR_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?  or s.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REPORTDATE AND r.REPORTDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REPORTDATE AND r.REPORTDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ASSETS_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param3)){
            sql.append("  and  (  ");
            String[] data= param3.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  s.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param4)){
            sql.append("  and  (  ");
            String[] data= param4.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.REPAIR_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REPAIR_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param5)){
            sql.append("  and  (  ");
            String[] data= param5.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.REPORTER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REPORTER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6)){
            sql.append("  and  (  ");
            String[] data= param6.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  s.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }



    public List<Map<String,Object>> exlRepairAsset(String param,String bzDate,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT r.*,s.ASSETS_NAME,s.ASSETS_COMPANY  FROM IT_ASSET_REPAIRASSET r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE=?  and s.del_log=0   ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append("AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.REPAIR_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ? or s.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REPORTDATE AND r.REPORTDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }

        sql.append(" ORDER BY r.CREATE_TIME DESC ");

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }





    public int upRepairAsset(String bxcode,String bxdate,String bzName,String bzType,String gzLve,String gznr,String wcdate,String uname,String wxje,String id){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_REPAIRASSET SET REPAIR_ID=?,REPORTDATE=?,REPORTER=?,FAULTTYPE=?,FAULTCLASS=?,FAULTDESC=?,FINISHTIME=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=?,REPAIR_PRICE=? WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),bxcode,bxdate,bzName,bzType,gzLve,gznr,wcdate,uname,wxje,id);
    }
    public int upAssetStatus(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_FIXASSET SET STATUS='03' WHERE ASSETS_ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    public int delRepairAsset(String bxcode){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_REPAIRASSET set DELETE_TYPE='1' WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),bxcode);
    }


    /**
     *
     *
     *资产折旧
     *
     *
     *
     */


    public List<Map<String,Object>> selectDepreciation(Integer page, Integer limit,String param){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("   SELECT * FROM( SELECT r.*,s.ASSETS_NAME,s.PRICE,rownum rowno FROM IT_ASSET_DEPRECIATION r  LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE='0'     ");

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");

        }
        sql.append("   and rownum <=? ORDER BY r.UPDATE_DATE DESC )tt where tt.rowno >? ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countDepreciation(String param){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT COUNT(*) COUNTS FROM IT_ASSET_DEPRECIATION r  LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE=?   ");

        listpar.add("0");
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");

        }
        sql.append("  ORDER BY r.UPDATE_DATE DESC ");


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }
    public List<Map<String,Object>> exlDepreciation(String param){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT r.*,s.ASSETS_NAME,s.PRICE   FROM IT_ASSET_DEPRECIATION r  LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DELETE_TYPE=?   ");

        listpar.add("0");
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");

        }
        sql.append("  ORDER BY r.UPDATE_DATE DESC ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int updetDepreciation(ItAssetDepreciation iad) {
        StringBuilder sql = new StringBuilder();
        List listpar = new ArrayList();

        sql.append("    UPDATE IT_ASSET_DEPRECIATION SET DEPREDATE=?,DEPREMETHOD=?,SERVICE_LIFE=?,NETRATE=?,DEPREMONTHS=?,INITIALNETWORTH=?,  DEPRENOWMONTH=?, DEPREALL=?,NETWORTH=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=?,DEPRECIATION_ID=? WHERE ID=? ");

        listpar.add(iad.getDepredate());
        listpar.add(iad.getDepremethod());
        listpar.add(iad.getServiceLife());
        listpar.add(iad.getNetrate());
        listpar.add(iad.getDepremonths());
        listpar.add(iad.getInitialnetworth());
        listpar.add(iad.getDeprenowmonth());
        listpar.add(iad.getDepreall());
        listpar.add(iad.getNetworth());
        listpar.add(iad.getUpdateName());
        listpar.add(iad.getDepreciationId());
        listpar.add(iad.getId());

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }

    public int delDepreciation(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("   UPDATE IT_ASSET_DEPRECIATION SET DELETE_TYPE='1' WHERE ID=? ");


        return itsmJdbcTemplate.update(sql.toString(),id);
    }


/**
 *
 *
 *报废清单
 *
 */


public List<Map<String,Object>> selectDumpAsset(Integer page, Integer limit,String param,String datas,String uname,List user,String param2,
                                                String param3,String param4,String param5,String param6,String param7,String param8,
                                                String param9,String param10){
    StringBuilder sql = new StringBuilder();
    List listpar=new ArrayList();
    sql.append("   SELECT * from (SELECT tt.*,rownum rowno FROM( SELECT r.*,s.ASSETS_NAME,ASSETS_COMPANY,s.ASSETCLASS_1 ,s.ASSETCLASS_2,s.BRAND ,s.TYPE_NAME  FROM IT_ASSET_DUMPASSET r   LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID  WHERE  r.DELETE_TYPE='0' and s.status = 04 and s.del_log = 0   ");
    if (user.isEmpty()){
        sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)   ");
        listpar.add(uname);
    }
    if(!StringUtils.isBlank(param)){
        sql.append(" and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?  or s.ASSETS_COMPANY like ?)  ");
        listpar.add("%"+param+"%");
        listpar.add("%"+param+"%");
        listpar.add("%"+param+"%");
    }

    if(!StringUtils.isBlank(datas)){
       String[] data= datas.split(" - ");
       String data1=data[0];
        String data2=data[1];

        sql.append(" AND ? <=r.DUMPDATE AND r.DUMPDATE<=?  ");

        listpar.add(data1);
        listpar.add(data2);
    }
    if(!StringUtils.isBlank(param2)){
        sql.append("  and  (  ");
        String[] data= param2.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   r.ASSETS_ID LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  r.ASSETS_ID LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param3)){
        sql.append("  and  (  ");
        String[] data= param3.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("  s.ASSETS_NAME LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.ASSETS_NAME LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }
        }
        sql.append("  ) ");
    }
    if(!StringUtils.isBlank(param4)){
        sql.append("  and  (  ");
        String[] data= param4.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   s.ASSETCLASS_1 LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.ASSETCLASS_1 LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param5)){
        sql.append("  and  (  ");
        String[] data= param5.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   s.ASSETCLASS_2 LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.ASSETCLASS_2 LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param6)){
        sql.append("  and  (  ");
        String[] data= param6.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   s.BRAND LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.BRAND LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param7)){
        sql.append("  and  (  ");
        String[] data= param7.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   s.TYPE_NAME LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.TYPE_NAME LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param8)){
        String[] data= param8.split(" - ");
        String data1=data[0];
        String data2=data[1];
        sql.append("  AND ? <=r.DUMPDATE AND r.DUMPDATE<=? ");
        listpar.add(data1);
        listpar.add(data2);

    }
    if(!StringUtils.isBlank(param9)){
        sql.append("  and  (  ");
        String[] data= param9.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   r.REASON LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  r.REASON LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");

    }
    if(!StringUtils.isBlank(param10)){
        sql.append("  and  (  ");
        String[] data= param10.split("，");
        for (int i = 0; i < data.length; i++){
            if (i == 0){
                sql.append("   s.ASSETS_COMPANY LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }else {
                sql.append(" or  s.ASSETS_COMPANY LIKE ?  ");
                listpar.add("%"+data[i]+"%");
            }

        }
        sql.append("  ) ");
    }
    sql.append("    ORDER BY r.UPDATE_DATE DESC )tt  where rownum <=?) dd WHERE dd.rowno >?    ");
    listpar.add(page*limit);
    listpar.add((page-1)*limit);

    return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
}



    public Map countDumpAsset(String param,String datas,String uname,List user,String param2,
                              String param3,String param4,String param5,String param6,String param7,String param8,
                              String param9,String param10){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT count(*) COUNTS FROM IT_ASSET_DUMPASSET r  LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID  WHERE  r.DELETE_TYPE='0' and s.status = 04 and s.del_log = 0   ");

        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }


        if(!StringUtils.isBlank(param)){
            sql.append(" and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?  or s.ASSETS_COMPANY like ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        if(!StringUtils.isBlank(datas)){
            String[] data= datas.split(" - ");
            String data1=data[0];
            String data2=data[1];

            sql.append(" AND ? <=r.DUMPDATE AND r.DUMPDATE<=?  ");

            listpar.add(data1);
            listpar.add(data2);
        }

        sql.append("   ORDER BY r.UPDATE_DATE DESC   ");


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }


    public List<Map<String,Object>> exlDumpAsset(String param,String datas,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("    SELECT r.*,s.ASSETS_NAME,s.ASSETCLASS_1 ,s.ASSETCLASS_2,s.BRAND ,s.TYPE_NAME,s.ASSETS_COMPANY FROM IT_ASSET_DUMPASSET r  LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID  WHERE  r.DELETE_TYPE=? and s.status='04' and s.del_log=0 ");

        listpar.add("0");
        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append(" and (r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ?  or s.ASSETS_COMPANY like ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }

        if(!StringUtils.isBlank(datas)){
            String[] data= datas.split(" - ");
            String data1=data[0];
            String data2=data[1];

            sql.append(" AND ? <=r.DUMPDATE AND r.DUMPDATE<=?  ");

            listpar.add(data1);
            listpar.add(data2);
        }

        sql.append("  ORDER BY r.CREATE_TIME DESC   ");


        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int upDumpAsset(String reason,String dumpDate,String upName,String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("   UPDATE IT_ASSET_DUMPASSET SET REASON=?,DUMPDATE=?,UPDATE_NAME=?,UPDATE_DATE=SYSDATE WHERE ID=?     ");

        return itsmJdbcTemplate.update(sql.toString(),reason,dumpDate,upName,id);
    }

    public int delDumpAsset(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE IT_ASSET_DUMPASSET SET DELETE_TYPE='1' WHERE ID=?    ");

        return itsmJdbcTemplate.update(sql.toString(),id);
    }

}
