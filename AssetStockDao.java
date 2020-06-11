package com.spring.assetStock;


import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class AssetStockDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;
    /**
     * 添加入库信息
     * @param assetsid
     * @param assetsname
     * @param assetclass1
     * @param assetclass2
     * @param brand
     * @param typename
     * @param num
     * @param unit
     * @param postingdate
     * @param purchasedate
     * @param warranty
     * @param servicelife
     * @param attribution
     * @param respman
     * @param price
     * @param location
     * @param usedesc
     * @return
     */
    public int addStock(String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                        String typename,String num,String unit,String postingdate,
                        String purchasedate,String warranty,String servicelife,String attribution,
                        String respman,String price,String location,String usedesc,String uname,String loca){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO IT_ASSET_FIXASSET (ASSETS_ID,ASSETS_NAME,ASSETCLASS_1,ASSETCLASS_2,BRAND,TYPE_NAME,NUM,\n" +
                "UNIT,POSTINGDATE,PURCHASEDATE,WARRANTY,SERVICELIFE,ATTRIBUTION,RESPMAN,PRICE,LOCATION,INCOMING_TIME,\n" +
                "USEDESC,STATUS,DEL_LOG,CREAT_TIME,CREAT_NAME,UPDATE_DATE,UPDATE_NAME,COMPANY,ASSETS_COMPANY) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,'01',0,SYSDATE,?,SYSDATE,?,?,?)");
        if(StringUtils.isBlank(purchasedate)){
            purchasedate="1900-01-01";
        }

        return itsmJdbcTemplate.update(sql.toString(),assetsid,assetsname,assetclass1,assetclass2,brand,
                typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,respman,price,loca,usedesc,uname,uname,location,location);

    }

    /**
     *根据id修改入库信息
     * @param assetsid
     * @param assetsname
     * @param assetclass1
     * @param assetclass2
     * @param brand
     * @param typename
     * @param num
     * @param unit
     * @param postingdate
     * @param purchasedate
     * @param warranty
     * @param servicelife
     * @param attribution
     * @param respman
     * @param price
     * @param usedesc
     * @return
     */
    public int updateStockById(String id,String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                               String typename,String num,String unit,String postingdate,
                               String purchasedate,String warranty,String servicelife,String attribution,
                               String respman,String price,String COMPANY,String usedesc,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("update IT_ASSET_FIXASSET set assets_id=?, assets_name=?,assetclass_1=?,assetclass_2=?,brand=?,\n" +
                " type_name=?,num=?,unit=?,postingdate=?,\n" +
                " purchasedate=?,warranty=?,servicelife=?,attribution=?,\n" +
                " respman=?,price=?,ASSETS_COMPANY=?,usedesc=?,update_date=SYSDATE,update_name=? where id =?");
        if(StringUtils.isBlank(purchasedate)){
            purchasedate="1900-01-01";
        }
        return itsmJdbcTemplate.update(sql.toString(),assetsid,assetsname,assetclass1,assetclass2,brand,
                typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,respman,price,
                COMPANY,usedesc,uname,id);

    }

    /**
     * 根据id删除入库
     * @return
     */
    public int delStorkById(String id){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_FIXASSET SET DEL_LOG=1 WHERE ID=?");
        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selectStockAsset(Integer page, Integer limit, String param, String bzDate1, String bzDate2,String uname,List user,String param2,
                                                     String param3,String param4,String param5,String param6,String param7,String param8,
                                                     String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                                                     String param13p,String param13l,String param14,String param15,String param17,
                                                     String param19){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT * from (SELECT tt.*,rownum rowno FROM( SELECT r.* FROM IT_ASSET_FIXASSET r WHERE r.status = '01' and r.del_log = 0  ");
        if (user.isEmpty()){
            sql.append(" AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                            "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                            "AND BB.DEL_FLAG = '0' \n" +
                            "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ?  or r.ASSETS_COMPANY like ? or r.brand like ? or r.RESPMAN like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate1)){
            String[] data= bzDate1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append(" AND r.incoming_time >= to_date(?,'yyyy-mm-dd  hh24:mi:ss') AND r.incoming_time <=  to_date(?,'yyyy-mm-dd hh24:mi:ss')");
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
                    sql.append("  r.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_NAME LIKE ?  ");
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
                    sql.append("   r.ASSETCLASS_1 LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETCLASS_1 LIKE ?  ");
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
                    sql.append("   r.ASSETCLASS_2 LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETCLASS_2 LIKE ?  ");
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
                    sql.append("   r.BRAND LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BRAND LIKE ?  ");
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
                    sql.append("   r.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.TYPE_NAME LIKE ?  ");
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
                    sql.append("   r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param9)){
            String[] data= param9.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.POSTINGDATE AND r.POSTINGDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param10)){
            String[] data= param10.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param11p)||!StringUtils.isBlank(param11l)){
            if (!StringUtils.isBlank(param11p)&&StringUtils.isBlank(param11l)){
                sql.append("  AND ? <=r.PRICE ");
                listpar.add(param11p);
            }
            if (StringUtils.isBlank(param11p)&&!StringUtils.isBlank(param11l)){
                sql.append("  AND r.PRICE<=? ");
                listpar.add(param11l);
            }
            if (!StringUtils.isBlank(param11p)&&!StringUtils.isBlank(param11l)){
                sql.append("  AND ? <=r.PRICE AND r.PRICE<=? ");
                listpar.add(param11p);
                listpar.add(param11l);
            }
        }
        if(!StringUtils.isBlank(param12p)||!StringUtils.isBlank(param12l)){
            if (!StringUtils.isBlank(param12p)&&StringUtils.isBlank(param12l)){
                sql.append("  AND ? <=r.WARRANTY ");
                listpar.add(param12p);
            }
            if (StringUtils.isBlank(param12p)&&!StringUtils.isBlank(param12l)){
                sql.append("  AND r.WARRANTY<=? ");
                listpar.add(param12l);
            }
            if (!StringUtils.isBlank(param12p)&&!StringUtils.isBlank(param12l)){
                sql.append("  AND ? <=r.WARRANTY AND r.WARRANTY<=? ");
                listpar.add(param12p);
                listpar.add(param12l);
            }
        }
        if(!StringUtils.isBlank(param13p)||!StringUtils.isBlank(param13l)){
            if (!StringUtils.isBlank(param13p)&&StringUtils.isBlank(param13l)){
                sql.append("  AND ? <=r.SERVICELIFE ");
                listpar.add(param13p);
            }
            if (StringUtils.isBlank(param13p)&&!StringUtils.isBlank(param13l)){
                sql.append("  AND r.SERVICELIFE<=? ");
                listpar.add(param13l);
            }
            if (!StringUtils.isBlank(param13p)&&!StringUtils.isBlank(param13l)){
                sql.append("  AND ? <=r.SERVICELIFE AND r.SERVICELIFE<=? ");
                listpar.add(param13p);
                listpar.add(param13l);
            }
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
        if(!StringUtils.isBlank(param15)){
            String[] data= param15.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append(" AND r.incoming_time >= to_date(?,'yyyy-mm-dd  hh24:mi:ss') AND r.incoming_time <=  to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            listpar.add(data1);
            listpar.add(data2);
        }

        if(!StringUtils.isBlank(param17)){
            sql.append("  and  (  ");
            String[] data= param17.split("，");
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

        if(!StringUtils.isBlank(param19)){
            sql.append("  and  (  ");
            String[] data= param19.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        sql.append("  ORDER BY UPDATE_DATE DESC ,ASSETS_ID DESC  )tt where rownum <=?) dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countStockAsset(String param,String bzDate1,String bzDate2,String uname,List user,String param2,
                               String param3,String param4,String param5,String param6,String param7,String param8,
                               String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                               String param13p,String param13l,String param14,String param15,String param17,
                               String param19){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS   FROM IT_ASSET_FIXASSET r WHERE  r.status = '01' and r.del_log = 0  "
               );
        if (user.isEmpty()){
            sql.append( "AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)    ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ? or r.ASSETS_COMPANY like ? or r.brand like ? or r.RESPMAN like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate1)){
            String[] data= bzDate1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append(" AND r.incoming_time >= to_date(?,'yyyy-mm-dd  hh24:mi:ss') AND r.incoming_time <=  to_date(?,'yyyy-mm-dd hh24:mi:ss')");
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
                    sql.append("  r.ASSETS_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_NAME LIKE ?  ");
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
                    sql.append("   r.ASSETCLASS_1 LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETCLASS_1 LIKE ?  ");
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
                    sql.append("   r.ASSETCLASS_2 LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETCLASS_2 LIKE ?  ");
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
                    sql.append("   r.BRAND LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.BRAND LIKE ?  ");
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
                    sql.append("   r.TYPE_NAME LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.TYPE_NAME LIKE ?  ");
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
                    sql.append("   r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.UNIT LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        if(!StringUtils.isBlank(param9)){
            String[] data= param9.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.POSTINGDATE AND r.POSTINGDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param10)){
            String[] data= param10.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append("  AND ? <=r.PURCHASEDATE AND r.PURCHASEDATE<=? ");
            listpar.add(data1);
            listpar.add(data2);

        }
        if(!StringUtils.isBlank(param11p)||!StringUtils.isBlank(param11l)){
            if (!StringUtils.isBlank(param11p)&&StringUtils.isBlank(param11l)){
                sql.append("  AND ? <=r.PRICE ");
                listpar.add(param11p);
            }
            if (StringUtils.isBlank(param11p)&&!StringUtils.isBlank(param11l)){
                sql.append("  AND r.PRICE<=? ");
                listpar.add(param11l);
            }
            if (!StringUtils.isBlank(param11p)&&!StringUtils.isBlank(param11l)){
                sql.append("  AND ? <=r.PRICE AND r.PRICE<=? ");
                listpar.add(param11p);
                listpar.add(param11l);
            }
        }
        if(!StringUtils.isBlank(param12p)||!StringUtils.isBlank(param12l)){
            if (!StringUtils.isBlank(param12p)&&StringUtils.isBlank(param12l)){
                sql.append("  AND ? <=r.WARRANTY ");
                listpar.add(param12p);
            }
            if (StringUtils.isBlank(param12p)&&!StringUtils.isBlank(param12l)){
                sql.append("  AND r.WARRANTY<=? ");
                listpar.add(param12l);
            }
            if (!StringUtils.isBlank(param12p)&&!StringUtils.isBlank(param12l)){
                sql.append("  AND ? <=r.WARRANTY AND r.WARRANTY<=? ");
                listpar.add(param12p);
                listpar.add(param12l);
            }
        }
        if(!StringUtils.isBlank(param13p)||!StringUtils.isBlank(param13l)){
            if (!StringUtils.isBlank(param13p)&&StringUtils.isBlank(param13l)){
                sql.append("  AND ? <=r.SERVICELIFE ");
                listpar.add(param13p);
            }
            if (StringUtils.isBlank(param13p)&&!StringUtils.isBlank(param13l)){
                sql.append("  AND r.SERVICELIFE<=? ");
                listpar.add(param13l);
            }
            if (!StringUtils.isBlank(param13p)&&!StringUtils.isBlank(param13l)){
                sql.append("  AND ? <=r.SERVICELIFE AND r.SERVICELIFE<=? ");
                listpar.add(param13p);
                listpar.add(param13l);
            }
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
        if(!StringUtils.isBlank(param15)){
            String[] data= param15.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append(" AND r.incoming_time >= to_date(?,'yyyy-mm-dd  hh24:mi:ss') AND r.incoming_time <=  to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            listpar.add(data1);
            listpar.add(data2);
        }

        if(!StringUtils.isBlank(param17)){
            sql.append("  and  (  ");
            String[] data= param17.split("，");
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

        if(!StringUtils.isBlank(param19)){
            sql.append("  and  (  ");
            String[] data= param19.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   r.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  r.ASSETS_COMPANY LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }

            }
            sql.append("  ) ");
        }
        return itsmJdbcTemplate.queryForMap(sql.toString(),listpar.toArray());
    }



    public List<Map<String,Object>> exlStockAsset(String param,String bzDate1,String bzDate2,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT r.*  FROM IT_ASSET_FIXASSET r  WHERE  r.status = '01' and r.del_log = 0   ");
        if (user.isEmpty()){
            sql.append( "AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)    ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ? or r.ASSETS_COMPANY like ? or r.brand like ? or r.RESPMAN like ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
        }
        if(!StringUtils.isBlank(bzDate1)){
            String[] data= bzDate1.split(" - ");
            String data1=data[0];
            String data2=data[1];
            sql.append(" AND r.incoming_time >= to_date(?,'yyyy-mm-dd  hh24:mi:ss') AND r.incoming_time <=  to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            listpar.add(data1);
            listpar.add(data2);
        }

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selYJFL(){
        StringBuilder sql = new StringBuilder();
        sql.append("select r.TYPECD,r.TYPENM\n" +
                "from it_type_dict r\n" +
                "where classnm ='资产填报' and REMARK = '一级分类'\n" +
                "order by 1");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }
    public List<Map<String,Object>> selEJFL(){
        StringBuilder sql = new StringBuilder();
        sql.append("select r.TYPECD,r.TYPENM\n" +
                "from it_type_dict r\n" +
                "where classnm ='资产填报' and REMARK = '二级分类'\n" +
                "order by 1");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }

    public List<Map<String,Object>> selBM(){
        StringBuilder sql = new StringBuilder();
        sql.append("select v.compnm,i.name\n" +
                "from DZUG_ITF.V_OFFICE_COMP v \n" +
                "LEFT JOIN DZUG_ITF.it_eip_sys_office i \n" +
                "ON v.officeid = i.id WHERE i.DEL_FLAG=0 AND v.COMPNM <> '未分组' and v.COMPNM <> '系统管理组' AND v.COMPNM <> i.name");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }
/*
*
* 查找存放地点
*
* */
    public List<Map<String,Object>> selCFDD(String company){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT * FROM IT_TYPE_DICT r WHERE r.CLASSNM='资产存放地点' AND r.COMPANY = (SELECT compcd from DZUG_ITF.V_OFFICE_COMP AA WHERE aa.COMPNM = ? GROUP BY compcd ) ");
        listpar.add(company);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }
    /**
     * 查找资产单位
     */
    public List<Map<String,Object>> selDW(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.typecd,r.typenm FROM IT_TYPE_DICT r WHERE r.CLASSNM='资产单位' ");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }
    /**
     * 查找面板类型
     */
    public List<Map<String,Object>> selMBLX(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.typecd,r.typenm FROM IT_TYPE_DICT r WHERE r.CLASSNM='面板类型' ");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }
    /**
     * 查找故障等级
     */
    public List<Map<String,Object>> selGZDJ(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.typecd,r.typenm FROM IT_TYPE_DICT r WHERE r.CLASSNM='故障等级' ");
        return itsmJdbcTemplate.queryForList(sql.toString());
    }
    public List<Map<String,Object>> selPDBM(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("select v.compnm,i.name \n" +
                "from DZUG_ITF.V_OFFICE_COMP v  \n" +
                "LEFT JOIN DZUG_ITF.it_eip_sys_office i \n" +
                "ON v.officeid = i.id WHERE i.DEL_FLAG=0 AND v.COMPNM <> '未分组' and v.COMPNM <> '系统管理组' AND v.COMPNM <> i.name \n" +
                "and v.COMPNM=(SELECT DISTINCT AA.COMPNM \n" +
                "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' AND BB.LOGIN_NAME =?)");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selGSGS(String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("select DISTINCT v.compnm from DZUG_ITF.V_OFFICE_COMP v  WHERE v.COMPNM <> '未分组' and v.COMPNM <> '系统管理组'  ");
        if (user.isEmpty()){
            sql.append("AND v.COMPNM=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());

    }

    public List<Map<String,Object>> selGS(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("SELECT DISTINCT AA.COMPNM FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                "AND BB.DEL_FLAG = '0' \n" +
                "AND BB.LOGIN_NAME =?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());

    }
}
