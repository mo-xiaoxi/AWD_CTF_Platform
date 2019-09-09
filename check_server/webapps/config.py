# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://172.17.0.6:8000/adm1n_ap1'
user_count = 4  # 数量
round_time = 5 * 60
secret_key = 'a2a457f4202a13d8978521a71a1cec07'
flag_key = 'cc349d7b895a4ddd02b6b53dc4fcbb48'
lib = {
"user01": "172.17.0.2",
"user02": "172.17.0.3",
"user03": "172.17.0.4",
"user04": "172.17.0.5",
}
check_port = 8080
