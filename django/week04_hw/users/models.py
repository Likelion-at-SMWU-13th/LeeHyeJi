from django.contrib.auth.models import AbstractUser
from django.db import models

class User(AbstractUser):
    phone = models.CharField(verbose_name='전화번호', max_length=11)
    profile_image = models.ImageField(verbose_name='프로필 이미지', null=True, blank=True, upload_to='profile/')