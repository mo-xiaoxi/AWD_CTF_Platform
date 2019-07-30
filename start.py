#!/usr/bin/env python

import sys
import os

teamno = int(sys.argv[1])


def start_flag():
    os.system('cd flag_server && sh docker.sh')
    print('[*] start docker flag_server')


def gen_host_lists():
    res = ''
    for i in range(teamno):
        res += "team%d:172.17.0.%d\n" % (i + 1, i + 2)
    open('check_server/host.lists', 'w').write(res)


def start_check():
    gen_host_lists()
    os.system('cd check_server && sh docker.sh')
    print('[*] start docker check_server')


def start_team(teamno):
    team_dir = 'team' + str(teamno).zfill(2)
    os.system('cd %s && sh docker.sh' % (team_dir))
    print('[*] start docker %s' % team_dir)


if __name__ == '__main__':
    for i in range(1, teamno + 1):
        start_team(i)
    start_flag()
    start_check()
