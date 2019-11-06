from django.db import models


# Create your models here.

class Flag(models.Model):
    """
    靶机编号，靶机flag，flag入库时间
    """
    user_name = models.CharField(max_length=10)
    flag_num = models.CharField(max_length=50)
    create_time = models.DateTimeField(auto_now_add=True)
    round_index = models.IntegerField()

class Round(models.Model):
    round_index = models.IntegerField()

class Score(models.Model):
    """
    选手编号，选手分数，选手最后一次提交时间，选手的token值
    """
    user_name = models.CharField(max_length=10)
    fraction = models.IntegerField()
    last = models.DateTimeField(auto_now=True)
    token = models.CharField(max_length=50)



class Logs(models.Model):
    """
    选手编号，被攻击者,提交的flag，flag提交时间，信息, 轮次,分数变化
    """
    user_name = models.CharField(max_length=10)
    hacked_name = models.CharField(max_length=10)
    flag_num = models.CharField(max_length=50)
    last = models.DateTimeField(auto_now_add=True)
    info = models.CharField(max_length=50)
    round_index = models.IntegerField()
    result = models.IntegerField()

class Status(models.Model):
    """
    靶机编号，选手编号，服务器是否正常运行
    """
    user_name = models.CharField(max_length=10)
    run = models.IntegerField()
    round_index = models.IntegerField()
    create_time = models.DateTimeField(auto_now_add=True)
