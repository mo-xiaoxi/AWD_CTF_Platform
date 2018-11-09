# -*- coding:utf-8 -*-
import requests
import traceback 
import re
from random import Random
import hashlib
import sys
reload(sys)
sys.setdefaultencoding('utf8')

pages = ['js', 'phpcom', 'normaliz', 'md5']


def md5(str):
    m = hashlib.md5()
    m.update(str)
    return m.hexdigest()


def sha1(str):
    m = hashlib.sha1()
    m.update(str)
    return m.hexdigest()


def random_str(randomlength=8):
    str = ''
    chars = 'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789'
    length = len(chars) - 1
    random = Random()
    for i in range(randomlength):
        str += chars[random.randint(0, length)]
    return str


def find_res(c):
    rule = r'''<b class="search-hint CentHid"> (.+?) </b>'''
    # rule = r'form="(.+?)"'
    return re.findall(rule, c, re.S)


def isValidIp(ip):
    if re.match(r"^\s*\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\s*$", ip):
        return True
    return False


def isValidEmail(email):
    if re.match("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$", email):
        return True
    return False


def check_index(base_url, rand_str, s):
    try:
        global pages
        page = pages[ord(rand_str[0]) % 4]
        page2 = pages[(ord(rand_str[0]) + 1) % 4]
        u = base_url + "index.php?page=" + pages[ord(rand_str[0]) % 4]
        # data = {'username': rand_str, 'password': rand_str}
        r = s.get(u, timeout=10, allow_redirects=False)
        c = r.content
        m1 = '''<a href="index.php?page=%s"  class="CHeadcur" >''' % page
        m2 = '''<a href="index.php?page=%s"  class="" >''' % page2
        res1 = m1 in c and m2 in c

    except Exception, e:
        print e
        return False, 'check index page exception'
    return res1, 'index page check'


def check_phpcom(base_url, rand_str, s):
    try:
        u = base_url + "action.php?page=phpcom"
        input_code = '''<?php
require_once("library/common.php");
require_once("library/view.php");
$view_class = new View();
$data = array();
if (isset($_GET['page']))
{
    $data['page'] = filter($_GET['page']);
}
else{
    $data['page'] = 'js';
}
$view_class->echoContent($data['page'], $data);
?>%s''' % rand_str
        data = {'source': input_code, 'res': "", 'page': "phpcom"}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        m1 = '''&lt;?php
require_once(&quot;library/common.php&quot;); require_once(&quot;library/view.php&quot;); $view_class = new View(); $data = array(); if (isset($_GET['page'])) { $data['page'] = filter($_GET['page']); } else{ $data['page'] = 'js'; } $view_class-&gt;echoContent($data['page'], $data); ?&gt;%s''' % rand_str
        res1 = m1 in c
    except Exception, e:
        print e
        return False, 'check phpcom page exception'
    return res1, 'phpcom page check'


def check_md5(base_url, rand_str, s):
    try:
        u = base_url + "action.php?page=phpcom"
        input_code = rand_str
        data = {'source': input_code, 'res': "", 'page': "md5", 'method': 'md5'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        m1 = md5(input_code)
        res1 = m1 in c
        data = {'source': input_code, 'res': "", 'page': "md5", 'method': 'sha1'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        m2 = sha1(input_code)
        res2 = m2 in c
    except Exception, e:
        print e
        return False, 'check md5 page exception'
    return res1 and res2, 'md5 page check'


def check_normaliz(base_url, rand_str, s):
    try:
        u = base_url + "action.php?page=normaliz"
        ip = str(ord(rand_str[0]) % 10)
        for i in xrange(3):
            ip += '.' + str(ord(rand_str[i + 1]) % 10)
        data = {'source': ip, 'res': "", 'page': "normaliz", 'method': '/\\d+\\.\\d+\.\\d+\\.\\d+/'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        ip_res = find_res(c)[-1]
        res1 = isValidIp(ip_res) and (ip_res != ip)
        data = {'source': rand_str, 'res': "", 'page': "normaliz", 'method': '/\\d+\\.\\d+\.\\d+\\.\\d+/'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        ip_res = find_res(c)[-1]
        res2 = (ip_res == rand_str)
        email = rand_str[:4] + '@as.com'
        data = {'source': email, 'res': "", 'page': "normaliz", 'method': '/([0-9A-Za-z\\-_\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)/'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        email_res = find_res(c)[-1]
        res3 = isValidEmail(email_res) and (email_res != email)
        data = {'source': rand_str, 'res': "", 'page': "normaliz", 'method': '/([0-9A-Za-z\\-_\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)/'}
        r = s.post(u, data=data, timeout=10, allow_redirects=False)
        c = r.content
        email_res = find_res(c)[-1]
        res4 = (email_res == rand_str)
    except Exception, e:
        traceback.print_exc()
        print e
        return False, 'check normailz page exception'
    return res1 and res2 and res3 and res4, 'normailz page check'


# check entry point
def check(host, port, base_root):
    base_url = "http://%s:%s%s" % (host, port, base_root)

    s = requests.Session()
    rand_str = random_str()
    # admin service
    # (res, msg) = check_admin(base_url, rand_str, password)

    # if not res:
    #     return False, msg
    # register service
    (res, msg) = check_index(base_url, rand_str, s)
    if not res:
        return False, msg

    (res, msg) = check_phpcom(base_url, rand_str, s)
    if not res:
        return False, msg
    # login service
    (res, msg) = check_md5(base_url, rand_str, s)
    if not res:
        return False, msg
    # upload service
    (res, msg) = check_normaliz(base_url, rand_str, s)

    if not res:
        return False, msg

    return True, 'msg'


if __name__ == '__main__':
    print check(sys.argv[1], "20000", "/")
