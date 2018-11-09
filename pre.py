#coding:utf-8
import os
import sys
import time
import hashlib
import random


def copy_dir(src, dest):
    os.system('mkdir -m 777 -p %s' % (dest))
    os.system('cp -r %s %s' % (src, dest))


def read_data(path):
    with open(path, 'r') as f:
        data = f.read().strip()
    return data


def write_data(path, data=''):
    with open(path, 'w') as f:
        f.write(data)
    return True


def get_docker_sh(num):
    web_out_port = str(8800 + num)
    ssh_port = str(2200 + num)
    team_name = 'team' + str(num).zfill(2)
    data = read_data(team_name + '/docker.sh')
    data = data.replace("{web_out_port}", web_out_port).replace("{ssh_port}", ssh_port).replace("{team_name}",
                                                                                                team_name)
    write_data(team_name + '/docker.sh', data)
    data = read_data(team_name + '/reset_docker.sh')
    data = data.replace("{web_out_port}", web_out_port).replace("{ssh_port}", ssh_port).replace("{team_name}",
                                                                                                team_name)
    write_data(team_name + '/reset_docker.sh', data)
    return True


def generate_pass(teamno):
    salt = 'moxiaoxi123456789'
    passwd = hashlib.md5(salt + str(time.time()) + str(teamno)).hexdigest()[:6]
    return passwd


def generate_key():
    salt = 'moxiaoxi123456789'
    passwd = hashlib.md5(salt + str(time.time())+str(random.random())).hexdigest()
    return passwd


def update_run_sh(num, password):
    team_name = 'team' + str(num).zfill(2)
    data = read_data(team_name + '/run.sh')
    data = data.replace("moxiaoxi666", password)
    write_data(team_name + '/run.sh', data)
    return


def update_flag_key(team_name, flag_key):
    data = read_data(team_name + '/flag.py')
    data = data.replace("you_should_not_guess_the_key", flag_key)
    write_data(team_name + '/flag.py', data)
    return

def update_flag_server_config(secret_key, user_count, hold_hour=24):
    t  = time.time()+ int(hold_hour)*60*60
    end_time = time.strftime("%Y, %m, %d, %H, %M, %S", time.localtime(t)) 
    data = """#coding:utf-8
import hashlib

secret_key = '{}'
flag_score = 100 # 一个flag的分数
Year, month, day, Hour, Minute, Second = {}  # 在此设置比赛结束的时间 年月日时分秒
round_time = 5  # 一轮五分钟
user_count = {} # 用户数量
round_index = 1 # 第一轮
run = 1
fraction = 10000 #初始分数
status = []
score = []
for i in range(1, user_count + 1):
    user_name = 'user' + str(i).zfill(2)
    token = hashlib.md5((user_name +'moxiaoxi7777').encode('utf-8')).hexdigest()
    status.append([user_name, run,round_index])
    score.append([user_name, fraction, token])""".format(secret_key,end_time,user_count)
    write_data('flag_server/webapps/app/config.py',data)
    return 

def update_check_server_config(user_count, secret_key, flag_key):
    lib = "{\n"
    for i in range(1,user_count+1):
        user_name = "user" + str(i).zfill(2)
        lib += """"{}": "172.17.0.{}",\n""".format(user_name, i+1)
    lib +="}"
    flag_server = "172.17.0.{}".format(user_count+2)
    data = """# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://{}:8000/adm1n_ap1'
user_count = {}  # 数量
round_time = 5 * 60
secret_key = '{}'
flag_key = '{}'
lib = {}
""".format(flag_server,user_count,secret_key,flag_key,lib)
    write_data("host.list",lib)
    write_data("check_server/webapps/config.py",data)
    return 

def main():
    dir = sys.argv[1]
    team_number = int(sys.argv[2])
    passwords = ""
    flag_key = generate_key()
    secret_key = generate_key()
    for i in range(1, team_number + 1):
        team_name = 'team' + str(i).zfill(2)
        print "[+] Copy DATA ! {}".format(team_name)
        copy_dir(dir + '/', team_name)
        print "[+] get_docker_sh ! {}".format(team_name)
        get_docker_sh(i)
        print "[+] get pass!  {}".format(team_name)
        password = generate_pass(i)
        passwords += "{}\tctf:{}\tssh:{}\tport:{}\n".format(team_name, password, 2200 + i, 8800 + i)
        update_run_sh(i, password)
        print "[+] update run sh!  {}".format(team_name)
        update_flag_key(team_name, flag_key)
        print "[+] update flag key!  {}".format(team_name)
    update_flag_server_config(secret_key, team_number, hold_hour=24)
    print "[+] update flag server config! "
    update_check_server_config(team_number, secret_key, flag_key)
    print "[+] update check server config! "
    write_data('pass.txt', passwords)


if __name__ == "__main__":
    main()
