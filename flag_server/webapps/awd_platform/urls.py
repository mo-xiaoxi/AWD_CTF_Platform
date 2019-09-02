"""awd_platform URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from app import views
from .settings import *
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
                  # path('admin/', admin.site.urls),
                  path('', views.index, name='index'),
                  path('score/', views.score, name='score'),
                  path('accounts/login/', views.account_login, name='admin_login'),
                  path('admin/', views.admin, name='admin'),
                  path('admin/table/', views.admin_table, name='admin_table'),
                  path('user_api1/', views.user_api1, name='user_api1'),
                  path('user_api2/', views.user_api2, name='user_api2'),
                  path('admin_api1/', views.admin_api1, name='admin_api1'),
                  path('admin_api2/', views.admin_api2, name='admin_api2'),
                  path('admin_api3/', views.admin_api3, name='admin_api3'),
                  path('adm1n_ap1', views.update, name='update'),
              ] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
