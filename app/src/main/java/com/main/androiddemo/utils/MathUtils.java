package com.main.androiddemo.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 计算
 *
 * @Description
 * @author 叶青
 * @version 1.0
 * @date 2015-11-24
 */
public class MathUtils {

    /**
     * 字符串A比较B
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:10:52
     * @updateTime 2015-12-30,下午9:10:52
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param a
     *            字符串A
     * @param b
     *            字符串B
     * @return
     */
    public static int strCompareTo(String a, String b) {
        int result = a.compareTo(b);
        // result > 0 大于 result < 0 小于 result = 0 等于
        return result;
    }

    /**
     * 浮点数A比较B
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:08
     * @updateTime 2015-12-30,下午9:11:08
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param a
     *            浮点数A
     * @param b
     *            浮点数B
     * @return
     */
    public static int doubleCompareTo(double a, double b) {
        int result = new BigDecimal(a).compareTo(new BigDecimal(b));
        // result > 0 大于 result < 0 小于 result = 0 等于
        return result;
    }

    // ****************2015年12月22日 17:33:09*********************
    // ********************************字符转整型 装码失败会默认返回0
    /**
     * 字符转整型 装码失败会默认返回0
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @return 转换数值
     */
    public static int str2Int(String str) {
        return str2Int(str, 0);
    }

    /**
     * 带默认值的字符转整型
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @param defaultValue
     *            默认值
     * @return 转换的数值
     */
    public static int str2Int(String str, int defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        } else {
            return Integer.parseInt(str);
        }
    }

    // ********************************字符转长整型 装码失败会默认返回0
    /**
     * 字符转长整型 装码失败会默认返回0
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @return 转换数值
     */
    public static long str2Long(String str) {
        return str2Long(str, 0);
    }

    /**
     * 带默认值的字符转长整型
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param str
     *            转换的字符串
     * @param defaultValue
     *            默认值
     * @return 转换的数值
     */
    public static long str2Long(String str, long defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        } else {
            return Long.parseLong(str);
        }
    }

    // ********************************字符转浮点型 失败会默认返回0
    /**
     * 字符转浮点型 失败会默认返回0
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @return 转换数值
     */
    public static float str2Float(String str) {
        return str2Float(str, 0);
    }

    /**
     * 带默认值的字符转浮点型
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @param defaultValue
     *            默认值
     * @return 转换的数值
     */
    public static float str2Float(String str, float defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        } else {
            return Float.parseFloat(str);
        }
    }

    // ********************************字符转双精度浮点型 失败会默认返回0.0
    /**
     * 字符转双精度浮点型 失败会默认返回0.0
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @return 转换数值
     */
    public static Double str2Double(String str) {
        return str2Double(str, 0.0);
    }

    /**
     * 带默认值的字符转双精度浮点型
     *
     * @version 1.0
     * @createTime 2015-12-30,下午9:11:34
     * @updateTime 2015-12-30,下午9:11:34
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param str
     *            转换的字符串
     * @param defaultValue
     *            默认值
     * @return 转换的数值
     */
    public static Double str2Double(String str, Double defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        } else {
            return Double.parseDouble(str);
        }
    }

    // ********************************四舍五入.失败会默认返回0.0
    /**
     * 四舍五入.
     *
     * @version 1.0
     * @createTime 2015-12-30,下午2:27:06
     * @updateTime 2015-12-30,下午2:27:06
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param number
     *            原数
     * @param decimal
     *            保留几位小数
     * @return 四舍五入后的值
     */
    public static double round4Double(double number, int decimal) {
        BigDecimal b = new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();
    }

    /**
     * 四舍五入.
     *
     * @version 1.0
     * @createTime 2015-12-30,下午2:27:06
     * @updateTime 2015-12-30,下午2:27:06
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param number
     *            原数
     * @param decimal
     *            保留几位小数
     * @return 四舍五入后的值
     */
    public static double round4String(String str, int decimal) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        BigDecimal b = new BigDecimal(str).setScale(decimal, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();
    }

    /**
     * 四舍五入.
     *
     * @version 1.0
     * @createTime 2015-12-30,下午2:27:06
     * @updateTime 2015-12-30,下午2:27:06
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param number
     *            原数
     * @param decimal
     *            保留几位小数
     * @return 四舍五入后的值
     */
    public static double round4Float(float number, int decimal) {
        BigDecimal b = new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();
    }

    /**
     * <p/>  App带人民币符号的商品的价格格式：￥1.00
     * <br/> @version 1.0
     * <br/> @createTime 2016/3/29 19:38
     * <br/> @updateTime 2016/3/29 19:38
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param value
     *            需要转换的价格
     *
     * */
    public static String priceForAppWithSign(String value) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return "¥" + df.format(str2Double(value));
    }

    /**
     * <p/>  App带人民币符号的商品的价格格式：￥1.00
     * <br/> @version 1.0
     * <br/> @createTime 2016/3/29 19:38
     * <br/> @updateTime 2016/3/29 19:38
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param value
     *            需要转换的价格
     *
     * */
    public static String priceForAppWithSign(double value) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return "¥" + df.format(value);
    }

    /**
     * <p/>  App未带人民币符号的商品的价格格式：1.00
     * <br/> @version 1.0
     * <br/> @createTime 2016/3/29 19:38
     * <br/> @updateTime 2016/3/29 19:38
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param value
     *            需要转换的价格
     * */
    public static String priceForAppWithOutSign(String value) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(str2Double(value));
    }

    /**
     * <p/>  App未带人民币符号的商品的价格格式：1.00
     * <br/> @version 1.0
     * <br/> @createTime 2016/3/29 19:38
     * <br/> @updateTime 2016/3/29 19:38
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param value
     *            需要转换的价格
     *
     * */
    public static String priceForAppWithOutSign(double value) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(value);
    }

    /**
     * <p/>  保留整数位
     * <br/> @version 1.0
     * <br/> @createTime 2016/3/29 19:38
     * <br/> @updateTime 2016/3/29 19:38
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param value 需要转换的价格
     */
    public static String priceForAppRound(String value) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(str2Double(value));
    }
}