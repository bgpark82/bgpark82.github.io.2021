---
layout: single
title: Serializable
categories:
- 
tags: 
- 
---

## Serializable

Serializable은 **객체를 파일에 읽고 쓰거나 서버로 보내고 받을 때 사용**된다

Serializable는 메소드가 없는 인터페이스로 `java.io` 패키지에 존재한다

Serializable 인터페이스륽 구현한 후에는 **`serialVersionUID`를 지정하는 것을 권장**한다

별도로 지정하지 않으면 컴파일러가 자동으로 생성한다

```java
static final long serialVersionUID = 1L;
```

serialVersionUID는 **객체의 버전을 명시하는데 사용**된다

만약 서버 A가 서버 B에게 SerialDTO 객체를 전송한다면 서버 A와 서버 B에 SerialDTO라는 클래스가 존재해야한다

serialVersionUID는 **서버 A가 서버 B에게 보내는 객체가 같은지 확인하도록 한다** 

클래스 이름이 같아도 serialVersionUID가 다르면 다른 클래스라고 인식한다

serialVersionUID가 같아도 변수 개수나 타입이 다르면 다른 클래스라고 인식한다

## transient

```java
transient private int count;
```

transient는 **선언된 변수는 Serializable 대상에서 제외할 때** 사용된다

주로 비밀번호를 전송하지 않을 경우 등 보안상 이슈로 사용된다



## 참고

https://nesoy.github.io/articles/2018-06/Java-transient

https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.3.1.4