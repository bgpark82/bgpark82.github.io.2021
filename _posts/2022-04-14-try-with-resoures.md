---
layout: single
title: 자바의 try with resource
categories:
- 에프랩 멘토링 2주차
tags:
- java
---

# 왜 try-with-resources를 사용하는가?

자바 라이브러리에는 close 메소로 직접 닫아줘야 할 자원이 많다 

기존에 try-catch-finally를 사용한다면 **여러개의 자원을 사용할 때는 코드가 지저분**해진다

또한, 물리적인 문제가 발생한다면 **try 문에서 에러가 발생하고 finally에도 에러가 발생**한다

그렇게 되면 finally의 에러가 try의 에러를 집어 삼켜 try의 에러가 무엇인지 알 수 없게 된다

이러한 문제 때문에 try-with-resources를 사용한다

# try-with-resources

try-with-resources는 자바 7부터 사용할 수 있다

try-with-resources를 사용하려면 `AutoCloseable` 인터페이스를 구현해야 한다

```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

`AutoCloseable`은 **close()라는 메소드를 하나 가진 인터페이스**이다

파일이나 소켓등의 여러 자바 라이브러리들의 클래스가 이미 `AutoCloseable`을 구현해서 사용하고 있다

예를들어, `InputStream`나 `OutputStream`는 내부적으로 `AutoCloseable`을 상속받고 있다

```java
public abstract class InputStream implements Closeable { }
public interface Closeable extends AutoCloseable { }
```

아래는 try-with-resources 사용 방법이다 

```java
try(InputStream in = new FileInputStream(src);
   	OutputStream out = new FileOutputStream(dst)) {
		// 구현
} catch (IOException e) {
  e.printStackTrace();
}
```

try문 내부에 `AutoCloseable`을 상속받은 구현체의 인스턴스를 생성하여 try 문 아래 사용할 수 있다

이렇게 되면, 실제로 **보여줄 예외만 보존되고 다른 예외는 숨길 수 있다** 

(이 경우, 숨겨진 예외는 suppressed를 가지고 출력된다, 자바 7의 Throwable에 getSuppresed로 해당 메세지를 가져올 수 있다)

# 정리

정리하면 꼭 닫아야 되는 자원 (소켓, 파일 등)은 try-catch-finally가 아닌 try-with-resources를 사용하는 것이 유용하다

1. 높은 가독성 (짧은 코드)
2. 정확한 예외