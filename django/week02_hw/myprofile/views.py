from django.shortcuts import render
from django.http import HttpResponse
from django.views import View

# FBV - 자기소개 기능
def intro_view(request):
    if request.method == "POST":
        name = request.POST.get("name")
        age = request.POST.get("age")
        univ = request.POST.get("univ")
        track = request.POST.get("track")
        mbti = request.POST.get("mbti")

        print(f"[POST] 이름: {name}, 나이: {age}, 학교: {univ}, 트랙: {track}, MBTI: {mbti}")

    return render(request, "intro_form.html")

# CBV - 일기 기능
class DiaryView(View):
    def get(self, request):
        return render(request, "diary_form.html")

    def post(self, request):
        date = request.POST.get("date")
        weather = request.POST.get("weather")
        mood = request.POST.get("mood")
        content = request.POST.get("content")

        print(f"[POST] 날짜: {date}, 날씨: {weather}, 기분: {mood}, 내용: {content}")
        return render(request, "diary_form.html")