#!/usr/bin/env python
# -*- coding:utf8 -*-
import requests
import base64
import random
import string

my_time = ''.join(random.sample(string.ascii_letters + string.digits, 8))


def check(target_ip, target_port):
    target_url = 'http://' + target_ip + ':' + str(target_port) + '/'
    if not index_check(target_url):
        print("[-]: {},index_check error".format(target_url))
        return False
    print("[+]:{},index check succ!".format(target_url))
    if not test_check(target_url):
        print("[-]: {},test_check error".format(target_url))
        return False
    print("[+]:{},test_check check succ!".format(target_url))
    if not login_check(target_url):
        print("[-]: {},login_check error".format(target_url))
        return False
    print("[+]:{},login_check check succ!".format(target_url))
    if not admin_check(target_url):
        print("[-]: {},admin_check error".format(target_url))
        return False
    print("[+]:{},admin_check check succ!".format(target_url))
    if not admin_indec_check(target_url):
        print("[-]: {},admin_indec_check error".format(target_url))
        return False
    print("[+]:{},admin_indec_check check succ!".format(target_url))
    return True

def index_check(target_url):
    res = requests.get(target_url + '/index.php?file=news&cid=1&page=1&test=eval&time=%s' % str(my_time))
    if 'A Travel Agency'.encode() in res.content:
        return True
    return False


def test_check(target_url):
    res = requests.get(target_url + '/contact.php?file=flag&time=%s' % str(my_time))
    if 'info@example.com'.encode() in res.content:
        return True
    return False


def admin_check(target_url):
    data = base64.b64encode(
        'eval($b($c($d($b($c($d($b($c($d($b($c($d("BcHJglAwAADQD2Uo0UsOPUtNR8UYVqkb1RhYcKT2r+975tP9ze/G4hhpcgKyhlHNeFY+VLqnCNUBq55lTggTDCQuMEAPeGsrZK35BnUpXBriUPk9VDxp4pL3x7iYj3YH5nIa0/qxXMRMsvmVjX7vkjjs0YYadh5onm96ALwKbaxC1cZgZt5MxBQAi7XfekgpnF0oRBHRVIaznEZaDjbMBJxLXlnLHEIqhMhPofY0PhV3WPsfvYhn7Prhxzc7tw1NLDh7XuS7O3ODKMbAvU1/vAx1kJDp9n59kK7eA84Sw1WUeZfpZTp9AQ==")))))))))))));'.encode());
    cookies = {"PHPSESSID": "f50fb7948dggefigd9shl1rqg7"}
    res = requests.post(target_url + '/admin/index.php?time=%s' % str(my_time), data=data, cookies=cookies)
    if 'glyphicon glyphicon-envelope'.encode() in res.content:
        return True
    return False


def login_check(target_url):
    session = requests.Session()
    paramsPost = {"button": "SIGN-IN", "password": "mysql", "username": "admin"}
    headers = {"Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
               "Upgrade-Insecure-Requests": "1",
               "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:68.0) Gecko/20100101 Firefox/68.0",
               "Accept-Language": "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
               "Accept-Encoding": "gzip, deflate", "Content-Type": "application/x-www-form-urlencoded"}
    cookies = {"PHPSESSID": "f50fb7948dggefigd9shl1rqg7"}
    response = session.post(target_url + "/login.php", data=paramsPost, headers=headers, cookies=cookies)
    if '<li>admin</li>'.encode() in response.content:
        return True
    return False


def admin_indec_check(target_url):
    session = requests.Session()
    data = 'eval(6666)'
    headers = {"Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
               "Upgrade-Insecure-Requests": "1",
               "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:68.0) Gecko/20100101 Firefox/68.0",
               "Accept-Language": "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
               "Accept-Encoding": "gzip, deflate", "Content-Type": "application/x-www-form-urlencoded"}
    cookies = {"PHPSESSID": "f50fb7948dggefigd9shl1rqg7"}
    response = session.post(target_url + "/index.php/admin/Index/main.html", data=data, headers=headers, cookies=cookies)
    if 'It seems that this command'.encode() in response.content:
        return True
    return False



if __name__ == '__main__':
    target_ip, target_port = '127.0.0.1', 8801
    check(target_ip, target_port)
