package cn.m1c.frame.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2016年8月15日 多数据源
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    String value();
}