package mytest;

import org.hsqldb.types.Type;
import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.mybatis.generator.internal.ObjectFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liujicheng on 2019/5/10.
 */
public class Luncher {
    public static void main(String[] args) throws SQLException {
//        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
//        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
//        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/orchard");
//        jdbcConnectionConfiguration.setUserId("root");
//        jdbcConnectionConfiguration.setPassword("111111");
//        ConnectionFactory connectionFactory;
//            connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
//
//        ResultSet result = connectionFactory.getConnection().getMetaData().getColumns(null, "article",
//                 "article", "%");


        test11(null, "article",
                "article", "%");
        System.out.println("");
    }



    public static void test11(String var1,String var2 ,String var3,String var4){
        StringBuffer var5 = toQueryPrefix("SYSTEM_COLUMNS").append(and("TABLE_CAT", "=", var1)).append(and("TABLE_SCHEM", "LIKE", var2)).append(and("TABLE_NAME", "LIKE", var3)).append(and("COLUMN_NAME", "LIKE", var4));
        System.out.println(var5);
    }

    private static StringBuffer toQueryPrefix(String var1) {
        StringBuffer var2 = new StringBuffer(255);
        return var2.append("SELECT * FROM INFORMATION_SCHEMA.").append(var1).append(" WHERE TRUE");
    }


    private static String and(String var0, String var1, Object var2) {
        if (var2 == null) {
            return "";
        } else {
            StringBuffer var3 = new StringBuffer();
            boolean var4 = var2 instanceof String;
            if (var4 && ((String)var2).length() == 0) {
                return var3.append(" AND ").append(var0).append(" IS NULL").toString();
            } else {
                String var5 = var4 ? Type.SQL_VARCHAR.convertToSQLString(var2) : String.valueOf(var2);
                var3.append(" AND ").append(var0).append(' ');
                if (var4 && "LIKE".equalsIgnoreCase(var1)) {
                    if (var5.indexOf(95) < 0 && var5.indexOf(37) < 0) {
                        var3.append("=").append(' ').append(var5);
                    } else {
                        var3.append("LIKE").append(' ').append(var5);
                        if (var5.indexOf("\\_") >= 0 || var5.indexOf("\\%") >= 0) {
                            var3.append(" ESCAPE '\\'");
                        }
                    }
                } else {
                    var3.append(var1).append(' ').append(var5);
                }

                return var3.toString();
            }
        }
    }
}
