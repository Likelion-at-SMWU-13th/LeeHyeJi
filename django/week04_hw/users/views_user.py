from rest_framework.response import Response
from rest_framework.decorators import api_view
from .models import User
from .serializers import UserModelSerializer

# CREATE - 회원가입
@api_view(['POST'])
def signup(request):
    serializer = UserModelSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data)
    return Response(serializer.errors)

# READ - 회원정보 조회
@api_view(['GET'])
def user_detail(request, user_id):
    user = User.objects.get(id=user_id)
    serializer = UserModelSerializer(user)
    return Response(serializer.data)

# UPDATE - 회원정보 수정
@api_view(['PATCH'])  
def user_update(request, user_id):
    user = User.objects.get(id=user_id)
    serializer = UserModelSerializer(user, data=request.data, partial=True)  # ← 부분 수정 허용
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data)
    return Response(serializer.errors)

# DELETE - 회원 탈퇴
@api_view(['DELETE'])
def user_delete(request, user_id):
    user = User.objects.get(id=user_id)
    user.delete()
    return Response({'message': '회원 탈퇴 완료'})