package cn.m1c.frame.utils;
import java.util.UUID;
/**
 * 2016年7月29日 m1c id generator tools
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class UUIDGenerator {
 public UUIDGenerator() {
 }

 public static String getUUID() {
  UUID uuid = UUID.randomUUID();
  String str = uuid.toString();
  // 去掉"-"符号
  return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
 }
 //获得指定数量的UUID
 public static String[] getUUID(int number) {
  if (number < 1) {
   return null;
  }
  String[] ss = new String[number];
  for (int i = 0; i < number; i++) {
   ss[i] = getUUID();
  }
  return ss;
 }

 public static void main(String[] args) {
  String[] ss = getUUID(10);
  for (int i = 0; i < ss.length; i++) {
   System.out.println("ss["+i+"]====="+ss[i].length());
  }
 }
} 
