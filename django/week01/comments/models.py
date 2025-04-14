from django.db import models
from posts.models import Post 

# Create your models here.
class Comment(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE, verbose_name='게시물')
    user = models.CharField(max_length=50, verbose_name='사용자')
    text = models.TextField(verbose_name='내용')
    time = models.DateTimeField(auto_now_add=True, verbose_name='작성시간')

    def __str__(self):
        return f'{self.user} - {self.text[:20]}'