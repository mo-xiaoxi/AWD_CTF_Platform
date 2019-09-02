# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://172.17.0.6:8000/adm1n_ap1'
user_count = 4  # 数量
round_time = 5 * 60
secret_key = 'c1cbf6bcce787ef8bb206f0682b2897f'
flag_key = 'b81d2a20d38e4d713373831ee4e43454'
lib = {
"user01": "172.17.0.2",
"user02": "172.17.0.3",
"user03": "172.17.0.4",
"user04": "172.17.0.5",
}
check_port = 8080
