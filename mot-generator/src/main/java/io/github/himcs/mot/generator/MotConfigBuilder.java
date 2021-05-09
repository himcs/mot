package io.github.himcs.mot.generator;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;

import java.util.Map;

public class MotConfigBuilder extends ConfigBuilder {


    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @param template         模板配置
     * @param globalConfig     全局配置
     */
    public MotConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, TemplateConfig template, GlobalConfig globalConfig) {
        super(packageConfig, dataSourceConfig, strategyConfig, template, globalConfig);
        Map<String, String> packageInfo = super.getPackageInfo();
        packageInfo.remove(ConstVal.SERVICE);
        packageInfo.remove(ConstVal.SERVICE_IMPL);
        packageInfo.remove(ConstVal.CONTROLLER);
        Map<String, String> pathInfo = super.getPathInfo();
        pathInfo.remove(ConstVal.SERVICE_PATH);
        pathInfo.remove(ConstVal.SERVICE_IMPL_PATH);
        pathInfo.remove(ConstVal.CONTROLLER_PATH);
    }

}
