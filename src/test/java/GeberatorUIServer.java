/*
import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.sun.xml.internal.bind.api.impl.NameConverter;

public class GeberatorUIServer {

    public static void main(String[] args) {
        GeneratorConfig.GeneratorConfigBuilder builder = GeneratorConfig.builder();
        builder.jdbcUrl("jdbc:mysql://localhost:3306/marathon?nullNamePatternMatchesAll=true");
        builder.userName("root");
        builder.password("123456");
        builder.driverClassName("com.mysql.cj.jdbc.Driver");
        //builder.schemaName("myBusiness");
        //builder.tablePrefix("t_");

        builder.basePackage("com.example.marathon");
        builder.port(8068);//数据库schema，MSSQL,PGSQL,ORACLE,DB2类型的数据库需要指定
//数据库表前缀，生成entity名称时会去掉(v2.0.3新增)
//如果需要修改entity及其属性的命名规则，以及自定义各类生成文件的命名规则，可自定义一个NameConverter实例，覆盖相应的名称转换方法，详细可查看该接口的说明：
//所有生成的java文件的父包名，后续也可单独在界面上设置
        GeneratorConfig config = builder
                .build();
        MybatisPlusToolsApplication.run(config);
    }

}*/
