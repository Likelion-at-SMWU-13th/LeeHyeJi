from rest_framework.serializers import ModelSerializer
from .models import User

class UserModelSerializer(ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'password', 'email', 'phone', 'profile_image']
        extra_kwargs = {
            'password': {'write_only': True}
        }

    def create(self, validated_data):
        user = User.objects.create_user(
            username=validated_data['username'],
            email=validated_data.get('email'),
            password=validated_data['password'],
            phone=validated_data.get('phone'),
            profile_image=validated_data.get('profile_image')
        )
        return user