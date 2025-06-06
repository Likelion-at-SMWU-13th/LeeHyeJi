# Generated by Django 5.2 on 2025-04-12 13:44

import django.db.models.deletion
import django.utils.timezone
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('comments', '0001_initial'),
        ('posts', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='comment',
            name='time',
            field=models.DateTimeField(auto_now_add=True, default=django.utils.timezone.now, verbose_name='작성시간'),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='comment',
            name='post',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='posts.post', verbose_name='게시물'),
        ),
        migrations.AlterField(
            model_name='comment',
            name='user',
            field=models.CharField(max_length=50, verbose_name='사용자'),
        ),
    ]
