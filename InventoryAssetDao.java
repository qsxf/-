package com.spring.inventoryAsset;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryAssetDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;

    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
    public List<Map<String,Object>> selectInventoryAsset(Integer page, Integer limit,String param,String bzDate,String uname,List user,String param1,String param2,
                                                         String param3,String param4,String param5,String param6,String param7,String param8,
                                                         String param9p,String param9l,String param10p,String param10l,String param11){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from (SELECT tt.*,ROWNUM rowno FROM(SELECT r.* FROM IT_ASSET_INVENTORYASSET r WHERE  DELETE_TYPE= 0    ");
        if (user.isEmpty()){
            sql.append("AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.INVENTORY_ID LIKE ?  OR r.INVENTORYER LIKE ? or r.company like ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANDATE AND r.PLANDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            sql.append("  and  (  ");
            String[] data= param1.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.INVENTORY_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.INVENTORY_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ATTRIBUTION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ATTRIBUTION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }

        if(!StringUtils.isBlank(param3)){
            String[] data= param3.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANDATE AND r.PLANDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param4)){
            String[] data= param4.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANFINDATE AND r.PLANFINDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param5)){
            String[] data= param5.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REALITYDATE AND r.REALITYDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param6)){
            String[] data= param6.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REALITYFINDATE AND r.REALITYFINDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param7)){
            sql.append("  and  (  ");
            String[] data= param7.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.INVENTORYER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.INVENTORYER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param8)){
            sql.append("  and  (  ");
            String[] data= param8.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.STAGE LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.STAGE LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param9p)||!StringUtils.isBlank(param9l)){
            if (!StringUtils.isBlank(param9p)&&StringUtils.isBlank(param9l)){
                sql.append("  AND ? <=r.DISKCOUNT ");
                listpar.add(param9p);
            }
            if (StringUtils.isBlank(param9p)&&!StringUtils.isBlank(param9l)){
                sql.append("  AND r.DISKCOUNT<=? ");
                listpar.add(param9l);
            }
            if (!StringUtils.isBlank(param9p)&&!StringUtils.isBlank(param9l)){
                sql.append("  AND ? <=r.DISKCOUNT AND r.DISKCOUNT<=? ");
                listpar.add(param9p);
                listpar.add(param9l);
            }
        }
        if(!StringUtils.isBlank(param10p)||!StringUtils.isBlank(param10l)){
            if (!StringUtils.isBlank(param10p)&&StringUtils.isBlank(param10l)){
                sql.append("  AND ? <=r.INVENTORYNUM ");
                listpar.add(param10p);
            }
            if (StringUtils.isBlank(param10p)&&!StringUtils.isBlank(param10l)){
                sql.append("  AND r.INVENTORYNUM<=? ");
                listpar.add(param10l);
            }
            if (!StringUtils.isBlank(param10p)&&!StringUtils.isBlank(param10l)){
                sql.append("  AND ? <=r.INVENTORYNUM AND r.INVENTORYNUM<=? ");
                listpar.add(param10p);
                listpar.add(param10l);
            }
        }

        if(!StringUtils.isBlank(param11)){
            sql.append("  and  (  ");
            String[] data= param11.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        sql.append(" ORDER BY UPDATE_DATE DESC )tt where rownum <=? ) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countInventoryAsset(String param,String bzDate,String uname,List user,String param1,String param2,
                                   String param3,String param4,String param5,String param6,String param7,String param8,
                                   String param9p,String param9l,String param10p,String param10l,String param11){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS   FROM IT_ASSET_INVENTORYASSET r WHERE  DELETE_TYPE=0   ");
        if (user.isEmpty()){
            sql.append("AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.INVENTORY_ID LIKE ?  OR r.INVENTORYER LIKE ? or r.company like ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANDATE AND r.PLANDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            sql.append("  and  (  ");
            String[] data= param1.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.INVENTORY_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.INVENTORY_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ATTRIBUTION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ATTRIBUTION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }

        if(!StringUtils.isBlank(param3)){
            String[] data= param3.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANDATE AND r.PLANDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param4)){
            String[] data= param4.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANFINDATE AND r.PLANFINDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param5)){
            String[] data= param5.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REALITYDATE AND r.REALITYDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param6)){
            String[] data= param6.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.REALITYFINDATE AND r.REALITYFINDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param7)){
            sql.append("  and  (  ");
            String[] data= param7.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.INVENTORYER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.INVENTORYER LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param8)){
            sql.append("  and  (  ");
            String[] data= param8.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.STAGE LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.STAGE LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");

        }
        if(!StringUtils.isBlank(param9p)||!StringUtils.isBlank(param9l)){
            if (!StringUtils.isBlank(param9p)&&StringUtils.isBlank(param9l)){
                sql.append("  AND ? <=r.DISKCOUNT ");
                listpar.add(param9p);
            }
            if (StringUtils.isBlank(param9p)&&!StringUtils.isBlank(param9l)){
                sql.append("  AND r.DISKCOUNT<=? ");
                listpar.add(param9l);
            }
            if (!StringUtils.isBlank(param9p)&&!StringUtils.isBlank(param9l)){
                sql.append("  AND ? <=r.DISKCOUNT AND r.DISKCOUNT<=? ");
                listpar.add(param9p);
                listpar.add(param9l);
            }
        }
        if(!StringUtils.isBlank(param10p)||!StringUtils.isBlank(param10l)){
            if (!StringUtils.isBlank(param10p)&&StringUtils.isBlank(param10l)){
                sql.append("  AND ? <=r.INVENTORYNUM ");
                listpar.add(param10p);
            }
            if (StringUtils.isBlank(param10p)&&!StringUtils.isBlank(param10l)){
                sql.append("  AND r.INVENTORYNUM<=? ");
                listpar.add(param10l);
            }
            if (!StringUtils.isBlank(param10p)&&!StringUtils.isBlank(param10l)){
                sql.append("  AND ? <=r.INVENTORYNUM AND r.INVENTORYNUM<=? ");
                listpar.add(param10p);
                listpar.add(param10l);
            }
        }

        if(!StringUtils.isBlank(param11)){
            sql.append("  and  (  ");
            String[] data= param11.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }



    public List<Map<String,Object>> exlInventoryAsset(String param,String bzDate,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT r.*,ROWNUM rowno FROM IT_ASSET_INVENTORYASSET r WHERE  DELETE_TYPE= 0   ");
        if (user.isEmpty()){
            sql.append("AND r.COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.INVENTORY_ID LIKE ?  OR r.INVENTORYER LIKE ? or r.company like ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PLANDATE AND r.PLANDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }





    public int upInventoryAsset(String sjkssj,String sjjssj,String pcs,String uname,String ID,String bz,String status){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_INVENTORYASSET SET REALITYDATE=?,REALITYFINDATE=?,INVENTORYNUM=?,STAGE=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=?,REMARK = ? WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),sjkssj,sjjssj,pcs,status,uname,bz,ID);
    }

    public int delInventoryAsset(String bxcode){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_INVENTORYASSET set DELETE_TYPE=1 WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),bxcode);
    }

    public List<Map<String,Object>> selectFixAsset(String param,String uname,String pddh){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT r.* ,t.LOG \n" +
                "FROM IT_ASSET_FIXASSET r \n" +
                "LEFT JOIN (SELECT * from IT_ASSET_INVENTORY_LOG WHERE INVENTORYASSET = ?   AND company = (SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB WHERE AA.OFFICEID=BB.OFFICE_ID AND BB.DEL_FLAG = '0' AND BB.LOGIN_NAME =?)) t \n" +
                "ON r.assets_id = t.ASSETS_ID \n" +
                "WHERE r.DEL_LOG=0 and r.STATUS <> '04' \n" +
                "and r.ASSETS_COMPANY = (SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB WHERE AA.OFFICEID=BB.OFFICE_ID AND BB.DEL_FLAG = '0' AND BB.LOGIN_NAME =?)    ");
        listpar.add(pddh);
        listpar.add(uname);
        listpar.add(uname);
        if(!StringUtils.isBlank(param)){
            sql.append("  and  (  ");
            String[] data= param.split(",");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }else {
                    sql.append(" or  r.ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }

            }
            sql.append("  ) ");

        }
        sql.append(" ORDER BY r.UPDATE_DATE DESC , r.ASSETS_ID DESC ");

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countFixAsset(String param,String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT count(*) COUNTS   FROM IT_ASSET_FIXASSET r WHERE  r.DEL_LOG=0 and STATUS <> '04' and ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)   ");
        listpar.add(uname);
        if(!StringUtils.isBlank(param)){
            sql.append("  and  (  ");
            String[] data= param.split(",");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }else {
                    sql.append(" or  r.ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }

            }
            sql.append("  ) ");

        }
        /*if(!StringUtils.isBlank(param)){
            sql.append("  and r.ATTRIBUTION = ? ");
            listpar.add(param);
        }*/
        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }

    public int addInventoryAsset(String pdbh,String jhkssj,String jhjssj,String sjkssj,String sjjssj,String pdr,String yps,String pcs, String bz, String zdbm, String uname){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO IT_ASSET_INVENTORYASSET (INVENTORY_ID,PLANDATE,PLANFINDATE,REALITYDATE,REALITYFINDATE,INVENTORYER,STAGE,DISKCOUNT,INVENTORYNUM\n" +
                ",REMARK,ATTRIBUTION,DELETE_TYPE,CREATE_NAME,CREAT_TIME,UPDATE_DATE,UPDATE_NAME,company) VALUES(?,?,?,?,?,?,0,?,?,?,?,0,?,SYSDATE,SYSDATE,?,(SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)) ");
        return itsmJdbcTemplate.update(sql.toString(),pdbh,jhkssj,jhjssj,sjkssj,sjjssj,pdr,yps,pcs,bz,zdbm,uname,uname,uname);
    }

    public List sumAttribution(String param,String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT SUM(num) FROM IT_ASSET_FIXASSET WHERE  DEL_LOG = 0 and STATUS <> '04' and assets_company=(SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)  ");
        listpar.add(uname);
        if(!StringUtils.isBlank(param)){
            sql.append("  and  (  ");
            String[] data= param.split(",");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }else {
                    sql.append(" or  ATTRIBUTION = ?  ");
                    listpar.add(data[i]);
                }

            }
            sql.append("  ) ");

        }

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selectLicense(Integer page, Integer limit, String param){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT tt.* FROM( SELECT r.*,rownum rowno FROM IT_ASSET_LICENSE r WHERE r.del_log = 0 AND r.assets_id =?  ");
        listpar.add(param);
        sql.append(" and rownum <=? ORDER BY UPDATE_DATE DESC )tt where tt.rowno >? ");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map licenseCount(String param){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS FROM IT_ASSET_LICENSE r WHERE  r.del_log = 0 AND r.ASSETS_ID =?    ");
        listpar.add(param);


        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }






    public int addLicense(String LICENSENUM,String LICENSECOMPANY,String ASSETS_ID,String uname){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" INSERT into IT_ASSET_LICENSE(LICENSENUM,LICENSECOMPANY,ASSETS_ID,DEL_LOG,UPDATE_DATE,UPDATE_NAME) VALUES(?,?,?,0,SYSDATE,?) ");

        listpar.add(LICENSENUM);
        listpar.add(LICENSECOMPANY);
        listpar.add(ASSETS_ID);
        listpar.add(uname);

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }


    public int updetLicense(String LICENSENUM,String LICENSECOMPANY,String uname,String ID){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  UPDATE IT_ASSET_LICENSE SET LICENSENUM=?,LICENSECOMPANY=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE id=? ");

        listpar.add(LICENSENUM);
        listpar.add(LICENSECOMPANY);
        listpar.add(uname);
        listpar.add(ID);

        return itsmJdbcTemplate.update(sql.toString(),listpar.toArray());
    }
    public int delLicense(String ID){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" UPDATE IT_ASSET_LICENSE SET  del_log=1 WHERE ID=?  ");



        return itsmJdbcTemplate.update(sql.toString(),ID);
    }

    /*增加盘点记录标记*/
    public int addInventoryAssetLog(String INVENTORYASSET,String uname,String assets_id,String data2){
        StringBuilder sql = new StringBuilder();
        int resurt = 0;

            sql.append("INSERT INTO IT_ASSET_INVENTORY_LOG(INVENTORYASSET\n" +
                    ",ASSETS_ID\n" +
                    ",LOG\n" +
                    ",CREATE_TIME\n" +
                    ",CREATE_NAME\n" +
                    ",UPDATE_DATE\n" +
                    ",UPDATE_NAME\n" +
                    ",COMPANY) VALUES (?,?,?,SYSDATE,?,SYSDATE,?,(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?))");
            resurt = itsmJdbcTemplate.update(sql.toString(),INVENTORYASSET,assets_id,data2,uname,uname,uname);


        return resurt;
    }


    public List<Map<String,Object>> selectInventoryAssetLog(String INVENTORYASSET,String uname,String assets_id){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT * from IT_ASSET_INVENTORY_LOG WHERE INVENTORYASSET = ? and COMPANY = (SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?) and assets_id = ? ");
        listpar.add(INVENTORYASSET);
        listpar.add(uname);
        listpar.add(assets_id);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int updetInventoryAssetLog(String INVENTORYASSET,String uname,String data2,String assets_id){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        int resurt = 0;
            sql.append("UPDATE IT_ASSET_INVENTORY_LOG SET LOG = ? WHERE INVENTORYASSET = ? AND ASSETS_ID = ? AND company = (SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)");
            resurt = itsmJdbcTemplate.update(sql.toString(),data2,INVENTORYASSET,assets_id,uname);


        return resurt;
    }

    public List<Map<String,Object>> selectInventoryAssetLog1(String INVENTORYASSET,String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT * from IT_ASSET_INVENTORY_LOG WHERE INVENTORYASSET = ? and COMPANY = (SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)");
        listpar.add(INVENTORYASSET);
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int updetInventoryAssetLog1(String INVENTORYASSET,String uname,String[] data){

        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        int resurt = 0;
        sql.append("UPDATE IT_ASSET_INVENTORY_LOG SET LOG = '1' WHERE INVENTORYASSET = ? AND company = (SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?) ");
        listpar.add(INVENTORYASSET);
        listpar.add(uname);
        if (data.length != 0){
            for (int i = 0;i<data.length;i++){
                sql.append(" and ASSETS_ID <> ? ");
                listpar.add(data[i]);
            }

        }
        resurt = itsmJdbcTemplate.update(sql.toString(),listpar.toArray());


        return resurt;
    }
    public List<Map<String,Object>> selInventoryid(String inventoryid,String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT count(*) counts from IT_ASSET_INVENTORYASSET r WHERE r.INVENTORY_ID = ? AND r.company = (SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?)  ");
        listpar.add(inventoryid);
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());

    }
}
