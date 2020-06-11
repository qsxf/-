package com.spring.allocationAsset;


import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 调拨dao层
 */
@Repository
public class AllocationAssetDao {

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
     *查询调拨信息
     * @param page
     * @param limit
     * @return
     */
    public List<Map<String,Object>> selectAllocation(Integer page, Integer limit,String param,String bzDate,String uname,List user,String param1,String param2,
                                                     String param3,String param4,String param5,String param6,String param7,String param8,
                                                     String param9,String param10,String param11){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
            sql.append("SELECT * from (SELECT tt.*,rownum rowno FROM( SELECT r.*,s.ASSETS_NAME,s.BRAND,s.TYPE_NAME,s.UNIT,s.Location,s.ASSETS_COMPANY FROM IT_ASSET_ALLOCATION r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DEL_LOG=0  and s.del_log=0  ");
        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ALLOCATION_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ? or s.ASSETS_COMPANY like ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.ALLOCATIONDATE AND r.ALLOCATIONDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.ALLOCATIONDATE AND r.ALLOCATIONDATE<=? ");
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
                    sql.append("  r.ALLOCATION_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ALLOCATION_ID LIKE ?  ");
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
                    sql.append("  s.brand LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.brand LIKE ?  ");
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
                    sql.append("  s.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.TYPE_NAME LIKE ?  ");
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
                    sql.append("  r.APPLYDEPART LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLYDEPART LIKE ?  ");
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
                    sql.append("  r.APPLYCOMPNM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLYCOMPNM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param9)){
            sql.append("  and  (  ");
            String[] data= param9.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.APPLICANT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLICANT LIKE ?  ");
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
                    sql.append("  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param11)){
            sql.append("  and  (  ");
            String[] data= param11.split("，");
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
        sql.append("  ORDER BY r.UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    /**
     * 调拨信息总条数
     * @return
     */
    public Map selCount(String param,String bzDate,String uname,List user,String param1,String param2,
                        String param3,String param4,String param5,String param6,String param7,String param8,
                        String param9,String param10,String param11){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT COUNT(*) COUNTS FROM IT_ASSET_ALLOCATION r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DEL_LOG=0  and s.del_log=0  ");
        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ALLOCATION_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ? or s.ASSETS_COMPANY like ?) ");
            listpar.add("'%"+param+"%'");
            listpar.add("'%"+param+"%'");
            listpar.add("'%"+param+"%'");
            listpar.add("'%"+param+"%'");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.ALLOCATIONDATE AND r.ALLOCATIONDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.ALLOCATIONDATE AND r.ALLOCATIONDATE<=? ");
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
                    sql.append("  r.ALLOCATION_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ALLOCATION_ID LIKE ?  ");
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
                    sql.append("  s.brand LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.brand LIKE ?  ");
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
                    sql.append("  s.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.TYPE_NAME LIKE ?  ");
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
                    sql.append("  r.APPLYDEPART LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLYDEPART LIKE ?  ");
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
                    sql.append("  r.APPLYCOMPNM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLYCOMPNM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param9)){
            sql.append("  and  (  ");
            String[] data= param9.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.APPLICANT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.APPLICANT LIKE ?  ");
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
                    sql.append("  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param11)){
            sql.append("  and  (  ");
            String[] data= param11.split("，");
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
        sql.append(" SELECT r.*,s.ASSETS_NAME,s.BRAND,s.TYPE_NAME,s.UNIT,s.Location  FROM IT_ASSET_ALLOCATION r LEFT JOIN IT_ASSET_FIXASSET s ON r.ASSETS_ID=s.ASSETS_ID WHERE  r.DEL_LOG=0   and s.del_log=0  ");
        if (user.isEmpty()){
            sql.append(" AND s.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.ALLOCATION_ID LIKE ?  OR r.ASSETS_ID LIKE ? OR s.ASSETS_NAME LIKE ? or s.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.ALLOCATIONDATE AND r.ALLOCATIONDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int upRepairAsset(String bxcode,String bxdate,String num,String bzName,String phone,String sqgs,String sqbm,String dbdz,String zrr,String dbqk,String id,String uname){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_ALLOCATION SET ALLOCATION_ID=?,ALLOCATIONDATE=?,NUM=?,APPLICANT=?,PHONE=?,  APPLYDEPART=?,APPLYCOMPNM=?,TOADRESS=? ,RESPMAN=?,ALLOCATIONDESC=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),bxcode,bxdate,num,bzName,phone,sqgs,sqbm,dbdz,zrr,dbqk,uname,id);
    }

    public int delRepairAsset(String bxcode){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE IT_ASSET_ALLOCATION set DEL_LOG=1 WHERE ID=? ");
        return itsmJdbcTemplate.update(sql.toString(),bxcode);
    }
}
