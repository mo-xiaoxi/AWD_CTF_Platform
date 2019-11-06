/**
 * 密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
 * 验证密码
 * @param password
 * @returns {boolean}
 */
// function validatePassword(password) {
//     var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
//     return pPattern.test(password);
// }

/**
 *
 //正整数正则
 * @param number
 */
function validatePositiveInteger(number) {
    var posPattern = /^\d+$/;
    return posPattern.test(number);
}

/**
 * 验证Email
 * @param email
 * @returns {boolean}
 */
function validateEmail(email) {
    //Email正则
    var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    return ePattern.test(email);

}

/**
 * 电话号码
 * @param phone
 * @returns {boolean}
 */
function validatePhoneNumber(phone) {
    var mPattern = /^1[34578]\d{9}$/; //http://caibaojian.com/regexp-example.html
    return mPattern.test(phone);
}

/**
 * 身份证号（18位）正则
 * @param IDNumber
 * @returns {boolean}
 */
function validateIDNumber(IDNumber) {
    var cP = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
    return cP.test(IDNumber);

}

/**
 *
 //日期正则，复杂判定
 * @param date
 */
function validateDate(date) {
//日期正则，简单判定,未做月份及日期的判定
    var dP1 = dP2 = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    return dP1.test(date);
}

function validateLengthMax(str,max) {
    var len = getByteLen(str);
    return len<=max;
}

function validateLengthMin(str,min) {
    var len = getByteLen(str);
    return len>=min;
}

/**
 * 校验用户名
 * 要求：0-9a-zA-Z和@.+-_
 * 长度：30以内
 * @param username
 */
function validateUserName(username) {
    var uPattern = /^[0-9a-zA-Z@.+\-_]{1,30}$/;
    return uPattern.test(username);
}

/**
 * 校验密码
 * 要求：0-9a-zA-Z
 * 长度：6-12位
 * @param password
 */
function validatePassword(password) {
    var uPattern = /^[0-9a-zA-Z]{6,12}$/;
    return uPattern.test(password);
}
/**
 * 获取字符串长度，一个中文算两个字符
 * @param val
 * @returns {number}
 */
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null)
        {
            len += 2;
        }
        else
        {
            len += 1;
        }
    }
    return len;
}

function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}