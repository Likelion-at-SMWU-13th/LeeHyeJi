from django.contrib import admin
from django.urls import path, include
from posts.views import login_view

urlpatterns = [
    path('admin/', admin.site.urls),
    path('posts/', include('posts.urls', namespace='posts')),
    path('login/', login_view),
]
