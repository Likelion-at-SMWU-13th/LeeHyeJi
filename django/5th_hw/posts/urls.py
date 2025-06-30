from django.urls import path, include
from django.contrib import admin
from rest_framework import routers

from .views import PostModelViewSet,PostListView,PostRetrieveView, CommentCreateView

app_name="posts"

router_post = routers.DefaultRouter()
router_post.register('', PostModelViewSet)

urlpatterns = [
    path('comments/', CommentCreateView.as_view()),
    path('', include(router_post.urls)),
    path('',PostListView.as_view()),
    path('<int:pk>', PostRetrieveView.as_view()),
]