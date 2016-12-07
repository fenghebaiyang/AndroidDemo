package com.main.androiddemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 *
 * @author 叶青
 * @version 1.0
 * @Description
 * @date 2015-10-14
 */
public class PatternUtil {


    /**
     * 是否是正确的邮箱格式
     *
     * @param source 需要验证的邮箱
     * @return true 正确手机号码；false 非法手机号码
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidEmail(String source) {
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 判断是否纯数字
     *
     * @param source 需要判断的源字符串
     * @return true 纯数字，false非纯数字
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isNumber(String source) {
        Pattern p = Pattern.compile("\\d*");
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 判断是纯字母串
     *
     * @param source 判断的源字符串
     * @return true是纯字母字符串，false非纯字母字符串
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isChar(String source) {
        Pattern p = Pattern.compile("[a-z]*[A-Z]*");
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 是否纯特殊符号串
     *
     * @param source 纯特殊符号串
     * @return true纯特殊符号串，false非纯特殊符号串
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isSymbol(String source) {
        Pattern p = Pattern.compile("[{\\[(<~!@#$%^&*()_+=-`|\"?,./;'\\>)\\]}]*");
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 是否合法帐号
     *
     * @param source
     * @return
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidAccount(String source) {
        Pattern p = Pattern.compile("^(?![0-9])[a-zA-Z0-9]+$");
        Matcher m = p.matcher(source);
        return m.matches() && source.length() >= 4 && source.length() <= 50;
    }

    /**
     * 是否正确密码(纯数字 纯字母 特殊字符 都可以)
     *
     * @param source 需要判断的密码
     * @return true 合格的密码，false不合法的密码
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidPassword(String source) {
        Pattern p = Pattern.compile("[\\d*[a-z]*[A-Z]*[{\\[(<~!@#$%^&*()_+=-`|\"?,./;'\\>)\\]}]*]*");
        Matcher m = p.matcher(source);
        return m.matches() && source.length() >= 6 && source.length() <= 15;
    }

    /**
     * 是否正确密码（ 数字 和字符）
     *
     * @param source 需要判断的密码
     * @return true 合格的密码，false不合法的密码
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidPasswordNew(String source) {
        // 1，不能全部是数字
        // 2，不能全部是字母
        // 3，必须是数字或字母
        // String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        // String regex = "^(?![0-9]+$)" + // 预测该位置后面不全是数字
        // "(?![a-zA-Z]+$)[0-9A-Za-z]" + // 预测该位置后面不全是字母
        // "{6,15}$" // 由8-16位数字或这字母组成
        // ;

        // 只允许字母和数字
        String regEx = "[a-zA-Z0-9]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(source);
        return m.matches() && source.length() >= 6 && source.length() <= 15;
    }

    /**
     * 判断是否符合正则的字符串
     *
     * @param source  需要判断的源字符串
     * @param pattern 用于判读的正则表达式
     * @return true if the source is valid of pattern,else return false
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidPattern(String source, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 正则校验身份证号码
     *
     * @param idcard 身份证号码
     * @return true 有效的身份证；false 无效的身份证
     * @version 1.0
     * @createTime 2015-12-22,下午5:45:09
     * @updateTime 2015-12-22,下午5:45:09
     * @createAuthor 叶青
     * @updateAuthor 叶青
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static boolean isValidIdCardNum(String idcard) {
        Pattern p1 = Pattern.compile("/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$/");
        Pattern p2 = Pattern.compile("/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$/");
        Matcher m1 = p1.matcher(idcard);
        Matcher m2 = p2.matcher(idcard);

        return m1.matches() && m2.matches();
    }

    /**
     * @param bankCard 基于Luhn算法验证银行卡的合法性
     * @return
     */
    public static boolean isBankCardValidity(String bankCard) {
        if (!isNumber(bankCard)) {
            return false;
        }
        int sum = 0;
        if (bankCard.length() % 2 == 0) {
            for (int i = bankCard.length() - 1; i >= 0; i--) {
                if (i % 2 == 0) {
                    int value = (bankCard.charAt(i) - '0') * 2;
                    sum += value > 9 ? value - 9 : value;
                } else {
                    sum += bankCard.charAt(i) - '0';
                }
            }
        } else {
            for (int i = bankCard.length() - 1; i >= 0; i--) {
                if (i % 2 == 0) {
                    sum += bankCard.charAt(i) - '0';
                } else {
                    int value = (bankCard.charAt(i) - '0') * 2;
                    sum += value > 9 ? value - 9 : value;
                }
            }
        }
        if (sum > 0 && sum % 10 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 校验是否是以零开的整数字符串 0[\d]+
     * <p>
     * <p/>
     * <br/> @version 1.0
     * <br/> @createTime 2015/11/17 15:26
     * <br/> @updateTime 2015/11/17 15:26
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param num 校验的字符串
     * @return
     */
    public static boolean isValidNaturalNumber(String num) {
        Pattern p = Pattern.compile("[0|.][\\d]+");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /**
     * <p/>  是否是整数
     * <br/> @version 1.0
     * <br/> @createTime 2016/10/29 0029 16:48
     * <br/> @updateTime 2016/10/29 0029 16:48
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param num
     * @return
     */
    public static boolean isInteger(String num) {
        Pattern p = Pattern.compile("^-?[1-9]\\d*|0$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /**
     * <p/>  是否是浮点数
     * <br/> @version 1.0
     * <br/> @createTime 2016/10/29 0029 16:48
     * <br/> @updateTime 2016/10/29 0029 16:48
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param num
     * @return
     */
    public static boolean isDouble(String num) {
        Pattern p = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /**
     * <p/>  是否是一个有理数
     * <br/> @version 1.0
     * <br/> @createTime 2016/10/29 0029 16:58
     * <br/> @updateTime 2016/10/29 0029 16:58
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param num
     * @return
     */
    public static boolean isPrimeNumber(String num) {
        return isInteger(num)||isDouble(num);
    }
}
