#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 2018/4/18 下午4:38
# @Author  : tudoudou
# @File    : init.py
# @Software: PyCharm

import os
from app.config import *
from app.models import Score,Status,Flag,Logs,Round
from django.core.management.base import BaseCommand, CommandError


class Command(BaseCommand):

    def add_arguments(self, parser):

        parser.add_argument(
            '-n',
            '--name',
            action='store',
            dest='name',
            default='ddd',
            help='name of author.',
        )

    def handle(self, *args, **options):
        try:
            os.system('python3 manage.py makemigrations')
            os.system('python3 manage.py migrate')
            # 清空数据库
            Score.objects.all().delete()
            Status.objects.all().delete()
            Flag.objects.all().delete()
            Logs.objects.all().delete()
            Round.objects.all().delete()
            Round(round_index=round_index).save()
            for i in status:
                Status(
                    user_name=i[0],
                    run=i[1],
                    round_index=i[2]
                ).save()
            for i in score:
                Score(
                    user_name=i[0],
                    fraction=i[1],
                    token=i[2]
                ).save()
            # for i in logs:
            #     Logs(
            #         user_name=i[0],
            #         hacked_name=i[1],
            #         flag_num=i[2],
            #         info=i[3],
            #         round_index=i[4],
            #         result=i[5]
            #     ).save()
            # for i in flags:
            #     Flag(
            #         user_name=i[0],
            #         flag_num=i[1],
            #         round_index=i[2]
            #     ).save()
            self.stdout.write(self.style.SUCCESS('初始化成功，请尽情使用吧 (～o￣▽￣)～o ~。。。'))
        except Exception:
            self.stdout.write(self.style.ERROR('命令执行出错'))
