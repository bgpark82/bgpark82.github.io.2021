---
layout: single
title: ThreadPool
categories:
- java
tags: 
- java
---



## Thread의 문제

Thread는 작업을 병렬적으로 처리하도록 돕는다

하지만 병렬작업이 많이 처리되면 스레드의 개수가 증가하게 된다

**스레드 생성과 스케쥴링은 CPU와 메모리 리소스를 많이 사용하게 된다**

결국, 어플리케이션 성능이 급격히 저하하는 원인이 된다

> 특히 WAS 서버는 병렬처리를 위해 요청마다 Thread를 생성하게 된다
>
> 트래픽이 많은 서비스는 매우 많은 요청이 들어어고 그만큼 Thread가 필요하다
>
> 하지만 



## ThreadPool

![image-20220419012004916](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220419012004916.png)

ThreadPool은 병렬처리에 사용되는 **Thread를 제한된 개수만큼 미리 생성**한다

Queue에 들어오는 작업을 미리 생성된 Thread가 처리한다

작업이 끝난 Thread는 결과를 어플리케이션에 반환하고, Thread는 Queue로 들어와 재활용된다

작업이란 Runnable이나 Callable 인터페이스를 상속받은 클래스를 말한다

> ThreadPool은 데몬 스레드가 아니다
>
> 즉, main 스레드가 종료되어도 ThreadPool의 Thread는 작업을 처리해야 하므로 어플리케이션을 종료하지 않는다

## Excutors

ThreadPool은 **Executors라는 `java.concurrent` 패키지의 클래스를 사용하여 생성**한다

Executors는 ThreadPool을 생성하는 메소드인 `newCachedThreadPool`과 `newFixedThreadPool`를 가지고 있다

해당 메소드들은 ThreadPoolExecutor(ThreadPool 실행하는 객체)를 반환하고 이는 ExecutorService를 위임받는다

(개인적으로 ExecutorService와 ThreadPool이라는 개념이 연결되지 않았는데 구현체의 이름을 보면 좀 더 쉽게 이해할 수 있다)

![image-20220419013202061](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220419013202061.png)


### 3-1. newCachedThreadPool

![image-20220419014414284](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220419014414284.png)

```java
ExecutorService executorService = Executors.newCachedThreadPool();
```

newCachedThreadPool는 이론적으로 **Integer.MAX_VALUE 개수만큼 Thread를 생성**할 수 있다 (물론 OS 상황에 따라 다르다)

이미 생성된 Thread는 ThreadPool 내에서 재사용된다

기본적으로 60초 동안 Thread가 아무 작업을 하지 않으면 ThreadPool에서 제거 된다

보통 CachedThreadPool은 Integer.MAX_VALUE 만큼 Thread를 생성할 수 있으므로 **짧은 비동기 작업**에 유리하다

(Cached라는 이름이 조금 헷깔렸는데 아마 이미 생성된 Thread에 대해서는 ThreadPool 안에서 Cache 된다는 의미로 보인다)


### 3-2. newFixedThreadPool

![image-20220419014909183](https://raw.githubusercontent.com/bgpark82/image/master/images/image-20220419014909183.png)

```java
ExecutorService executorService = Executors.newFixedThreadPool(nThread);
```

newFixedThreadPool은 주어진 Thread 개수만큼 코어 스레드 개수와 최대 스레드 개수를 가질 수 있다

즉, **주어진 Thread 개수만큼 ThreadPool에 항상 남아있고 그 이상 Thread가 필요해도 더 이상 생성하지 않고 재활용해서 사용한다**

만약 Thread 개수 이상의 작업 요청이 오면 **작업이 끝난 Thread가 생길 때까지 작업은 Queue에서 대기**한다

만약 Thread 중 하나를 사용하지 못하면 새로운 Thread를 생성하여 개수를 맞춘다


### 3-3. newCachedThreadPool vs newFixedThreadPool

두 메소드를 정리해서 비교하면 다음과 같다

| 메소드명                        | 초기 스레드 수 | 코어 스레드 수 | 최대 스레드 수    |
| ------------------------------- | -------------- | -------------- | ----------------- |
| newCachedThreadPool()           | 0              | 0              | Integer.MAX_VALUE |
| newFixedThreadPool(int nThread) | 0              | nThread        | nThread           |

(코어 스레드는 **ThreadPool을 유지하는 최소한의 Thread 개수**를 뜻한다)

newCachedThreadPool은 **필요할 때마다 Thread를 생성**하고

newFixedThreadPool은 **초기 설정 개수만큼만 Thread를 생성**한다





