package com.bupt.utils;

import com.bupt.domain.Profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

    public static String removeIllegalChar(String str) {
        return str.replace('<', ' ').replace('>', ' ');
    }

    public static boolean checkProfile(Profile profile){

        try {
            if (profile.getName() == null || profile.getName().trim().equals("")) {
                System.out.println('1');
                return false;
            }
            if (profile.getGender() == null || profile.getGender().trim().equals("")
                    ||!(
                        profile.getGender().equals("男")
                        ||profile.getGender().equals("女")
                    )){
                System.out.println('1');
                return false;
            }
            if (profile.getBirthday() == null || profile.getBirthday().trim().equals("")) {
                System.out.println('2');
                System.out.println(profile.getBirthday());
                return false;
            }

            if (profile.getType() == null || profile.getType().trim().equals("") ||
                    !(
                        profile.getType().equals("理科")
                        || profile.getType().equals("文科")
                        || profile.getType().equals("综合改革")
                    )){
                System.out.println('3');
                return false;
            }

            if (profile.getPhone() == null || profile.getPhone().trim().equals("") || !checkCellphone(profile.getPhone())) {
                System.out.println('4');
                return false;
            }
            if (profile.getMail() == null || profile.getMail().trim().equals("") || !checkEmail(profile.getMail())) {
                System.out.println('5');
                return false;
            }

            if (profile.getHobby().length() > 200) {
                System.out.println('6');
                return false;
            }
            if (profile.getDescription().length() > 600) {
                System.out.println('7');
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    static boolean flag = false;
    static String regex = "";

    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 匹配密码
     */
    public static boolean isPwd(String str) {
        regex = "^[a-zA-Z]\\w{6,12}$";
        return check(str, regex) ? false : true;
    }

    /**
     * 验证非空
     *
     * @return
     */
    public static boolean checkNotEmputy(String notEmputy) {
        if(notEmputy==null||notEmputy.trim().equals("")){
            return false;
        }
        regex = "^\\s*$";
        return check(notEmputy, regex) ? false : true;
    }


    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        return check(email, regex);
    }

    /**
     * 验证手机号码
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^1[34578]\\d{9}$";
        return check(cellphone, regex);
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(telephone, regex);
    }


    public static boolean checkUserName(String username){
        if(username==null){
            return false;
        }
        String reg = "^[0-9a-zA-Z@.+\\-_]{1,30}$";
        return Pattern.matches(reg, username);
    }

    public static boolean checkPassword(String password){
        if(password==null){
            return false;
        }
        String reg = "^[0-9a-zA-Z]{6,12}$";
        return Pattern.matches(reg, password);
    }
}
