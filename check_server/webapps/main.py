import importlib
import hashlib
import requests
import time
import datetime
from config import *

check = importlib.import_module('check_scripts.checker').check


def check_one(user_name):
    check_ip = lib[user_name]
    return check(check_ip, check_port)


def update_flag_server(user_name, flag, round_index):
    data = {'secret_key': secret_key, 'user_name': user_name, 'action': 'update flag', 'data': flag,
            "round_index": round_index}
    try:
        res = requests.post(flag_server, data=data)
        # print("Status code:   %i" % res.status_code)
        # print("Response body: %s" % res.content)
        if res.status_code == 200 and b'succ' in res.content:
            return True
    except Exception as e:
        print(e)
    return False


def update_target_server(user_name, flag):
    target_url = 'http://' + lib[user_name] + ':9999'
    try:
        res = requests.get(target_url + '/' + flag_key + '/' + flag)
        # print(target_url + '/' + flag_key + '/' + flag)
        # print("Status code:   %i" % res.status_code)
        # print("Response body: %s" % res.content)
        if res.status_code == 200 and b'succ' in res.content:
            return True
    except Exception as e:
        print(e)
    return False


def update_status(user_name, round_index, run):
    data = {'secret_key': secret_key, 'user_name': user_name, 'action': 'status', 'data': run,
            'round_index': round_index}
    try:
        res = requests.post(flag_server, data=data)
        # print("Status code:   %i" % res.status_code)
        # print("Response body: %s" % res.content)
        if res.status_code == 200 and b'succ' in res.content:
            return True
    except Exception as e:
        print(e)
    return False


def run_one(round_index):
    for i in range(1, user_count + 1):
        user_name = 'user' + str(i).zfill(2)
        tmp = (user_name + str(round_index) + str(int(time.time()) / round_time)).encode('utf-8')
        flag = hashlib.md5(tmp).hexdigest()
        if update_flag_server(user_name, flag, round_index):
            print('[+] Flag_server: update {} with flag {}, succ'.format(user_name, flag))
        else:
            print('[-] Flag_server: update {} with flag {}, error!!'.format(user_name, flag))
        if update_target_server(user_name, flag):
            print('[+] Target_server: update {},{} with flag {}, succ'.format(user_name, lib[user_name], flag))
        else:
            print('[-] Target_server: update {} with flag {}, error!!'.format(user_name, flag))
        if check_one(user_name):
            run = 1
            update_status(user_name, round_index, run)
            print("[+] Check_server {},{} run succ !".format(user_name, lib[user_name]))
        else:
            print("[-] Check_server {},{} run error !".format(user_name, lib[user_name]))
            run = 0
            update_status(user_name, round_index, run)


if __name__ == '__main__':
    print("[+] Starting checking framework...")
    print("[+] Round time : %s seconds..." % round_time)
    while True:
        start = time.time()
        run_one(round_index)
        round_index += 1
        print("[+] This round checking is finished , waiting for the next round...")
        while True:
            wait_time = start + round_time - time.time()
            if wait_time <= 0:
                break
            print("[+] The next checking is %d seconds later..." % (wait_time))
            time.sleep(1)
