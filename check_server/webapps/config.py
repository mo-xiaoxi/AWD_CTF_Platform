# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://172.17.0.7:8000/adm1n_ap1'
user_count = 5  # 数量
round_time = 5 * 60
secret_key = 'd498a3032f91c4853fe5253d49fea142'
flag_key = 'f3cc19a7d404c9c61fa55a6c6aaa56c5'
lib = {
"user01": "172.17.0.2",
"user02": "172.17.0.3",
"user03": "172.17.0.4",
"user04": "172.17.0.5",
"user05": "172.17.0.6",
}
