
#03_07


##nodeJS Basic


```java

//주석 
/*
*/

//1. 변수의 선언 - 타입이 없다 var 로 통일 

var string = "david";
var integer = 50;

//2. 숫자 연산
var result = integer + 30;

//3. 콘솔에 출력
console.log("reuslt = " + result);

//4. 함수 - 함수내부에 리턴이 있으면 알아서 인식 
function sum(param1, param2) {
	return param1 + param2;
}

function sumPrint(param1, param2) {
	console.log("sum result = " + sum(param1,param2));
}

sumPrint(4,7);


```
-  실행
  -  cmd 창에서 nodejs파일이 있는 폴더로 들어간 다음 
  -  입력 : node 파일이름 


```java

//서버 생성을 위해 http 모듈을 사용한다
var http = require('http') //java에서 import http와 같다

//http 모듈 안에 API로 서버를 생성한다
var server = http.createServer(
function (request, response) { //콜백함수는 함수 이름없이 body를 작성한다, 사용자로부터 요청이 있으면 호출되는 콜백함수 
	response.writeHead(200, {'Content-Type' : 'text/html'});
	response.end('Hello node.js! <br/> Nice2meetYa')
}
	);

//생성된 서버를 열어준다 
server.listen(8080, function() { // 서버가 열리고 나면 호출되는 콜백함수 
	console.log('server is running..')
});

```

-  node server  <- 서버 실행 
-  Ctr + C 로 종료

