# Springboot를 활용한 웹 서비스 프로젝트

<pre>
웹 서비스를 개발할 때 공통적으로 자주 사용할 만한 기능들을 시험삼아 제작했습니다. 백엔드 개발을 다루기 위해 Restful API 형식으로 개발중인 예제 프로젝트입니다.
</pre>

<pre>
docker를 활용한 데이터베이스 환경설정 방법
프로젝트 루트경로의 docker-compose.yml 을 활용한다.
<code>
% docker compose up --detach
</code>
</pre>

<ul>사용 기술</ul>
<li>Java (OpenJDK 21)</li>
<li>Springboot (3.2.6-SNAPSHOT)</li>
<li>JPA</li>
<li>SpringSecurity</li>
<br/>
<li>Swagger-UI</li>
<li>Docker</li>
<br/>

<ul>구현 기능</ul>
<li>Exception에 대한 Restful API 응답처리 [커밋: 24.05.09]</li>
<li>회원가입, 로그인, 로그아웃 [커밋: 24.05.09]</li>
<li>JWT 로그인 [커밋: 24.05.09]</li>
<li>Swagger-UI 적용 [커밋: 24.05.15]</li>
<li>Docker를 활용한 DB 초기설정 적용 [커밋: 24.05.16]</li>