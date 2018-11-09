// JavaScript Document
$(function () {
    fn();
    $(window).on('resize', function () {
        fn()

    });
});
function fn() {
    var winWidth = window.innerWidth || document.documentElement && document.documentElement.clientWidth || 0,
        pageWidth = 'SMALL';

    // 获取 body 宽度设定。
    var pageWidthDef = {
        SMALL: 1000,
        LARGE: 1200
    };
    if (winWidth >= 1200) {    // @media screen and (min-width: 1280px)
        pageWidth = 'LARGE';
    } else {                  // Default width.
        pageWidth = 'SMALL';
    }


    //document.body.className += (' pu' + pageWidth.toLowerCase());
    $("body").attr("class", ' pu' + pageWidth.toLowerCase());
}
$(document).ready(function () {
    //ToolTop
    menuHover($("#menu li>a"), $("#menu li p"), function (_this) {
        _this.siblings().addClass("OnCurt");
    }, function (_this) {
        _this.siblings().removeClass("OnCurt");
    }, 200);
    //Navbar
    var timer;
    $("#Navbar").hover(function () {
        clearTimeout(timer);
        timer = setTimeout(function () {
            $("#ToolNav").addClass("ToolNavbar-hover");
        }, 300);
    }, function () {
        clearTimeout(timer);
        $("#ToolNav").removeClass("ToolNavbar-hover");
    });

    $(".WrapHid").each(function () {
        checkFocus({
            obj_input: $(this),
            msgBox: $(this).siblings(".CentHid"),
            Tip: "CentHid"
        });
        clearInput({
            obj_input: $(this),
            msgBox: $(this).siblings("._CentHid"),
            Tip: "_CentHid"
        });
    });

    $(".ToolChoese").each(function () {
        _select({
            select: $(this).find(".SearChoese"),
            options: $(this).find("ul.SearChoese-show"),
            option: $(this).find("ul.SearChoese-show li a"),
            t: "slide", //效果（可选参数）
            parents: $(".ToolChoese")//多个select时，传入父级（可选参数）
        });
    });

    Init(); //保存输入框的记录 ::如果输入框要保存为url input[url='true'],关键词则为input[words='true']
    getLochis();
    boxScroll({
        _scroll: $("#toTop"),
        _width: 30,
        isresize: true,
        callback: function (op, _scrolltop) {
            if (_scrolltop >= 100) {
                $("#TFloat").fadeIn(200);
            } else {
                $("#TFloat").fadeOut(200);
            }
            $("#TFloat").click(function () {
                $("html,body").stop().animate({ scrollTop: 0 }, 200);
            });
        }
    });
    menuHover($("#record"), $("#RecordShow"));
    $("input[isnum='true']").on("keyup keydown", function (e) { entNumber(e, 1) }); //只能输入(不包含小数点)数字
    $("input[isnums='true']").on("keyup keydown", function (e) { entNumber(e) }); //只能输入(或包含小数点)数字 

    $(".ToFooter a").hover(function () {
        $(".ToFooter a").removeClass("ToCurt");
        $(this).addClass("ToCurt");
        var index = $(this).index();
        $(".GMFocusBox").addClass("autohide")
        $(".GMFocusBox").eq(index).removeClass("autohide");
    });
    //    if (loadscripts) {
    //        for (var i = 0; i < loadscripts.length; i++) {
    //            loadScript({ url: loadscripts[i], elms: document.getElementsByTagName("body")[0] })
    //        } 
    //    }
    if ($("#settingpopup").length) {
        $("#settingpopup").addClass("autohide");
        $("#top_link_center").mouseover(function () {
            $("#settingpopup").removeClass("autohide");
        });
    }
    //粘贴IP 分解
    $(".ipgroup").each(function () {
        var group = $(this);
        group.find("input[n^='ip']").bind("paste", function (e) {
            var obj = e.target ? e.target : e.srcElement;
            setTimeout(function () {
                var ip = $(obj).val().trim();
                var ipReg = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)(\/(2[0-4]\d|25[0-5]|[01]?\d\d?))?$/;
                if (!ipReg.test(ip)) return;
                var ipArr = ip.split('.');
                group.find("[n='ip1']").val(ipArr[0]);
                group.find("[n='ip2']").val(ipArr[1]);
                group.find("[n='ip3']").val(ipArr[2]);
                var iplastByte = ipArr[3];//允许输入127.0.0.1/2  输出结果为 127 0 0 2
                if (iplastByte.indexOf("/")) {
                    iplastByte = iplastByte.split('/');
                    group.find("[n='ip4']").val(iplastByte[iplastByte.length - 1]);
                } else
                    group.find("[n='ip4']").val(iplastByte);
            }, 50);
        });
    });


});

function menuHover(menuObj, menuItem, itemOverbackall, itemOutbackall, timer) {
    if (!timer) timer = 200;
    var hoverTimer, outTimer;
    menuObj.hover(function () {
        var _this = $(this);
        clearTimeout(hoverTimer);
        hoverTimer = setTimeout(function () {
            menuItem.hide();
            _this.nextAll().show();
        }, timer);
    }, function () {
        var _this = $(this);
        clearTimeout(outTimer);
        outTimer = setTimeout(function () {
            _this.nextAll().hide();
        }, timer);
    });
    menuItem.hover(function () {
        var _this = $(this);
        clearTimeout(hoverTimer);
        hoverTimer = setTimeout(function () {
            _this.show();
        }, timer);
        if (itemOverbackall) itemOverbackall(_this);
    }, function () {
        var _this = $(this);
        clearTimeout(outTimer);
        outTimer = setTimeout(function () {
            _this.hide();
        }, timer);
        if (itemOutbackall) itemOutbackall(_this);
    });
};
//SearchWrapHid-Cent
var checkFocus = function (options) {
    var thisCheck = options.obj_input; //当前input
    var msgBox = options.msgBox; //当前提示标签
    checkValue = thisCheck.val();
    var trime = options.trime !== undefined ? options.trime : 200;
    thisCheck.focus(function () {
        msgBox.fadeOut(trime);
    });
    thisCheck.blur(function () {
        if ($(this).val() == "") {
            if (msgBox.hasClass(options.Tip)) {
                msgBox.stop(true, true).fadeIn(trime);
            }
            return false;
        } else {
            msgBox.stop(true, true).fadeOut(trime);
            return true;
        }
    });
    msgBox.click(function () {
        thisCheck.mousedown();
        thisCheck.focus();
    });

    function init() {
//        if (!options.isselchk)
//            $(".publicSearch input[type='text']:first").focus().select();
        if (checkValue !== '') {
            msgBox.stop(true, true).fadeOut(trime);
        } else {
            msgBox.stop(true, true).fadeIn(trime);
        }
    }
    init();
};
var clearInput = function (options) {
    var thisCheck = options.obj_input; //当前input
    var msgBox = options.msgBox; //当前提示标签
    checkValue = thisCheck.val();
    var trime = options.trime !== undefined ? options.trime : 100;
    thisCheck.bind("blur keyup", function () {
        if ($(this).val() == "") {
            if (msgBox.hasClass(options.Tip)) {
                msgBox.stop(true, true).fadeOut(trime);
            }
        } else {
            msgBox.stop(true, true).fadeIn(trime);
        }
    });
    msgBox.click(function () {
        thisCheck.focus();
        msgBox.stop(true, true).fadeOut(trime);
        thisCheck.val("");
    });

    function init() {
        $("input[type='text']:first").focus().select();
        if (checkValue !== '') {
            msgBox.stop(true, true).fadeIn(trime);
        } else {
            msgBox.stop(true, true).fadeOut(trime);
        }
    }
    init();
};
var _select = function (settings) {
    settings.select.bind("selectstart", function () { return false; }); //禁用选中IE，其他-moz-user-select:none;
    settings.select.click(function (e) {
        if (settings.parents)
            if (settings.parents.length > 1) settings.parents.find("ul").not(settings.options).hide(); //如果有多个select隐藏非当前的所有相关ul
    if (settings.options.is(":visible")) showType(0);
    else showType(1);
    if (settings.selectcallback) settings.selectcallback(this);
    e.stopPropagation();
});
settings.option.click(function () {
    settings.select.text($(this).text());
    settings.select.next().val($(this).attr("val"));
    showType(0);
    if (settings.callback) settings.callback(this);
});
$(document).click(function () {
    if (settings.options.is(":visible")) showType(0);
});

function showType(flage) {
    switch (settings.t) {
        case "slide":
            if (flage) {
                settings.options.slideDown(50);
                settings.select.siblings("span").addClass("corUp");
            }
            else {
                settings.options.slideUp(50);
                settings.select.siblings("span").removeClass("corUp");
            }
            break;
        case "fade":
            if (flage) {
                settings.options.fadeIn(200);
                settings.select.siblings("span").addClass("corUp");
            }
            else {
                settings.options.fadeOut(200);
                settings.select.siblings("span").removeClass("corUp");
            }
            break;
        default:
            if (flage) {
                settings.options.show();
                settings.select.siblings("span").addClass("corUp");
            }
            else {
                settings.options.hide();
                settings.select.siblings("span").removeClass("corUp");
            }
            break;
    }
}
};

/**
* 移除数组中的空元素
* @param {array} 数组
* @returns {narray} 新数组
* */
Array.prototype.trimArray = function () {
    var array = this;
    var narray = [];
    for (var i = 0; i < array.length; i++)

        if (array[i]) {
            if (typeof array[i] == "string") {
                if (array[i].trim())
                    narray.push(array[i]);
            } else {
                narray.push(array[i]);
            }
        }
    return narray;
}; 
/**
* 移除数组中的指定元素
* @param {elm} 指定元素值
* @returns {narray} 新数组
* */
Array.prototype.remove = function (elm) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == elm) {
            this[i] = '';
            break;
        }
    }
    return this.trimArray(); //清除空元素
};
/**
* 去除数组中重复的元素
* @param {isStrict} 是否严格模式
* ['1',1] isStric=true 返回1，否则，返回1,1
* @returns {Array}
* */
Array.prototype.unique = function (isStrict) {
    if (!this.length) return [];
    if (this.length < 2) return [this[0]] || [];
    var tempObj = {},
			newArr = [];
    for (var i = 0; i < this.length; i++) {
        var v = this[i];
        var condition = isStrict ? (typeof tempObj[v] != typeof v) : false;
        if ((typeof tempObj[v] == "undefined") || condition) {
            tempObj[v] = v;
            newArr.push(v);
        }
    }
    return newArr;
};
/**
* 统计元素在数组中出现的次数
* @param {array} 数组
* ['1',1] isStric=true 返回1，否则，返回1,1
* @returns {Array} 返回一个二维数组::["元素名","出现的次数"]
* */
Array.prototype.sameCount = function () {
    var res = [];
    var ary = this;
    ary.sort();
    for (var i = 0; i < ary.length; ) {
        var count = 0;
        for (var j = i; j < ary.length; j++) {
            if (ary[i] == ary[j]) {
                count++;
            }
        }
        res.push([ary[i], count]);
        i += count;
    }
    return res;
}
var getClassName = function (name) {
    try {
        return document.getElementsByClassName(name);
    } catch (e) {
        var tags = document.getElementsByTagName('*') || document.all;
        var els = [];
        for (var i = 0; i < tags.length; i++) {
            if (tags[i].className && typeof tags[i].className == "string") {
                var cs = [];
                try {
                    cs = tags[i].className.split(' ');
                } catch (e) {
                    break;
                }
                for (var j = 0; j < cs.length; j++) {
                    if (name == cs[j]) {
                        els.push(tags[i]);
                        break
                    }
                }
            }
        }
        return els
    }
};
var byClass = getClassName;

function gopage(page, form) {
    $("#pagelist a.item").click(function () {
        $("#" + page).val($(this).attr("val"));
        //jq中submit()提交表单需加setTimeout兼容IE6，原因未知
        setTimeout(function () {
            $("#" + form).submit();
        }, 0);
    });
    $("#pageok").click(function () {
        if ($("#pn").val()) {
            $("#" + page).val($("#pn").val());
            setTimeout(function () {
                $("#" + form).submit();
            }, 0);
        }
    });
}
/**
* 格式化时间函数
* @param {format} 时间显示格式
*/
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};
String.prototype.format = function (args) {
    if (arguments.length > 0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] == undefined) {
                    result = result.replace(reg, arguments[i]);
                }
                else {
                    var reg = new RegExp('\\{' + i + '\\}', 'gm'); ;
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    }
    else {
        return this;
    }
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '')
};
function StringBuilder() {
    this.__values = new Array();
};
StringBuilder.prototype.appendLine = function (v) {
    this.__values.push(v);
}
StringBuilder.prototype.toString = function () {
    return this.__values.join('');
}
Number.prototype.toFixed = function (d) {
    var s = this + "";
    if (!d) d = 0;
    if (s.indexOf(".") == -1) s += ".";
    s += new Array(d + 1).join("0");
    if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0," + (d + 1) + "})?)\\d*$").test(s)) {
        var s = "0" + RegExp.$2, pm = RegExp.$1, a = RegExp.$3.length, b = true;
        if (a == d + 2) {
            a = s.match(/\d/g);
            if (parseInt(a[a.length - 1]) > 4) {
                for (var i = a.length - 2; i >= 0; i--) {
                    a[i] = parseInt(a[i]) + 1;
                    if (a[i] == 10) {
                        a[i] = 0;
                        b = i != 1;
                    }
                    else break;
                }
            }
            s = a.join("").replace(new RegExp("(\\d+)(\\d{" + d + "})\\d$"), "$1.$2");
        }
        if (b) s = s.substr(1);
        return (pm + s).replace(/\.$/, "");
    }
    return this + "";
}
//限制只能键入数字,flage:是否验证‘.’传入则不可以输入‘.’
function entNumber(e, flage) {
    e = e || window.event;
    var keyCode = e.keyCode || e.which;
    if (!(keyCode == 46) && !(keyCode == 8) && !(keyCode == 37) && !(keyCode == 39) && !(keyCode == 17)  && !(keyCode == 13) && ctrlKey()) {
        if (!((keyCode >= 48 && keyCode <= 57) || (keyCode == 110 || keyCode == 190) || keyCode == 9 || (keyCode >= 96 && keyCode <= 105))) stopDefault(e);
        if (flage) if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 9 || (keyCode >= 96 && keyCode <= 105))) stopDefault(e);
    }
    //ctrl+c/v/a/x/z
    function ctrlKey() {
        return !(e.ctrlKey && keyCode == 67) && !(e.ctrlKey && keyCode == 86) && !(e.ctrlKey && keyCode == 65) && !(e.ctrlKey && keyCode == 88) && !(e.ctrlKey && keyCode == 90)
    }
}
function getKeyCode(e) {
    e = e || window.event;
    return e.keyCode || e.which;
}
//阻止浏览器的默认行为
function stopDefault(e) {
    e = e || window.event;
    if (e.preventDefault) e.preventDefault(); //其他浏览器
    else e.returnValue = false; //IE浏览器
}
/**
* 阻止事件(包括冒泡和默认行为)
* */
function stopEvent(e) {
    e = e || window.event;
    if (e.preventDefault) { //其他浏览器
        e.preventDefault();
        e.stopPropagation();
    } else { //IE浏览器
        e.returnValue = false;
        e.cancelBubble = true;
    }
};
function getid(id) {
    return (typeof id == 'string') ? document.getElementById(id) : id
};
function getcookie(name) {
    var cookie_start = document.cookie.indexOf(name);
    var cookie_end = document.cookie.indexOf(";", cookie_start);
    return cookie_start == -1 ? '' : unescape(document.cookie.substring(cookie_start + name.length + 1, (cookie_end > cookie_start ? cookie_end : document.cookie.length)));
}
function setcookie(cookieName, cookieValue) {
    var expires = new Date();
    var now = parseInt(expires.getTime());
    var et = (86400 - expires.getHours() * 3600 - expires.getMinutes() * 60 - expires.getSeconds());
    expires.setTime(now + 1000000 * (et - expires.getTimezoneOffset() * 60));
    document.cookie = escape(cookieName) + "=" + escape(cookieValue) + ";expires=" + expires.toGMTString() + "; path=/";
}
function IsURL(strUrl) {
    //var regular = /^\b(((https?|ftp):\/\/)?[-a-z0-9]+(\.[-a-z0-9]+)*\.(?:com|edu|gov|int|mil|net|org|biz|info|name|museum|asia|coop|red|aero|xyz|top|ren|club|wang|[a-z][a-z]|((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d))\b(\/[-a-z0-9_:\@&?=+,.!\/~%\$]*)?)$/i
    var regular = /^\b(((https?|ftp):\/\/)?[-a-z0-9]+(\.[-a-z0-9]+)*\.([a-z0-9]+)(\/[-a-z0-9_:\@&?=+,.!\/~%\$]*)?)$/i
    if (regular.test(strUrl)) {
        return true;
    }
    else {
        return false;
    }
}

//url参数分解
String.prototype.queryString = function () {
    var raw = this.toString();
    if (raw.length == 0)
        return null;
    var arr = [];
    var collection = raw.split('&');
    for (var i = 0; i < collection.length; i++) {
        var o = {};
        var tmp = collection[i].split('=');
        o.k = tmp[0];
        o.v = tmp[1];
        arr.push(o);
    }
    return arr;
}
//获取url参数值
String.prototype.queryStringValue = function (keyName) {
    var url = this.toString();
    if (url.length == 0)
        return null;
    var collection = url.split('&');
    for (var i = 0; i < collection.length; i++) {
        var tmp = collection[i].split('=');
        if (tmp.length < 2)
            continue;
        if (tmp[0].toUpperCase() == keyName.toUpperCase())
            return tmp[1];
    }
    return null;
}
function Init() {
    var currentInput = null;
    var iswords = false;
    var showtype = "url";
    var trime;
    var tipTxt;
    var inputSave = {
        OnKeyup: function (e) {
            var obj = e.target ? e.target : e.srcElement;
            if (/\s+/.test(obj.value))
                $(obj).val($(obj).val().replace(/\s+/g, ''));
            setTimeout(function () { inputSave.addInput('' + obj.id + '', this) }, 200);
        },
        BoxShowUrls: function (e) {
            if (currentInput) {
                if ($(currentInput).siblings(".CentHid").hasClass("col-red"))
                    $(currentInput).siblings(".CentHid").removeClass("col-red")
            }
            inputSave.BoxShowTime(e, "url");
        },
        BoxHide: function (e) {
            clearTimeout(trime);
            trime = setTimeout(function () {
                if (getid("ToolBox")) {
                    getid("ToolBox").style.display = 'none';
                    var tags = document.getElementsByTagName('input');
                    for (var i = 0; i < tags.length; i++) {
                        if (tags[i].getAttribute('f') == '1') {
                            tags[i].setAttribute('f', 0)
                        }
                    }
                }
            }, 200);
        },
        OnPaste: function (e) {
            var obj = e.target ? e.target : e.srcElement;
            setTimeout(function () { inputSave.MoveHttp('' + obj.id + '') }, 200);
        },
        BoxShowWords: function (e) {
            inputSave.BoxShowTime(e, "words");
        },
        BoxShowCname: function (e) {
            inputSave.BoxShowTime(e, "cname");
        },
        BoxShowIcpcode: function (e) {
            inputSave.BoxShowTime(e, "icpcode");
        },
        BoxShowTime: function (e, b) {
            if (getid("ToolBox"))
                getid("ToolBox").style.display = 'none';
            clearTimeout(trime);
            trime = setTimeout(function () {
                showtype = b;
                if (tipTxt) $(currentInput).siblings(".CentHid").removeClass("col-hint").text(tipTxt);
                inputSave.BoxShow(e);
            }, 200);
        },
        BoxShow: function (e) {
            var input = e;
            if (!input.id) {
                input = e.target ? e.target : e.srcElement;
            }
            currentInput = input;
            switch (showtype) {
                case "url":
                    inputSave.FillUrls("toolbox_urls");
                    break;
                case "words":
                    inputSave.FillUrls("toolbox_words");
                    break;
                case "cname":
                    inputSave.FillUrls("toolbox_cname");
                    break;
                case "icpcode":
                    inputSave.FillUrls("toolbox_icpcode");
                    break;
            }
            var box = getid("ToolBox");
            if (box.style.display == 'block' && currentInput.id == input.id) {
                return;
            }
            input.setAttribute("f", "1");
            var o_span = ($(input).parent())[0];
            box.style.left = inputSave.getOffsetLeft(o_span) + 'px';
            box.style.top = (inputSave.getOffsetTop(o_span) + (o_span.offsetHeight - 1)) + 'px';
            box.style.width = o_span.offsetWidth - 2 + 'px';
            box.style.display = 'block';
        },
        FillUrls: function (cookieName) {
            var urls = getcookie(cookieName);
            var html = "";
            switch (showtype) {
                case "url":
                    html = "<li><a href='javascript:;' class='add'>＋保存输入框的网址</a></li>";
                    break;
                case "words":
                    html = "<li><a href='javascript:;' class='add'>＋保存输入框的关键字</a></li>";
                    break;
                case "cname":
                    html = "<li><a href='javascript:;' class='add'>＋保存输入框的公司名称</a></li>";
                    break;
                case "icpcode":
                    html = "<li><a href='javascript:;' class='add'>＋保存输入框的备案编号</a></li>";
                    break;
            }
            if (urls != '' && urls != ';') {
                var urllist = urls.split('|');
                for (var i = 0; i < urllist.length; i++) {
                    var textval = urllist[i];
                    html += "<li><a href=\"javascript:\" class='setval'><input type='button' value='删除' class='del' v=" + textval + " />" + textval + "</a></li>";
                }
            }
            else {
                html += "<li>没有记录</li>"
            }
            getid("xlist").innerHTML = html;
            $("#ToolBox .add").click(inputSave.ToolBoxAdd);
            $("#ToolBox .setval").click(function () {
                inputSave.InputSetValue($(this).text());
                $("form .jstrime").remove();
                setTimeout(function () {
                    $(".WrapHid").each(function () {
                        checkFocus({
                            obj_input: $(this),
                            msgBox: $(this).siblings(".CentHid"),
                            Tip: "CentHid",
                            isselchk: true
                        });
                    });
                    $(currentInput).removeClass("col-hint");
                }, 200);
            });
            $("#ToolBox .del").click(function (e) {
                stopEvent(e);
                inputSave.ToolBoxDeleteValue($(this).attr("v"));
                $(".WrapHid").each(function () {
                    checkFocus({
                        obj_input: $(this),
                        msgBox: $(this).siblings(".CentHid"),
                        Tip: "CentHid",
                        isselchk: true
                    });
                });
            });
        },
        getOffsetTop: function (el, p) {
            var _t = el.offsetTop;
            while (el = el.offsetParent) {
                if (el == p) break;
                _t += el.offsetTop
            }
            return _t
        },
        getOffsetLeft: function (el, p) {
            var _l = el.offsetLeft;
            while (el = el.offsetParent) {
                if (el == p) break;
                _l += el.offsetLeft
            }
            return _l
        },
        ToolBoxAdd: function () {
            inputSave.BoxHide();
            var val = currentInput.value.trim();
            //col-red
            if (val == '') {
                //alert("不能添加空值。");
                tipTxt = $(currentInput).siblings(".CentHid").text();
                $(currentInput).siblings(".CentHid").addClass("col-hint").text("不能添加空值");
                return;
            }
            if (showtype == "url") {
                if (!IsURL(val)) {
                    //alert("输入网址不正确!")
                    tipTxt = $(currentInput).siblings(".CentHid").text();
                    currentInput.value = '';
                    $(currentInput).siblings(".CentHid").addClass("col-hint").text("输入网址不正确").show();
                    return;
                }
            }
            if (location.host.indexOf("mobile") >= 0 || location.host.indexOf("index") >= 0 || location.host.indexOf("wapseo") >= 0)//如果是mobile.chinaz.com
                $.ajax({ type: "POST", url: "/fit/toobox", data: { "addval": escape(val), "showtype": showtype} });
            else
                $.ajax({ type: "POST", url: "/ajaxsync.aspx", data: 'at=toolbox&showtype=' + showtype + '&addval=' + escape(val) });
        },
        addInput: function (id, _this) {
            var obj = getid(id);
            if (obj.value.indexOf('。') > 0) {
                obj.value = obj.value.replace('。', '.');
            }
            this.value = obj.value;
        },
        MoveHttp: function (id) {
            var val = getid(id).value;
            val = val.replace(/http(s)?:\/\//, "");
            var temp = val.split('/');
            if (temp.length <= 2) {
                if (val[val.length - 1] == '/') {
                    val = val.substring(0, val.length - 1);
                }
            }
            getid(id).value = val;
        },
        ToolBoxDeleteValue: function (val) {
            inputSave.BoxHide();
            if (location.host.indexOf("mobile") >= 0 || location.host.indexOf("index") >= 0 || location.host.indexOf("wapseo") >= 0)//如果是mobile.chinaz.com
                $.ajax({ type: "POST", url: "/fit/toobox", data: { "delval": escape(val), "showtype": showtype} });
            else
                $.ajax({ type: "POST", url: "/ajaxsync.aspx", data: 'at=toolbox&showtype=' + showtype + '&delval=' + escape(val) });
        },
        InputSetValue: function (val) {
            setTimeout(function () {
                var obj = currentInput;
                obj.value = val;
                if ($("input[name='page']")) $("input[name='page']").val(1);
                if (obj.getAttribute('url') == 'true') {
                    var tags = document.getElementsByTagName('input');
                    for (var i = 0; i < tags.length; i++) {
                        if (tags[i].getAttribute('url') == 'true' && tags[i] != obj && tags[i].getAttribute('f') == '1') {
                            tags[i].value = val;
                        }
                    }
                }
            }, 200);
            inputSave.BoxHide();
        }
    }
    $("input[url='true']").bind({ keyup: inputSave.OnKeyup, mousedown: inputSave.BoxShowUrls, mouseout: inputSave.BoxHide, paste: inputSave.OnPaste });
    $("input[words='true']").bind({ mousedown: inputSave.BoxShowWords, mouseout: inputSave.BoxHide });
    $("input[cname='true']").bind({ mousedown: inputSave.BoxShowCname, mouseout: inputSave.BoxHide });
    $("input[icpcode='true']").bind({ mousedown: inputSave.BoxShowIcpcode, mouseout: inputSave.BoxHide });
    $("#ToolBox").mouseout(inputSave.BoxHide).mouseover(function () { clearTimeout(trime); $(this).show(); });
}


//查询记录
function getLochis() {
    menuHover($("#selecthis"), $("#selecthis-box"), function () {
        $("#selecthis i").addClass("cnerCurt").removeClass("corner");
    }, function () {
        $("#selecthis i").removeClass("cnerCurt").addClass("corner");
    });
    var ocookie;
    $("#selecthis").hover(function () {
        var winWidth = window.innerWidth || document.documentElement && document.documentElement.clientWidth || 0;
        if (winWidth < 1050) {
            $("#selecthis-box").css({ left: "auto", right: "20px" });
            $("#selecthis-box .BomCor-arrow").css({ left: "auto", right: "6px" });
        } else {
            $("#selecthis-box").css({ left: "20px", right: "auto" });
            $("#selecthis-box .BomCor-arrow").css({ left: "6px", right: "auto" });
        }
        $("#selecthis i").addClass("cnerCurt").removeClass("corner");
        var cookie = getcookie("qHistory");
        if (cookie == ocookie) return;
        ocookie = cookie;
        var url;
        if (location.host.indexOf("mobile") >= 0 || location.host.indexOf("index") >= 0 || location.host.indexOf("wapseo") >= 0) {
            url = "/fit/GetQueryHistory?val=";
        } else {
            url = "/ajaxsync.aspx?at=qh&val=";
        }
        $.ajax({
            type: "get",
            url: url + encodeURIComponent(cookie),
            beforeSend: function () {
                $(".BomreList").html("<div class=\"BorWrapa tc\"><img src=\"" + imgurlbase + "/public/spinner.gif\" class=\"mt10\" /></div>");
            },
            success: function (data) {
                if (data == 0) $(".BomreList").html("<div class=\"BorWrapa tc\" style=\"color:#e60012\">无记录</div>");
                else {
                    $(".BomreList").html(data + "<a href=\"javascript:\" class=\"BomreMore BorWrapa\" id=\"jsclearall\"><i></i>清空记录</a>");
                    bindClick();
                }
            }
        });
    }, function () {
        $("#selecthis i").removeClass("cnerCurt").addClass("corner");
    });
}
function bindClick() {
    $("i.jsclear").click(function () {
        var _this = this;
        var url;
        if (location.host.indexOf("mobile") >= 0 || location.host.indexOf("index") >= 0 || location.host.indexOf("wapseo") >= 0) {
            url = "/fit/clearqh?val=";
        } else {
            url = "/ajaxsync.aspx?at=clearqh&val=";
        }
        $.post(url + encodeURIComponent($(this).attr("v")), function (data) {
            if (data == 1) {
                var _parents = $(_this).parents(".BorWrapa");
                _parents.fadeOut(200, function () {
                    _parents.remove();
                    if ($("i.jsclear").length == 0) $("#selecthis-box").hide();
                });
            }
        });
    });
    $("#jsclearall").click(function () {
        var url;
        if (location.host.indexOf("mobile") >= 0 || location.host.indexOf("index") >= 0 || location.host.indexOf("wapseo") >= 0) {
            url = "/fit/clearqh?val=all";
        } else {
            url = "/ajaxsync.aspx?at=clearqh&val=all";
        }
        $.post(url, function (data) {
            if (data == 1) $("#selecthis-box").fadeOut(200);
        });
    });
}

//滚动事件
var boxScroll = function (options) {
    var settings = {
        _scroll: $("#scroll"), //滚动的div
        _width: 0,
        _height: 0,
        _top: 0, //定位top
        _left: 0, //定位left
        endElm: "", //结束id
        ow: 10, //padding或margin的值，用来准确定位
        isresize: false,
        callback: function () { }
    };
    if (options)
        $.extend(settings, options);
    var _scroll = settings._scroll;
    _scrollfn();
    $(window).scroll(function () {
        _scrollfn();
    });
    if (settings.isresize) {
        $(window).resize(function () {
            _scrollfn();
        });
    }
    function _scrollfn() {
        var _scrolltop = $(window).scrollTop();
        var _postiton = "fixed"; //默认
        if (sys.ie <= 6)
            _postiton = "absolute";
        if (settings.endElm) {
            var endTop = settings.endElm.offset().top; //结束的TOP
            if (_scrolltop <= settings._top) {
                _scroll.css({
                    position: "static"
                });
            }
            else if (_scrolltop + settings._height >= endTop) {
                _scroll.css({
                    position: "absolute",
                    left: settings._left + "px",
                    top: (endTop - settings._height - settings.ow) + "px"
                });
            }
            else {
                _scroll.css({
                    position: _postiton,
                    left: settings._left + "px",
                    top: sys.ie <= 6 ? ((_scrolltop + settings.ow) + "px") : "10px"
                });
            }
        } else {
            settings._winWidth = window.innerWidth || document.documentElement && document.documentElement.clientWidth || 0;
            settings._winHeight = window.innerHeight || document.documentElement && document.documentElement.clientHeight || 0;
            var ob = $('.Map-navbar').length ? $('.Map-navbar') : $(".navfixd");
            if (!ob.length) return;
            var l;
            if (settings._winWidth <= ob.width() + 75) {
                _scroll.css({
                    position: _postiton,
                    left: "auto",
                    right:"0",
                    top: sys.ie <= 6 ? ((_scrolltop + (settings._winHeight * 0.9) - settings._height) + "px") : "initial"
                }).show();
            } else {
                _scroll.css({
                    position: _postiton,
                    left: ob.offset().left + ob.width(),
                    top: sys.ie <= 6 ? ((_scrolltop + (settings._winHeight * 0.9) - settings._height) + "px") : "initial"
                }).show();
            }
            if (settings.callback) settings.callback(settings, _scrolltop);
        }
    }
};
; (function () {
    window.sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? sys.ie = s[1] :
	(s = ua.match(/firefox\/([\d.]+)/)) ? sys.firefox = s[1] :
	(s = ua.match(/chrome\/([\d.]+)/)) ? sys.chrome = s[1] :
	(s = ua.match(/opera\/.*version\/([\d.]+)/)) ? sys.opera = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? sys.safari = s[1] : 0;

    if (/webkit/.test(ua)) sys.webkit = ua.match(/webkit\/([\d.]+)/)[1];
})();
/*  内容溢出省略替代，num最大长度  */
; (function ($) {
    $.fn.wordLimit = function (num) {
        this.each(function () {
            if (!num) {
                var copyThis = $(this.cloneNode(true)).hide().css({
                    'position': 'absolute',
                    'width': 'auto',
                    'overflow': 'visible'
                });
                $(this).after(copyThis);
                if (copyThis.width() > $(this).width()) {
                    $(this).text($(this).text().substring(0, $(this).text().length - 4));
                    $(this).html($(this).html() + '...');
                    copyThis.remove();
                    $(this).wordLimit();
                } else {
                    copyThis.remove();
                    return;
                }
            } else {
                var maxwidth = num;
                if ($(this).text().length > maxwidth) {
                    $(this).text($(this).text().substring(0, maxwidth));
                    $(this).html($(this).html() + '...');
                }
            }
        });
    }
})(jQuery);

function loadScript(options) {
    var url = options.url, elms = options.elms, callback = options.callback;
    var script = document.createElement("script");
    script.type = "text/javascript";
    if (script.readyState) {
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
                if (callback) callback();
            }
        };
    } else {
        script.onload = function () {
            if (callback) callback();
        };
    }
    script.src = url;
    elms.appendChild(script)
}

//过滤HTML标签
String.prototype.removeHtmlTab = function () {
    return this.replace(/<[^<>]+?>/g, '');
}
//HTML标签字符转换成转意符
String.prototype.html2Escape = function () {
    return this.replace(/[<>&"]/g, function (c) { return { '<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c]; });
}
//转意符换成HTML标签
String.prototype.escape2Html = function () {
    var arrEntities = { 'lt': '<', 'gt': '>', 'nbsp': ' ', 'amp': '&', 'quot': '"' };
    return this.replace(/&(lt|gt|nbsp|amp|quot);/ig, function (all, t) { return arrEntities[t]; });
}
//&nbsp;转成空格
String.prototype.nbsp2Space = function () {
    var arrEntities = { 'nbsp': ' ' };
    return this.replace(/&(nbsp);/ig, function (all, t) { return arrEntities[t] })
}
//回车转为br标签
String.prototype.return2Br = function () {
    return this.replace(/\r?\n/g, "<br />");
};

/*********Tabs***********/
; (function ($) {
    $.fn.tabs = function (settings) {
        var $control = $(settings.control);
        var childTag = settings.childTag;
        var className = settings.className;
        var eventName = settings.eventName;
        this.each(function () {
            var _this = $(this);
            var group = _this.attr("tabs");
            _this.find(childTag).bind(eventName, function (e) {
                var _index = $(this).index();
                _this.find(childTag).removeClass(className);
                $(this).addClass(className);

                $control.each(function () {
                    var cgroup = $(this).attr("tabs-control");
                    if (group == cgroup) {
                        $(this).find(">div").hide();
                        $(this).find(">div").eq(_index).show();
                    }
                });
                if (settings.callback) settings.callback(_this);
            });
        });
    }
})(jQuery);

/********************/
function Drag(obj, mover, parentElm) {
    this.obj = obj;
    this.mover = mover;
    this.ht = mover || obj;
    this.parentElm = parentElm;
}
Drag.prototype.mouseup = function (_this, e, callback) {
    e = e || window.event;
    if (_this.obj.drag) {
        _this.obj.drag = 0;
        if (sys.ie) _this.ht.releaseCapture();
        else {
            window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
            e.preventDefault();
        }
        document.body.onselectstart = null;
    }
    if (callback) callback();
}
Drag.prototype.mousemove = function (_this, e, callback) {
    if (!_this.obj.drag) return;
    e = e || window.event;
    var l, t;
    if (_this.parentElm) {
        var pos = $(_this.parentElm).position();
        l = e.clientX - _this.obj._x - pos.left;
        t = e.clientY - _this.obj._y - pos.top;
    } else {
        l = e.clientX - _this.obj._x;
        t = e.clientY - _this.obj._y;
    }
    if (l < 0) l = 0;
    if (t < 0) t = 0;
    var inner;
    if (_this.parentElm) inner = { width: _this.parentElm.offsetWidth, height: _this.parentElm.offsetHeight };
    else inner = getInner();
    if (l + _this.obj.offsetWidth >= inner.width) l = inner.width - _this.obj.offsetWidth;
    if (t + _this.obj.offsetHeight >= inner.height) t = inner.height - _this.obj.offsetHeight;
    //console.log($(_this.parentElm).position().top);
    $(_this.obj).css({ left: l + "px", top: t + "px" });
    if (callback) callback({ left: l, top: t });
}
Drag.prototype.mousedown = function (_this, e, callback) {
    e = e || window.event;
    if (sys.ie) _this.ht.setCapture();
    else {
        window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
        e.preventDefault();
    }
    var l = getLeft(_this.obj), t = getTop(_this.obj);
    _this.obj._x = e.clientX - l;
    _this.obj._y = e.clientY - t;
    _this.obj.drag = 1;
    document.body.onselectstart = function () { return false; };
    if (callback) callback({ left: e.clientX, top: e.clientY });
}
Drag.prototype.init = function (settings) {
    var _options = {
        isCenter:true,//是否
        downCallback: null,
        moveCallback: null,
        upCallback: null
    };
    _options = $.extend(_options,settings);
    var _this = this;
    if (_options.isCenter) center(_this.obj);
    $(_this.ht).on("mousedown", function (e) { _this.mousedown(_this, e, _options.downCallback); });
    if (!sys.ie)
        _this.ht = document.body;
    $(_this.ht).on("mousemove", function (e) { _this.mousemove(_this, e, _options.moveCallback); });
    $(_this.ht).on("mouseup", function (e) { _this.mouseup(_this, e, _options.upCallback); });
    $(window).resize(function () { if (_options.isCenter) center(_this.obj); });
}
var getInner = function () {
    return {
        width: window.innerWidth || document.documentElement && document.documentElement.clientWidth || 0,
        height: window.innerHeight || document.documentElement && document.documentElement.clientHeight || 0
    }
}
var center = function (elm) {
    var inner = getInner();
    elm.style.left = ((inner.width - elm.clientWidth) / 2) + "px";
    elm.style.top = ((inner.height - elm.clientHeight) / 2) + "px";
}
var getTop = function (e) {
    var offset = e.offsetTop;
    if (e.offsetParent != null) offset += getTop(e.offsetParent);
    return offset;
}
var getLeft = function (e) {
    var offset = e.offsetLeft;
    if (e.offsetParent != null) offset += getLeft(e.offsetParent);
    return offset;
}


 ; (function ($) {
     $.fn.serializeObject = function () {
         var o = {};
         var a = this.serializeArray();
         $.each(a, function () {
             if (o[this.name]) {
                 if (!o[this.name].push) {
                     o[this.name] = [o[this.name]];
                 }
                 o[this.name].push(this.value || '');
             } else {
                 o[this.name] = this.value || '';
             }
         });
         return o;
     }
 })(jQuery);