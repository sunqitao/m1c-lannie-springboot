package cn.m1c.frame.enums;

/**
 * 2016年7月27日 enum interface
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public interface BaseEnum<T> {
    
    public String getName();

    public int getOrdinal();

    public String getChineseName();

    public T parsing(String element);
}
