from django.shortcuts import render
from rest_framework.viewsets import ModelViewSet 

from .models import Post, Comment
from .serializers import PostModelSerializer,PostListSerializer, PostRetrieveSerializer, CommentListModelSerializer, CommentCreateSerializer
from rest_framework import generics
from rest_framework.decorators import action, api_view
from rest_framework.response import Response
from django.contrib.auth import authenticate
from rest_framework.authtoken.models import Token
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser
# Create your views here.

#게시글 목록 + 생성
class PostListView(generics.ListAPIView, generics.CreateAPIView):
    queryset = Post.objects.all()
    serializer_class = PostListSerializer

#게시글 상세 + 수정 + 삭제 
class PostRetrieveView(generics.RetrieveAPIView, generics.UpdateAPIView, generics.DestroyAPIView):
    queryset = Post.objects.all()
    serializer_class = PostRetrieveSerializer

#Viewset
class PostModelViewSet(ModelViewSet):
    queryset=Post.objects.all()
    serializer_class=PostListSerializer

    @action(detail = True, methods =['GET'])
    def get_comment_all(self, request, pk=None):
        post = self.get_object()
        comment_all = post.comment_set.all()
        serializer = CommentListModelSerializer(comment_all, many = True)
        return Response(serializer.data)
    
@api_view(['POST'])
def login_view(request):
    username = request.POST.get('username')
    password = request.POST.get('password')
    user = authenticate(request,
                        username = username,
                        password = password)
    
    if user : 
        token, _ = Token.objects.get_or_create(user=user)
        return Response({'token': token.key})
    
    else:
        return Response(status=401)

class CommentCreateView(generics.CreateAPIView):
    queryset = Comment.objects.all()
    serializer_class = CommentCreateSerializer
    permission_classes = [IsAuthenticated]