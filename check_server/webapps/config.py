# coding:utf-8
import hashlib

round_index = 1  # 轮次
flag_server = 'http://172.17.0.7:8000/adm1n_ap1'
user_count = 5  # 数量
round_time = 5 * 60
secret_key = 'b5de2983885c7e2792c7b6a04dda9ba6'
flag_key = 'cea46fd6f628e194f9a3daf0db649957'
lib = {
"user01": "172.17.0.2",
"user02": "172.17.0.3",
"user03": "172.17.0.4",
"user04": "172.17.0.5",
"user05": "172.17.0.6",
}
check_port = 80
