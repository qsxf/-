package com.spring.fixAsset;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
*
* 资产dao层
*
*/


@Repository
public class FixAssetDao {

    @Resource(name="qstb_jdbcTemplate")
    private JdbcTemplate itsmJdbcTemplate;


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
     * @param location
     * @param usedesc
     * @return
     */
    public int updateFixById(String id,String assetsid,String assetsname,String assetclass1,String assetclass2,String brand,
                        String typename,String num,String unit,String postingdate,
                        String purchasedate,String warranty,String servicelife,String attribution,
                        String respman,String price,String location,String uname,String usedesc){
        StringBuffer sql = new StringBuffer();
        sql.append("update IT_ASSET_FIXASSET set assets_id=?, assets_name=?,assetclass_1=?,assetclass_2=?,brand=?,\n" +
                " type_name=?,num=?,unit=?,postingdate=?,\n" +
                " purchasedate=?,warranty=?,servicelife=?,attribution=?,\n" +
                " respman=?,price=?,COMPANY=?,usedesc=?,UPDATE_DATE=SYSDATE,UPDATE_NAME=? where id=?");
        if(StringUtils.isBlank(purchasedate)){
            purchasedate="1900-01-01";
        }
        return itsmJdbcTemplate.update(sql.toString(),assetsid,assetsname,assetclass1,assetclass2,brand,
                typename,num,unit,postingdate,purchasedate,warranty,servicelife,attribution,respman,price,
                location,usedesc,uname,id);

    }

    /**
     * 根据id删除入库
     * @return
     */
    public int delFixAsset(String id){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_FIXASSET SET DEL_LOG=1 WHERE id=?");
        return itsmJdbcTemplate.update(sql.toString(),id);
    }

    public List<Map<String,Object>> selectUser(String uname){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * from  T_USER_ALLDATA_VISIT t WHERE t.login_name = ?  ");
        listpar.add(uname);
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public List<Map<String,Object>> selectFixAsset(Integer page, Integer limit,String param,String bzDate,String uname,List user,String param2,
                                                   String param3,String param4,String param5,String param6,String param7,String param8,
                                                   String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                                                   String param13p,String param13l,String param14,String param15,String param16,String param17,
                                                   String param18,String param19){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT * FROM (SELECT tt.*,ROWNUM rowno from (SELECT r.*,t.TYPENM FROM IT_ASSET_FIXASSET r LEFT JOIN IT_TYPE_DICT t on r.STATUS=t.typecd WHERE  r.status <> '04' and r.del_log = 0 and t.CLASSNM = '资产状态' "  );
        if (user.isEmpty()){
            sql.append("AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)  ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ?  or r.ASSETS_COMPANY like ?  or r.brand like ? or r.RESPMAN like ? or t.TYPENM LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
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
            sql.append("  AND ? <=r.purchasedate AND r.purchasedate<=? ");
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
            sql.append("  and  (  ");
            String[] data= param15.split("，");
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
        if(!StringUtils.isBlank(param16)){
            sql.append("  and  (  ");
            String[] data= param16.split("，");
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
        if(!StringUtils.isBlank(param18)){
            sql.append("  and  (  ");
            String[] data= param18.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   t.TYPENM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.TYPENM LIKE ?  ");
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
        sql.append(" ORDER BY  r.UPDATE_DATE DESC,r.ASSETS_ID DESC) tt WHERE ROWNUM <= ?)dd WHERE dd.rowno >?");
        listpar.add(page*limit);
        listpar.add((page-1)*limit);

        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public Map countStock(String param,String bzDate,String uname,List user,String param2,
                          String param3,String param4,String param5,String param6,String param7,String param8,
                          String param9,String param10,String param11p,String param11l,String param12p,String param12l,
                          String param13p,String param13l,String param14,String param15,String param16,String param17,
                          String param18,String param19){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append("  SELECT COUNT(*) COUNTS   FROM IT_ASSET_FIXASSET r LEFT JOIN IT_TYPE_DICT t \n" +
                "on r.STATUS=t.typecd WHERE  r.status <> '04' and r.del_log = 0 and t.CLASSNM = '资产状态'  " );
        if (user.isEmpty()){
            sql.append("AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }

        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ? or r.ASSETS_COMPANY like ? or r.brand like ? or r.RESPMAN like ? or t.TYPENM LIKE ?) ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
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
            sql.append("  AND ? <=r.purchasedate AND r.purchasedate<=? ");
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
            sql.append("  and  (  ");
            String[] data= param15.split("，");
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
        if(!StringUtils.isBlank(param16)){
            sql.append("  and  (  ");
            String[] data= param16.split("，");
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
        if(!StringUtils.isBlank(param18)){
            sql.append("  and  (  ");
            String[] data= param18.split("，");
            for (int i = 0; i < data.length; i++){
                if (i == 0){
                    sql.append("   t.TYPENM LIKE ?  ");
                    listpar.add("%"+data[i]+"%");
                }else {
                    sql.append(" or  t.TYPENM LIKE ?  ");
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



    public List<Map<String,Object>> exlFixAsset(String param,String bzDate,String uname,List user){
        StringBuilder sql = new StringBuilder();
        List listpar=new ArrayList();
        sql.append(" SELECT r.*,t.TYPENM FROM IT_ASSET_FIXASSET r LEFT JOIN IT_TYPE_DICT t on r.STATUS=t.typecd WHERE  r.status <> '04' and r.del_log = 0 and t.CLASSNM = '资产状态'   ");
        if (user.isEmpty()){
            sql.append("AND r.ASSETS_COMPANY=(SELECT DISTINCT AA.COMPNM \n" +
                    "FROM  DZUG_ITF.V_OFFICE_COMP AA,DZUG_ITF.IT_EIP_SYS_USER BB \n" +
                    "WHERE AA.OFFICEID=BB.OFFICE_ID \n" +
                    "AND BB.DEL_FLAG = '0' \n" +
                    "AND BB.LOGIN_NAME =?)   ");
            listpar.add(uname);
        }
        if(!StringUtils.isBlank(param)){
            sql.append("  and (r.assets_id like ? or r.assets_name like ? or r.assetclass_1 like ? or r.assetclass_2 like ?  or r.ASSETS_COMPANY like ? or r.brand like ? or r.RESPMAN like ? or t.TYPENM LIKE ?)  ");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
            listpar.add("%"+param+"%");
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
            sql.append("  AND ? <=r.purchasedate AND r.purchasedate<=? ");
            listpar.add(data1);
            listpar.add(data2);
        }
        return itsmJdbcTemplate.queryForList(sql.toString(),listpar.toArray());
    }

    public int addAllocation(String zcbh,String dbrq,String dbbh,String dbnum,String sqr,
                             String phone,String sqgs,String sqbm,String dbdz,String zrr,
                             String dbqk,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("insert into IT_ASSET_ALLOCATION (ALLOCATION_ID,ASSETS_ID,ALLOCATIONDATE,NUM,APPLICANT,PHONE,APPLYDEPART,APPLYCOMPNM,TOADRESS,RESPMAN,ALLOCATIONDESC,DEL_LOG,CREATER,CREAT_TIME,UPDATE_DATE,UPDATE_NAME) values(?,?,?,?,?,?,?,?,?,?,?,0,?,SYSDATE,SYSDATE,?) ");
        return itsmJdbcTemplate.update(sql.toString(),dbbh,zcbh,dbrq,dbnum,sqr,phone,sqbm,sqgs,dbdz,zrr,dbqk,uname,uname);

    }

    public int upAssetLocation(String id,String dbdz,String sqgs,String sqbm){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_FIXASSET SET LOCATION=?,COMPANY=?,ATTRIBUTION=? WHERE id=?");
        return itsmJdbcTemplate.update(sql.toString(),dbdz,sqgs,sqbm,id);
    }

    public int upAssetStatus(String id,String status){
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE IT_ASSET_FIXASSET SET status=? WHERE id=?");
        return itsmJdbcTemplate.update(sql.toString(),status,id);
    }

    public int addRepair(String bxbh,String zcbh,String bxsj,String bzr,String gzlx,
                         String gzdj,String gznr,String uname,String wxje){
        StringBuffer sql = new StringBuffer();
        sql.append("insert into IT_ASSET_REPAIRASSET (REPAIR_ID,ASSETS_ID,REPORTDATE,REPORTER,FAULTTYPE,FAULTCLASS,FAULTDESC,DELETE_TYPE,CREATE_TIME,CREATE_NAME,UPDATE_DATE,UPDATE_NAME,REPAIR_PRICE) VALUES(?,?,?,?,?,?,?,'0',SYSDATE,?,SYSDATE,?,?) ");
        return itsmJdbcTemplate.update(sql.toString(),bxbh,zcbh,bxsj,bzr,gzlx,gzdj,gznr,uname,uname,wxje);
    }

    public int addDepreciation(String depreciationId,String zcbh,String depredate,String depremethod,String serviceLife,
                               String netrate,String depremonths,String initialnetworth,String deprenowmonth,String depreall,String networth,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("insert into IT_ASSET_DEPRECIATION (DEPRECIATION_ID,ASSETS_ID,DEPREDATE,DEPREMETHOD,NETRATE,DEPREMONTHS,INITIALNETWORTH,DEPRENOWMONTH,DEPREALL,NETWORTH,DELETE_TYPE,CREATE_TIME,CREATE_NAME,UPDATE_DATE,UPDATE_NAME,SERVICE_LIFE) VALUES(?,?,?,?,?,?,?,?,?,?,'0',SYSDATE,?,SYSDATE,?,?) ");
        return itsmJdbcTemplate.update(sql.toString(),depreciationId,zcbh,depredate,depremethod,netrate,depremonths,initialnetworth,deprenowmonth,depreall,networth,uname,uname,serviceLife);
    }


    public int addDump(String bfbh,String zcbh,String bfsj,String bfyy,String uname){
        StringBuffer sql = new StringBuffer();
        sql.append("insert into IT_ASSET_DUMPASSET (DUMP_ID,ASSETS_ID,REASON,DUMPDATE,DELETE_TYPE,CREATE_TIME,CREATE_NAME,UPDATE_DATE,UPDATE_NAME) VALUES(?,?,?,?,'0',SYSDATE,?,SYSDATE,?) ");
        return itsmJdbcTemplate.update(sql.toString(),bfbh,zcbh,bfyy,bfsj,uname,uname);
    }
}
