# Generated by Django 5.2 on 2025-04-12 12:22

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('post', models.IntegerField(verbose_name='게시물')),
                ('user', models.CharField(verbose_name='사용자')),
                ('text', models.TextField(verbose_name='내용')),
            ],
        ),
    ]
