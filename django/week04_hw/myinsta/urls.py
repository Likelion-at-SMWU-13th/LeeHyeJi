from django.contrib import admin
from django.urls import path, include
from posts.views_cal import calculator_query, calculator_body

urlpatterns = [
    path('admin/', admin.site.urls),
    path('posts/', include('posts.urls', namespace='posts')),
    path('calculator/query', calculator_query),
    path('calculator/body', calculator_body),
    path('users/', include('users.urls', namespace='users')),
]