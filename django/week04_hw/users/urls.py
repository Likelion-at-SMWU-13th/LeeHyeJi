from django.urls import path, include
from django.contrib import admin
from rest_framework import routers
from .views import UserModelViewSet
from . import views_user

app_name="users"

router_user = routers.DefaultRouter()
router_user.register('model', UserModelViewSet)

urlpatterns = [
    path('', include(router_user.urls)),
    path('signup/', views_user.signup),
    path('<int:user_id>/', views_user.user_detail),
    path('<int:user_id>/update/', views_user.user_update),
    path('<int:user_id>/delete/', views_user.user_delete),
]