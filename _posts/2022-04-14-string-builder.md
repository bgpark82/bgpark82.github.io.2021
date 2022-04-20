---
layout: single
title: 자바의 try with resource
categories:
- 에프랩 멘토링 2주차
tags:
- java
---

## StringBuilder의 동작방식

StringBuilder는 내부적으로는 char 배열을 사용한다.

생성 시점에 문자가 없으면 길이가 16인 char 배열을 기본적으로 생성한다

```java
AbstractStringBuilder(int capacity) {
  value = new char[capacity];
}
```

문자를 더할 때, **null이면 char 배열에 4개의 공간을 추가해 null이라는 문자를 한 글자씩 char 배열에 넣는다**

문자가 있으면 **문자의 길이만큼 char 배열을 증가시켜 한 글자씩 char 배열에 넣는다**

```java
public AbstractStringBuilder append(String str) {
  if (str == null)
    return appendNull();
  int len = str.length();
  ensureCapacityInternal(count + len);
  str.getChars(0, len, value, count);
  count += len;
  return this;
}
```

배열의 길이를 증가시킬 때는 내부적으로 `System.arraycopy` 라는 native 메소드를 사용한다

내부적으로는 Builder 패턴을 사용한다

결국, StringBuilder 생성 시, 배열을 Heap 영역에 생성하고 해당 배열의 길이를 늘리는 방식을 사용한다



## StringBuffer의 동작방식

StringBuffer도 StringBuilder처럼 내부적으로는 char 배열을 사용한다.

문자를 더할 때도, StringBuilder 처럼 char 배열을 늘려가며 한 글자씩 배열에 넣는다.

이는, **StringBuilder와 StringBuffer가 모두 공통로직을 구현한 `AbstractBuilder`를 상속하여 사용**하기 때문이다

```java
public final class StringBuilder extends AbstractStringBuilder 
public final class StringBuffer extends AbstractStringBuilder 
```

하지만, **차이점은 StringBuffer의 메소드에 `synchronized` 지시어**가 붙는다

```java
@Override
public synchronized StringBuffer append(String str) {
  toStringCache = null;
  super.append(str);
  return this;
}
```

멀티스레드 환경에서 **인스턴스 변수는 Heap 영역에 존재하며 여러 스레드가 접근 할 수 있다**

그렇게 되면 StringBuilder를 여러 스레드가 사용하면서 원하는 문자가 되지 않을 수 있다

**synchronized 블록을 사용하면 스레드가 메소드의 monitor 락을 점유해야 자원을 변경할 수 있다**

작업을 마친 스레드는 monitor 락을 반납하면서 대기중이던 스레드가 메소드에 접근하게 된다

synchronized는 스레드를 할당하기 위해 CPU 자원을 사용하므로 StringBuilder보다는 느리다.

> 왜 synchronized가 메소드에 붙었을까? 메소드 내부에 참조변수에 붙었으면 성능이 더 좋지 않았을까?
>
> 성능상의 차이는 없을까?
>
> 성능상의 차이를 측정할 수는 없을까?

## 왜 StringBuilder를 사용할까?

자바 프로그램에서 가장 많이 사용하는 객체는 Object, String, Collection 순이다

(Object는 모든 객체의 부모 객체이므로 가장 많은 것이 당연하다)

String 객체는 immutable하다.

즉, String을 생성할 때마다 Heap에 메모리에 할당되며 생성된 **String의 주소만 변경**한다

String에 다른 String 값을 `+` 해도 새로운 객체가 생성된다

```java
@Test
void string() {
  String a = "abc";
  System.out.println(Integer.toHexString(a.hashCode()));
  a += "def";
  System.out.println(Integer.toHexString(a.hashCode()));
  a += "ghi";
  System.out.println(Integer.toHexString(a.hashCode()));
}
```

![image-20220416143956963](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220416143956963.png)

각 String의 hashCode를 사용하여 주소를 표현했다 (자바의 hashcode는 기본적으로 객체의 주소를 16진수로 표현한 값이다)

String 값을 더할 때마다 기존 객체는 GC의 대상이 되고 새로운 String 객체을 생성해낸다

이 작업이 **반복되면 메모리 공간을 많이 차지**하게 되고 **GC를 많이 하게 되어 시스템의 자원(CPU)를 사용**하게 된다

![image-20220416144407809](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220416144407809.png)

StringBuilder는 **내부에 char 배열 하나를 가지고 길이를 늘리며 문자를 추가**한다

그렇기 때문에 Heap 영역에는 배열하나만 존재하며 String 만큼 메모리를 많이 점유하거나 GC를 할 필요가 없다

![image-20220416145512064](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220416145512064.png)

> 참고로 **JDK 5.0 이상을 사용하면 String을 사용하더라도 컴파일 시, StringBuilder로 최적화를 해준다**

## 정리

String, StringBuilder, StringBuffer는 다음과 같은 상황에서 사용하는 것이 좋다

**String**은 다음과 같은 경우에 사용한다

- `+` 연산으로 반복적으로 더하지 않을 때

**StringBuilder**는 다음과 같은 경우에 사용한다

- 스레드를 사용하지 않는 환경에서 사용

**StringBuffer**는 다음과 같은 경우에 사용한다

- 멀티 스레드 환경에서 스레드에 안전한 프로그램을 작성할 때

- static으로 선언된 문자열을 작성할 때

- singleton으로 선언된 클래스의 문자열을 수정할 때

