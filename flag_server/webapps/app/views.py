# coding=utf-8
from django.shortcuts import render
from django.contrib.auth.decorators import login_required
from django.shortcuts import redirect
from django.contrib.auth import login, authenticate, logout
from django.http import HttpResponse, HttpResponseNotFound
from django.db.models import F
from .models import Score, Flag, Logs, Status, Round

from .config import *
import datetime
from django.db.models import Q
import hashlib
import time


def dict2list(dic: dict):
    ''' 将字典转化为列表 '''
    keys = dic.keys()
    vals = dic.values()
    lst = [(key, val) for key, val in zip(keys, vals)]
    return lst


def account_login(request):
    message = ['success', '欢迎来到登陆页面']
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(username=username, password=password)
        if user:
            login(request, user)
            return redirect('/admin/')
        else:
            message = ['warning', '登陆失败']
    return render(request, 'login.html',
                  {'message': message, 'Year': Year, 'month': month, 'day': day, 'Hour': Hour, 'Minute': Minute,
                   'Second': Second, 'mess': None, 'backimg': 6, 'height': 100})


def accounts_logout(request):
    logout(request)
    return redirect('/accounts/login')


# Create your views here.
def index(request):
    utcnow = datetime.datetime.now()
    log = -3
    message = ['success', '欢迎来到提交平台']
    ti = datetime.datetime(Year, month, day, Hour, Minute, Second)
    if utcnow > ti:
        message = "warning", "比赛已结束！"
    elif request.POST:
        # 判断是否正确用户
        score_change = 0
        token = request.POST['token']
        flag = request.POST['flag']
        score_result = Score.objects.filter(token=request.POST['token'])
        flag_result = Flag.objects.filter(flag_num=request.POST['flag'])
        round_index = Round.objects.all()[0].round_index
        # 判断token是否正确
        if score_result:
            # 判断flag是否正确
            if flag_result:
                # 判断是否提交自己的flag
                if flag_result[0].user_name == score_result[0].user_name:
                    message = "warning", "不允许提交自己的flag"
                elif round_index == flag_result[0].round_index:  # 是否超时
                    # 是否在该轮提交过
                    if Logs.objects.filter(user_name=score_result[0].user_name, flag_num=flag,
                                           round_index=flag_result[0].round_index):
                        message = "warning", "flag已提交"
                    else:
                        message = "success", "flag提交成功！"
                        score_change = flag_score
                        attack_score = Score.objects.get(token=token)
                        attack_score.fraction = F('fraction') + score_change
                        attack_score.save()
                        attacked_score = Score.objects.get(user_name=flag_result[0].user_name)
                        attacked_score.fraction = F('fraction') - score_change
                        attacked_score.save()
                else:
                    # 超时
                    message = "warning", "flag已过期"
                # 攻击者
                Logs(
                    user_name=score_result[0].user_name,
                    hacked_name=flag_result[0].user_name,
                    flag_num=flag,
                    info=message[1],
                    round_index=flag_result[0].round_index,
                    result=score_change
                ).save()
            else:
                message = "warning", "flag错误"
        else:
            message = "warning", "token错误"
    return render(request, 'index.html',
                  {'message': message, 'Year': Year, 'month': month, 'day': day, 'Hour': Hour, 'Minute': Minute,
                   'Second': Second, 'mess': None, 'backimg': 6, 'height': 100})


def score(request):
    message = ['success', '来查看总榜了呢']
    return render(request, 'table.html',
                  {'message': message, 'Year': Year, 'month': month, 'day': day, 'Hour': Hour, 'Minute': Minute,
                   'Second': Second, 'backimg': 6, 'height': 150})


# 看分数
def user_api1(request):
    htmls = ''
    html = {}
    round_index = Round.objects.all()[0].round_index
    for i in Status.objects.filter(round_index=round_index):
        s = Score.objects.filter(user_name=i.user_name)[0]
        if i.run == 0:
            r = '<font face="arial" >服务宕机</font>'
        else:
            r = '<font face="arial" >运行正常</font>'
        html[i.user_name] = [int(s.fraction), r]
    htm = sorted(dict2list(html), key=lambda x: x[1], reverse=True)  # 按照第1个元素降序排列
    j = 1
    for i in htm:
        t = str(j)
        htmls += """<tr><td>第{}名</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
            t, i[0], '&ensp;' + str(i[1][0]), i[1][1])
        j += 1

    return HttpResponse(htmls)


# admin 看分数
@login_required
def admin_api1(request):
    htmls = ''
    html = {}
    round_index = Round.objects.all()[0].round_index
    for i in Status.objects.filter(round_index=round_index):
        s = Score.objects.filter(user_name=i.user_name)[0]
        if i.run == 0:
            r = '<font face="arial" >服务宕机</font>'
        else:
            r = '<font face="arial" >运行正常</font>'
        r += '<td>{}</td>'.format(s.token)
        html[i.user_name] = [int(s.fraction), r]
    htm = sorted(dict2list(html), key=lambda x: x[1], reverse=True)  # 按照第1个元素降序排列
    j = 1
    for i in htm:
        if j == 1:
            t = str(j)
            htmls += """<tr><td>第{}名</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
                t, i[0], '&ensp;' + str(i[1][0]), i[1][1])
            j += 1
            continue
        if j == 2:
            t = str(j)
            htmls += """<tr><td>第{}名</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
                t, i[0], '&ensp;' + str(i[1][0]), i[1][1])
            j += 1
            continue
        if j == 3:
            t = str(j)
            htmls += """<tr><td>第{}名</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
                t, i[0], '&ensp;' + str(i[1][0]), i[1][1])
            j += 1
            continue
        else:
            t = str(j)
            htmls += "<tr><td>第{}名</td><td>{}</td><td>{}</td><td>{}</td></tr>".format(t, i[0],
                                                                                      '&ensp;' + str(i[1][0]),
                                                                                      i[1][1])
            j += 1
    return HttpResponse(htmls)


# 状态日志
def user_api2(request):
    html = ''
    round_index = Round.objects.all()[0].round_index
    for i in Logs.objects.filter(Q(result=100) | Q(result=-100, round_index=round_index))[
             ::-1][0:20]:
        html += """<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
            round_index,
            i.user_name,
            i.hacked_name,
            str(i.last)[5:19],
            i.result,
            i.info,

        )
    return HttpResponse(html)


@login_required
def admin_api2(request):
    html = ''
    for i in Logs.objects.all()[::-1][0:100]:
        html += """<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
            i.round_index,
            i.user_name,
            i.hacked_name,
            str(i.last)[5:19],
            i.flag_num,
            i.info,
            i.result
        )
    return HttpResponse(html)


# flag状态
@login_required
def admin_api3(request):
    html = ''
    utcnow = datetime.datetime.now().replace(tzinfo=None)
    for i in Flag.objects.all()[::-1][0:50]:
        round_index = Round.objects.all()[0].round_index
        if round_index == i.round_index:
            te = '有效'
        else:
            te = '已失效'
        html += """<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>""".format(
            i.round_index,
            i.user_name,
            i.flag_num,
            te
        )
    return HttpResponse(html)


def update(request):
    """
    更新flag和服务器状态
    :param request:
    :return:
    """
    key = request.POST['secret_key']
    user_name = request.POST['user_name']
    action = request.POST['action']
    data = request.POST['data']
    round_index = int(request.POST['round_index'])
    if key == secret_key:
        if action == 'flag':
            Flag(
                user_name=user_name,
                flag_num=data,
                round_index=round_index,
            ).save()
            Round.objects.all().update(round_index=round_index)  # 开始新的一轮
            Status.objects.all().update(round_index=round_index)
            return HttpResponse("flag updated succ")
        if action == 'status':
            if int(data):
                enc = 1
            else:
                enc = -1
            Status.objects.filter(user_name=user_name, round_index=round_index).update(run=data)
            if enc > 0:
                # Score.objects.filter(user_name=user_name).update(fraction=fraction + flag_score* enc)
                Logs(
                    user_name=user_name,
                    hacked_name=user_name,
                    flag_num='null',
                    info="运行正常！",
                    round_index=round_index,
                    result=0
                ).save()
            else:
                downserver_score = Score.objects.get(user_name=user_name)
                downserver_score.fraction = F('fraction') + flag_score * enc
                downserver_score.save()
                Logs(
                    user_name=user_name,
                    hacked_name=user_name,
                    flag_num='null',
                    info="服务器宕机！",
                    round_index=round_index,
                    result=flag_score * enc
                ).save()
            return HttpResponse("status updated succ")
    return HttpResponseNotFound


@login_required
def admin(request):
    message = ['success', '欢迎管理大大的到来']
    if request.POST:
        if int(request.POST['run']) == 1:
            info = "服务器正常 by admin"
            enc = 1
        else:
            info = "服务器宕机 by admin"
            enc = -1
        user_name = request.POST['user_name']
        round_index = Round.objects.all()[0].round_index
        server_score = Score.objects.get(user_name=user_name)
        server_score.fraction = F('fraction') + flag_score * enc
        server_score.save()
        Status.objects.filter(user_name=user_name, round_index=round_index).update(run=request.POST['run'])
        Logs(
            user_name=user_name,
            hacked_name=user_name,
            flag_num='null',
            info=info,
            round_index=round_index,
            result=flag_score * enc
        ).save()
        message = ['success', '修改成功了呢']
    status_ = Status.objects.all()
    return render(request, 'admin.html', {'status': status_, 'message': message, 'backimg': 6, 'height': 100})


@login_required
def admin_table(request):
    message = ['success', '来查看总榜了呢']
    return render(request, 'admin_table.html', {'message': message, 'backimg': 6, 'height': 150})
