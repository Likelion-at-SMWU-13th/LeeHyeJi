
# Django Forms 정리

## 1. Django에서의 Form

Django에서 Form은 사용자의 입력을 효과적으로 수집하고 처리할 수 있도록 합니다. 이를 통해 서버 측에서 검증하고, 필요한 데이터를 저장하거나 처리할 수 있습니다. 

## 2. HTML Form 기본 개념

HTML에서 `<form>` 태그는 사용자가 정보를 입력하고 서버로 전송할 수 있도록 하는 기본적인 구조입니다. 

## 3. GET과 POST의 차이

GET은 사용자가 입력한 데이터를 URL에 붙여서 서버에 전송하는 방식입니다. 이 방식은 URL에 입력 내용이 그대로 보이기 때문에 보안상 취약합니다. 따라서 단순 검색이나 조회에 적합합니다. 반면 POST는 데이터를 본문에 담아 전송하므로 보안상 안전하며, 로그인이나 회원가입처럼 민감한 정보를 처리할 때 사용합니다.


## 4. Django의 Form 클래스 정의

Django에서는 클래스를 사용해 폼을 정의합니다. 이 클래스는 `forms.Form`을 상속받아 만들며, 필드를 클래스 변수로 선언합니다. 예를 들어, 사용자의 이름을 입력받는 폼은 다음과 같이 작성할 수 있습니다.

```python
from django import forms

class NameForm(forms.Form):
    your_name = forms.CharField(label='Your name', max_length=100)
```


## 5. Form을 처리하는 뷰 함수 작성

사용자가 제출한 폼 데이터는 뷰에서 해당 Form 클래스를 이용해 처리됩니다. 일반적인 처리 흐름은 다음과 같습니다.

1. 사용자가 페이지에 처음 접근하면 빈 폼을 보여줍니다.
2. 사용자가 입력한 데이터를 제출하면 `POST` 요청을 통해 데이터를 받아옵니다.
3. 받은 데이터를 Form 클래스에 전달하고, `is_valid()` 메서드로 유효성을 검사합니다.
4. 검증을 통과하면 `cleaned_data` 속성을 통해 정제된 데이터에 접근할 수 있습니다.


## 6. 템플릿에서 Form 출력하기

Django는 템플릿에서 폼을 쉽게 출력할 수 있도록 도와줍니다. `{{ form }}`이라는 표현을 사용하면 폼의 필드들이 자동으로 HTML 형식으로 렌더링됩니다. 폼을 출력할 때는 `csrf_token` 태그도 함께 사용하여 CSRF 토큰을 포함해야 합니다.

```html
<form method="post">
    {% csrf_token %}
    {{ form }}
    <input type="submit" value="Submit">
</form>
```

## 7. 폼의 유효성 검사와 cleaned_data

폼에서 `is_valid()`를 호출하면 모든 필드에 대해 유효성 검사가 수행됩니다. 이 검사를 통과한 경우 입력된 데이터는 `cleaned_data`라는 딕셔너리에 저장되며, 이 데이터를 사용하여 데이터베이스 저장이나 이메일 전송 등의 작업을 수행할 수 있습니다.


## 8. 바운드 폼과 언바운드 폼

Django의 Form 객체는 데이터가 입력되었는지 여부에 따라 바운드(Bound) 상태와 언바운드(Unbound) 상태로 나뉩니다. 바운드 폼은 사용자의 입력 데이터가 포함되어 있어 검증이 가능하지만, 언바운드 폼은 빈 폼으로 검증 대상이 아닙니다.


## 9. ModelForm을 사용한 폼 자동 생성

ModelForm은 모델 클래스와 직접 연결되어 있어 모델에 정의된 필드들을 자동으로 폼으로 변환해 줍니다. 이를 통해 중복 코드를 줄이고 빠르게 CRUD 인터페이스를 만들 수 있습니다.

```python
from django.forms import ModelForm
from .models import Article

class ArticleForm(ModelForm):
    class Meta:
        model = Article
        fields = ['title', 'content']
```


## 10. 위젯을 통한 HTML 요소 제어

각 Form 필드는 내부적으로 위젯이라는 객체를 통해 HTML 요소로 렌더링됩니다. 예를 들어 `CharField`는 기본적으로 `<input type="text">`로, `BooleanField`는 `<input type="checkbox">`로 출력됩니다. 필요에 따라 위젯을 지정해 출력 형태를 커스터마이징할 수 있습니다.

```python
message = forms.CharField(widget=forms.Textarea)
```


## 11. 템플릿에서 필드 개별 출력

폼 전체를 출력하지 않고 개별 필드를 세밀하게 제어하고 싶을 때는 템플릿에서 필드를 하나씩 접근할 수 있습니다. 이렇게 하면 각 필드에 대해 오류 메시지, 라벨, 입력 요소를 따로 제어할 수 있습니다. 

```django
{{ form.subject.errors }}
{{ form.subject.label_tag }}
{{ form.subject }}
```

## 12. 반복문을 통한 필드 출력

폼의 모든 필드를 반복문을 통해 출력할 수 있습니다. 이 방식은 폼 필드 수가 많을 때 유용하며 레이아웃을 일관되게 구성할 수 있습니다.

```django
{% for field in form %}
  <div class="fieldWrapper">
    {{ field.errors }}
    {{ field.label_tag }} {{ field }}
    {% if field.help_text %}
      <p class="help">{{ field.help_text }}</p>
    {% endif %}
  </div>
{% endfor %}
```


## 13. 숨겨진 필드와 일반 필드 분리 출력

Django의 Form 객체는 숨겨진 필드(hidden fields)와 일반 필드(visible fields)를 구분할 수 있습니다. 이 기능을 통해 숨겨진 필드를 별도로 처리할 수 있습니다. 

```django
{% for hidden in form.hidden_fields %}
  {{ hidden }}
{% endfor %}

{% for field in form.visible_fields %}
  <div class="fieldWrapper">
    {{ field.errors }}
    {{ field.label_tag }} {{ field }}
  </div>
{% endfor %}
```

