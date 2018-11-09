# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://172.17.0.6:8000/adm1n_ap1'
user_count = 4  # 数量
round_time = 5 * 60
secret_key = 'fbb7f76e28ec4c660cb46a35573611bc'
flag_key = '300ef76c4c0bbbe96c5e97a085201b9b'
lib = {
"user01": "172.17.0.2",
"user02": "172.17.0.3",
"user03": "172.17.0.4",
"user04": "172.17.0.5",
}
