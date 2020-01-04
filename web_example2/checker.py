#!/usr/bin/env python
# -*- coding:utf8 -*-
import requests
import base64
import random
import string


def check(target_ip, target_port):
    res = requests.get('http://{}:{}'.format(target_ip,target_port))
    if res.status_code == 200:
        return True
    return False



if __name__ == '__main__':
    target_ip, target_port = '127.0.0.1', 8801
    check(target_ip, target_port)
