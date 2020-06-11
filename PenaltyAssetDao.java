package com.spring.penaltyAsset;


import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class PenaltyAssetDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;
    /**
     * 添加罚则信息
     *
     * @return
     */
    public int addPenalty(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                        String PENAL,String REMARK,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO IT_ASSET_PENALASSET(PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,PENAL,REMARK,DEL_LOG,CREATE_NAME,CREATE_DATE,UPDATE_NAME,UPDATE_DATE)\n" +
                "VALUES (?,?,?,?,?,?,?,0,?,SYSDATE,?,SYSDATE)");
        return itsmJdbcTemplate.update(sql.toString(),PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                PENAL,REMARK,uname,uname);

    }

    /**
     *根据id修改罚则信息
     * @return
     */
    public int updatePenaltyById(String PENAL_ID,String ASSETS_ID,String PENALDATE,String REASON,String MONEY,
                               String PENAL,String REMARK,String uname,String id){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_PENALASSET SET PENAL_ID=?,ASSETS_ID=?,PENALDATE=?,REASON=?,MONEY=?,PENAL=?,REMARK=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? WHERE id=?");
        return itsmJdbcTemplate.update(sql.toString(),PENAL_ID,ASSETS_ID,PENALDATE,REASON,MONEY,
                PENAL,REMARK,uname,id);

    }

    /**
     * 根据id删除罚则
     * @return
     */
    public int delPenaltyById(String id){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_PENALASSET SET DEL_LOG=1 WHERE ID=?");
        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selectPenaltyAsset(Integer page, Integer limit, String param, String bzDate,String uname,List user,String param1,String param2,
                                                       String param3,String param4,String param5,String param6p,String param6l,String param7,String param8){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from (SELECT tt.*,rownum rowno FROM( SELECT r.*,t.assets_name,t.ASSETS_COMPANY FROM IT_ASSET_PENALASSET r \n" +
                "LEFT JOIN IT_ASSET_FIXASSET t \n" +
                "ON r.assets_id = t.assets_id \n" +
                "WHERE r.del_log = 0  and t.del_log=0   ");
        if (user.isEmpty()){
            sql.append(" AND t.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?) ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or t.assets_name like ? or r.PENAL_ID like ? or r.PENAL like ? or t.ASSETS_COMPANY like ? ) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PENALDATE AND r.PENALDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PENALDATE AND r.PENALDATE<=? ");
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
                    sql.append("  t.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.ASSETS_NAME LIKE ?  ");
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
                    sql.append("  r.PENAL_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.PENAL_ID LIKE ?  ");
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
                    sql.append("  r.REASON LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REASON LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6p)||!StringUtils.isBlank(param6l)){
            if (!StringUtils.isBlank(param6p)&&StringUtils.isBlank(param6l)){
                sql.append("  AND ? <=r.MONEY ");
                listpar.add(param6p);
            }
            if (StringUtils.isBlank(param6p)&&!StringUtils.isBlank(param6l)){
                sql.append("  AND r.MONEY<=? ");
                listpar.add(param6l);
            }
            if (!StringUtils.isBlank(param6p)&&!StringUtils.isBlank(param6l)){
                sql.append("  AND ? <=r.MONEY AND r.MONEY<=? ");
                listpar.add(param6p);
                listpar.add(param6l);
            }
        }
        if(!StringUtils.isBlank(param7)){
            sql.append("  and  (  ");
            String[] data= param7.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.PENAL LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.PENAL LIKE ?  ");
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
                    sql.append("   t.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        sql.append(" ORDER BY r.UPDATE_DATE DESC )tt where rownum <=?) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countPenaltyAsset(String param,String bzDate,String uname,List user,String param1,String param2,
                                 String param3,String param4,String param5,String param6p,String param6l,String param7,String param8){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS   FROM IT_ASSET_PENALASSET r LEFT JOIN IT_ASSET_FIXASSET t ON r.assets_id = t.assets_id WHERE r.del_log = 0 and t.del_log=0  ");
        if (user.isEmpty()){
            sql.append(" AND t.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)     ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or t.assets_name like ? or r.PENAL_ID like ? or r.PENAL like ? or t.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PENALDATE AND r.PENALDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            String[] data= param1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PENALDATE AND r.PENALDATE<=? ");
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
                    sql.append("  t.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.ASSETS_NAME LIKE ?  ");
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
                    sql.append("  r.PENAL_ID LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.PENAL_ID LIKE ?  ");
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
                    sql.append("  r.REASON LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.REASON LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }
            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6p)||!StringUtils.isBlank(param6l)){
            if (!StringUtils.isBlank(param6p)&&StringUtils.isBlank(param6l)){
                sql.append("  AND ? <=r.MONEY ");
                listpar.add(param6p);
            }
            if (StringUtils.isBlank(param6p)&&!StringUtils.isBlank(param6l)){
                sql.append("  AND r.MONEY<=? ");
                listpar.add(param6l);
            }
            if (!StringUtils.isBlank(param6p)&&!StringUtils.isBlank(param6l)){
                sql.append("  AND ? <=r.MONEY AND r.MONEY<=? ");
                listpar.add(param6p);
                listpar.add(param6l);
            }
        }
        if(!StringUtils.isBlank(param7)){
            sql.append("  and  (  ");
            String[] data= param7.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.PENAL LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.PENAL LIKE ?  ");
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
                    sql.append("   t.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }



    public List<Map<String,Object>> exlPenaltyAsset(String param,String bzDate,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT r.*,t.assets_name FROM IT_ASSET_PENALASSET r LEFT JOIN IT_ASSET_FIXASSET t ON r.assets_id = t.assets_id WHERE r.del_log = 0  and t.del_log=0   ");
        if (user.isEmpty()){
            sql.append(" AND t.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)     ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or t.assets_name like ? or r.PENAL_ID like ? or r.PENAL like ? or t.ASSETS_COMPANY like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate)){
            String[] data= bzDate.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PENALDATE AND r.PENALDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        sql.append("  ORDER BY r.CREATE_DATE DESC   ");
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
}
