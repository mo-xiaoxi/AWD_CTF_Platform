"""
WSGI config for awd_platform project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/2.0/howto/deployment/wsgi/
"""

# import os

# from django.core.wsgi import get_wsgi_application

# os.environ.setdefault("DJANGO_SETTINGS_MODULE", "awd_platform.settings")

# application = get_wsgi_application()
import os
from os.path import join, dirname, abspath

PROJECT_DIR = dirname(dirname(abspath(__file__)))

import sys

sys.path.insert(0, PROJECT_DIR)
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "awd_platform.settings")

from django.core.wsgi import get_wsgi_application

application = get_wsgi_application()
