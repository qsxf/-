package com.spring.intangibleAsset;


import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class IntangibleAssetDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;
    /**
     * 添加无形资产信息
     * @return
     */
    public int addIntangible(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                        String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                        String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                        String uname,String TYPE_NAME,String UNIT,String STATUS,String LOCATION){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO IT_ASSET_INTANGIBLEASSET(ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE\n" +
                ",PURCHASEDATE,DEL_LOG,CREATE_NAME,CREATE_DATE,UPDATE_DATE,UPDATE_NAME,TYPE_NAME,UNIT,LOCATION,STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,SYSDATE,SYSDATE,?,?,?,?,?)");
        if(StringUtils.isBlank(PURCHASEDATE)){
            PURCHASEDATE="1900-01-01";
        }
        return itsmJdbcTemplate.update(sql.toString(),ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,uname,TYPE_NAME,UNIT,LOCATION,STATUS);

    }

    /**
     *根据id修改无形资产信息
     * @return
     */
    public int updateIntangibleById(String ASSETS_ID,String ASSETS_NAME,String BRAND,String NUM,String LICENSELIMIT,
                               String LICENSEAMT,String BUY_DEPARTMENT,String USE_DEPARTMENT,String PRICE,
                               String ATTRIBUTION,String RESPMAN,String POSTINGDATE,String PURCHASEDATE,
                               String uname,String ID,String TYPE_NAME,String UNIT,String STATUS,String LOCATION){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_INTANGIBLEASSET SET ASSETS_ID = ?,ASSETS_NAME = ?,BRAND = ?,NUM = ?,LICENSELIMIT = ?,LICENSEAMT = ?,BUY_DEPARTMENT = ?,USE_DEPARTMENT = ?,PRICE = ?,ATTRIBUTION = ?,RESPMAN = ?,POSTINGDATE = ?\n" +
                ",PURCHASEDATE = ?,UPDATE_DATE = SYSDATE,UPDATE_NAME = ?,TYPE_NAME=?,UNIT=?,STATUS=?,LOCATION=? WHERE ID = ?");
        if(StringUtils.isBlank(PURCHASEDATE)){
            PURCHASEDATE="1900-01-01";
        }
        return itsmJdbcTemplate.update(sql.toString(),ASSETS_ID,ASSETS_NAME,BRAND,NUM,LICENSELIMIT,
                LICENSEAMT,BUY_DEPARTMENT,USE_DEPARTMENT,PRICE,ATTRIBUTION,RESPMAN,POSTINGDATE,PURCHASEDATE,uname,TYPE_NAME,UNIT,STATUS,LOCATION,ID);

    }

    /**
     * 根据id删除无形资产
     * @return
     */
    public int delIntangibleById(String ID){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_INTANGIBLEASSET SET DEL_LOG=1 WHERE ID=?");
        return itsmJdbcTemplate.update(sql.toString(),ID);
    }
    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
    public List<Map<String,Object>> selectIntangibleAsset(Integer page, Integer limit, String param, String bzDate,String uname,List user,String param1,String param2,
                                                          String param3,String param4,String param5,String param6,String param7p,String param7l,String param8p,String param8l,
                                                          String param9,String param10,String param11,String param12,
                                                          String param13,String param14,String param15p,String param15l,String param16,String param17){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from (SELECT tt.*,rownum rowno  FROM( SELECT r.*,t.LICENSENUM,s.TYPENM\n" +
                "FROM IT_ASSET_INTANGIBLEASSET r \n" +
                "left JOIN (SELECT SUM(licensenum) LICENSENUM,assets_id ass from IT_ASSET_LICENSE WHERE DEL_LOG=0  GROUP BY ASSETS_ID) t \n" +
                "ON r.ASSETS_ID = t.ASS \n" +
                "LEFT JOIN IT_TYPE_DICT s on r.STATUS=s.typecd WHERE  s.CLASSNM = '无形资产状态'\n" +
                "AND r.del_log = 0  ");
        if (user.isEmpty()){
            sql.append("AND r.ATTRIBUTION=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.ATTRIBUTION like ? or r.RESPMAN like ? or r.BRAND like ?) ");
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
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            sql.append("  and  (  ");
            String[] data= param1.split("，");
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
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_NAME LIKE ?  ");
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
                    sql.append("   r.BRAND LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BRAND LIKE ?  ");
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
                    sql.append("   r.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.TYPE_NAME LIKE ?  ");
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
                    sql.append("   r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6)){
            String[] data= param6.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.LICENSELIMIT AND r.LICENSELIMIT<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param7p)||!StringUtils.isBlank(param7l)){
            if (!StringUtils.isBlank(param7p)&&StringUtils.isBlank(param7l)){
                sql.append("  AND ? <=r.LICENSEAMT ");
                listpar.add(param7p);
            }
            if (StringUtils.isBlank(param7p)&&!StringUtils.isBlank(param7l)){
                sql.append("  AND r.LICENSEAMT<=? ");
                listpar.add(param7l);
            }
            if (!StringUtils.isBlank(param7p)&&!StringUtils.isBlank(param7l)){
                sql.append("  AND ? <=r.LICENSEAMT AND r.LICENSEAMT<=? ");
                listpar.add(param7p);
                listpar.add(param7l);
            }
        }
        if(!StringUtils.isBlank(param8p)||!StringUtils.isBlank(param8l)){
            if (!StringUtils.isBlank(param8p)&&StringUtils.isBlank(param8l)){
                sql.append("  AND ? <=r.LICENSEAMT-t.LICENSENUM ");
                listpar.add(param8p);
            }
            if (StringUtils.isBlank(param8p)&&!StringUtils.isBlank(param8l)){
                sql.append("  AND r.LICENSEAMT-t.LICENSENUM<=? ");
                listpar.add(param8l);
            }
            if (!StringUtils.isBlank(param8p)&&!StringUtils.isBlank(param8l)){
                sql.append("  AND ? <=r.LICENSEAMT-t.LICENSENUM AND r.LICENSEAMT-t.LICENSENUM<=? ");
                listpar.add(param8p);
                listpar.add(param8l);
            }
        }
        if(!StringUtils.isBlank(param9)){
            sql.append("  and  (  ");
            String[] data= param9.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.BUY_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BUY_DEPARTMENT LIKE ?  ");
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
                    sql.append("   r.LOCATION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.LOCATION LIKE ?  ");
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
                    sql.append("   s.typenm LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.typenm LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param12)){
            sql.append("  and  (  ");
            String[] data= param12.split("，");
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
        if(!StringUtils.isBlank(param13)){
            sql.append("  and  (  ");
            String[] data= param13.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.USE_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.USE_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param14)){
            sql.append("  and  (  ");
            String[] data= param14.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param15p)||!StringUtils.isBlank(param15l)){
            if (!StringUtils.isBlank(param15p)&&StringUtils.isBlank(param15l)){
                sql.append("  AND ? <=r.PRICE ");
                listpar.add(param15p);
            }
            if (StringUtils.isBlank(param15p)&&!StringUtils.isBlank(param15l)){
                sql.append("  AND r.PRICE<=? ");
                listpar.add(param15l);
            }
            if (!StringUtils.isBlank(param15p)&&!StringUtils.isBlank(param15l)){
                sql.append("  AND ? <=r.PRICE AND r.PRICE<=? ");
                listpar.add(param15p);
                listpar.add(param15l);
            }
        }
        if(!StringUtils.isBlank(param16)){
            String[] data= param16.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.POSTINGDATE AND r.POSTINGDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param17)){
            String[] data= param17.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }

        sql.append("  ORDER BY r.UPDATE_DATE DESC,r.assets_id DESC ) tt  where rownum <=? ) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countIntangibleAsset(String param,String bzDate,String uname,List user,String param1,String param2,
                                    String param3,String param4,String param5,String param6,String param7p,String param7l,String param8p,String param8l,
                                    String param9,String param10,String param11,String param12,
                                    String param13,String param14,String param15p,String param15l,String param16,String param17){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT COUNT(*) COUNTS   FROM IT_ASSET_INTANGIBLEASSET r \n" +
                "left JOIN (SELECT SUM(licensenum) LICENSENUM,assets_id ass from IT_ASSET_LICENSE WHERE DEL_LOG=0  GROUP BY ASSETS_ID) t \n" +
                "ON r.ASSETS_ID = t.ASS \n" +
                "LEFT JOIN IT_TYPE_DICT s on r.STATUS=s.typecd WHERE  s.CLASSNM = '无形资产状态'\n" +
                "AND r.del_log = 0   ");
        if (user.isEmpty()){
            sql.append("AND r.ATTRIBUTION=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ?  or r.ATTRIBUTION like ? or r.RESPMAN like ? or r.BRAND like ?) ");
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
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        if(!StringUtils.isBlank(param1)){
            sql.append("  and  (  ");
            String[] data= param1.split("，");
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
        if(!StringUtils.isBlank(param2)){
            sql.append("  and  (  ");
            String[] data= param2.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("  r.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_NAME LIKE ?  ");
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
                    sql.append("   r.BRAND LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BRAND LIKE ?  ");
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
                    sql.append("   r.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.TYPE_NAME LIKE ?  ");
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
                    sql.append("   r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param6)){
            String[] data= param6.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.LICENSELIMIT AND r.LICENSELIMIT<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param7p)||!StringUtils.isBlank(param7l)){
            if (!StringUtils.isBlank(param7p)&&StringUtils.isBlank(param7l)){
                sql.append("  AND ? <=r.LICENSEAMT ");
                listpar.add(param7p);
            }
            if (StringUtils.isBlank(param7p)&&!StringUtils.isBlank(param7l)){
                sql.append("  AND r.LICENSEAMT<=? ");
                listpar.add(param7l);
            }
            if (!StringUtils.isBlank(param7p)&&!StringUtils.isBlank(param7l)){
                sql.append("  AND ? <=r.LICENSEAMT AND r.LICENSEAMT<=? ");
                listpar.add(param7p);
                listpar.add(param7l);
            }
        }
        if(!StringUtils.isBlank(param8p)||!StringUtils.isBlank(param8l)){
            if (!StringUtils.isBlank(param8p)&&StringUtils.isBlank(param8l)){
                sql.append("  AND ? <=r.LICENSEAMT-t.LICENSENUM ");
                listpar.add(param8p);
            }
            if (StringUtils.isBlank(param8p)&&!StringUtils.isBlank(param8l)){
                sql.append("  AND r.LICENSEAMT-t.LICENSENUM<=? ");
                listpar.add(param8l);
            }
            if (!StringUtils.isBlank(param8p)&&!StringUtils.isBlank(param8l)){
                sql.append("  AND ? <=r.LICENSEAMT-t.LICENSENUM AND r.LICENSEAMT-t.LICENSENUM<=? ");
                listpar.add(param8p);
                listpar.add(param8l);
            }
        }
        if(!StringUtils.isBlank(param9)){
            sql.append("  and  (  ");
            String[] data= param9.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.BUY_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BUY_DEPARTMENT LIKE ?  ");
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
                    sql.append("   r.LOCATION LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.LOCATION LIKE ?  ");
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
                    sql.append("   s.typenm LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  s.typenm LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param12)){
            sql.append("  and  (  ");
            String[] data= param12.split("，");
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
        if(!StringUtils.isBlank(param13)){
            sql.append("  and  (  ");
            String[] data= param13.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.USE_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.USE_DEPARTMENT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param14)){
            sql.append("  and  (  ");
            String[] data= param14.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.RESPMAN LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param15p)||!StringUtils.isBlank(param15l)){
            if (!StringUtils.isBlank(param15p)&&StringUtils.isBlank(param15l)){
                sql.append("  AND ? <=r.PRICE ");
                listpar.add(param15p);
            }
            if (StringUtils.isBlank(param15p)&&!StringUtils.isBlank(param15l)){
                sql.append("  AND r.PRICE<=? ");
                listpar.add(param15l);
            }
            if (!StringUtils.isBlank(param15p)&&!StringUtils.isBlank(param15l)){
                sql.append("  AND ? <=r.PRICE AND r.PRICE<=? ");
                listpar.add(param15p);
                listpar.add(param15l);
            }
        }
        if(!StringUtils.isBlank(param16)){
            String[] data= param16.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.POSTINGDATE AND r.POSTINGDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param17)){
            String[] data= param17.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }

        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }



    public List<Map<String,Object>> exlIntangibleAsset(String param,String bzDate,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT r.*,t.LICENSENUM,rownum rowno FROM IT_ASSET_INTANGIBLEASSET r left JOIN (SELECT SUM(licensenum) LICENSENUM,assets_id ass from IT_ASSET_LICENSE WHERE DEL_LOG=0  GROUP BY ASSETS_ID) t ON r.ASSETS_ID = t.ASS WHERE r.del_log = 0  ");
        if (user.isEmpty()){
            sql.append("AND r.ATTRIBUTION=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.ATTRIBUTION like ? or r.RESPMAN like ? or r.BRAND like ?) ");
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
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    /**
     * 查找无形资产单位
     */
    public List<Map<String,Object>> selWXDW(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.typecd,r.typenm FROM IT_TYPE_DICT r WHERE r.CLASSNM='无形资产单位' ");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }

    /**
     * 查找无形资产状态
     */
    public List<Map<String,Object>> selWXZT(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.typecd,r.typenm FROM IT_TYPE_DICT r WHERE r.CLASSNM='无形资产状态' ");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }

    /*
     *
     * 查找无形存放地点
     *
     * */
    public List<Map<String,Object>> selWXCFDD(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT * FROM IT_TYPE_DICT r WHERE r.CLASSNM='资产存放地点' AND r.COMPANY = (SELECT compcd from DZUG_ITF.V_OFFICE_COMP AA WHERE aa.COMPNM = (SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?) GROUP BY compcd) ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
}
