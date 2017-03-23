
# 03_07


## nodeJS Basic


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


# 03_21

```javascript

//가. 식별자 의 선언 및 사용
//1. 변수는 앞에 타입에 대한 구분없이 var 로 선언한다
//2. 문장의 끝은 항상 ; (세미콜론)으로 끝낸다
var name; // identifier 식별자 : 저장소의 이름 

console.log(name);
//3. = 을 사용해서 변수에 값을 입력하는데 문자열 입력시에는 앞뒤로 '(외따옴표) 또는 "(쌍따옴표)를 붙혀준다
name = '홍길동'; 
console.log(name);
//4. 문자나 숫자 및 타입에 관계없이 변수는 var 로 선언한다
var num1;
num1 = 21;
console.log(num1);
//5. 변수의 선언과 동시에 값을 입력할 수 있다
var num2 = 3;
console.log(num2);
//6. 두 개의 변수를 더해서 다른 변수에 입력할 수 있다
var sum = num1 + num2;
console.log(sum);
//7. 숫자와 문자를 더할 경우 결과값은 문자가 된다. 아래 연산결과로 sum2 에는 "홍길동21"이 sum3에는 "이순신3"이 입력된다
var sum2 = name + num1;
console.log(sum2);
var sum3 = '이순신' + 3;
console.log(sum3);


//나. 함수의 선언 및 사용
// 1. 세개의 파라미터를 더한 후 결과값을 리턴하는 함수를 선언
function funcSum(param1, param2, param3){
    return param1 + param2 + param3;
}

// 2. 함수 실행 후 결과값을 result 에 대입
var result = funcSum(1,2,3);

// 3. result 에 담긴 결과값을 출력
console.log('result='+result);

// 4. 결과값이 없는 함수의 선언
function print(param1){
    console.log('param1='+param1);
}

// 5. 함수호출 : return 이 없는 함수는 로직을 자체적으로 처리하기 때문에 결과값 대입 불필요
print('출력내용');

//조건문 반복문은 타 언어와 같다

//마. 클래스

//class의 선언 - 낙타표기법으로 첫번째 글자를 대문자로 함수를 하나 선언한다.
function Clazz(msg){
    // 변수를 객체의 멤버로 사용하기 위해 this 예약어를 사용해서 정의한다.
    // this 는 객체지향접근제한자의 public 개념 
    this.mName = 'I am Class';
    this.message = msg;

    // this를 사용하지 않은 변수
    message2 = "find me!";
    // 멤버함수 선언
    this.print = function(){
        console.log(message2);
    };
}

// 객체를 생성
var myClazz = new Clazz('good to see u!');
console.log(myClazz.message);
// this를 사용하지 않은 message2 변수는 외부에서 참조할 수 없다.
console.log(myClazz.message2);
// this로 선언된 함수를 통해 사용할 수 있다.
myClazz.print();

```

-  html, javascrpit 공부해놓아야 이해 가능
-  [참고 GitBook](https://javafa.gitbooks.io/nodejs_server_basic/content/chapter3.html)


# 03_22

Socket = ip + port

pc - 프로그램 접속 주소 체계

parse = 문자나 숫자를 객체화 


## 클라이언트 요청(GET) 처리

```javascript
var http = require('http');

// 1. 요청한 url을 객체로 만들기 위해 url 모듈사용
var url = require('url');
// 2. 요청한 url 중에 Query String 을 객체로 만들기 위해 querystring 모듈 사용
var querystring = require('querystring'); 

var server = http.createServer(function(request,response){
    // 3. 콘솔화면에 로그 시작 부분을 출력
    console.log('--- log start ---');
    //4. 브라우저에서 요청한 주소를 parsing 하여 객체화 후 출력
    var parsedUrl = url.parse(request.url);
    console.log(parsedUrl);
    // 5. 객체화된 url 중에 Query String 부분만 따로 객체화 후 출력
    var parsedQuery = querystring.parse(parsedUrl.query,'&','=');
    //http://도메인/디렉토리/디렉토리2/파일명?  변수1 = 값 & 변수 2= 값
    //변수 : '값' 형태로 만들어준다.
    console.log(parsedQuery);
    console.log("abc=" + parsedQuery.abc);
    console.log("abc=" + parsedQuery.bcd);

    // if(parsedUrl.pathname == "/public/파일명") {
    //     이런 식으로 처리 
    // }

    //6. 콘솔화면에 로그 종료 부분을 출력
    console.log('--- log end ---');
    
    response.writeHead(200, {'Content-Type':'text/html'});
    response.end('Hello node.js!!');

});

server.listen(8080, function(){
    console.log('Server is running...');
});

```

## 클라이언트 요청(POST) 처리

크롭 앱 postman 
api 테스트 툴 

```javascrpit

var http = require('http');
var querystring = require('querystring');

var server = http.createServer(function(request,response){
  // 1. post로 전달된 데이터를 담을 변수를 선언
  var postdata = '';

  //request.on = 안드로이드의 리스너와 동일한 역할 , data 이벤트 발생시 등록된 callback 함수가 동작 
  // 2. request객체에 on( ) 함수로 'data' 이벤트를 연결
  request.on('data', function (data) {
    // 3. data 이벤트가 발생할 때마다 callback을 통해 postdata 변수에 값을 저장
    console.log("data 이벤트 발생")
    postdata = postdata + data;
  });

  // 4. request객체에 on( ) 함수로 'end' 이벤트를 연결
  request.on('end', function () {
    // 5. end 이벤트가 발생하면(end는 한버만 발생한다) 3번에서 저장해둔 postdata 를 querystring 으로 객체화
    var parsedQuery = querystring.parse(postdata);
    // 6. 객체화된 데이터를 로그로 출력
    console.log(parsedQuery);
    // 7. 성공 HEADER 와 데이터를 담아서 클라이언트에 응답처리
    response.writeHead(200, {'Content-Type':'text/html'});
    response.end('var1의 값 = ' + parsedQuery.aaa);
  });

});

server.listen(8080, function(){
    console.log('Server is running...');
});

```


## module 사용하기 

-  lib, util의 개념
-  var custom = require(폴더명/파일명);으로 불러 쓴다


-  custom_module

```javascript

// 1. exports 객체를 사용해서 sum이라는 변수를 만들고, sum 변수에 function 을 사용해서 하나의 파라미터를 가진 함수식을 대입
exports.sum = function(max) {
    // 2. 입력된 값을 최대값으로 1부터 최대값까지 더해서 반환하는 로직
    return (max+1)*max/2;
}

// 3. var1 변수에 'NEW VALUE 100' 입력
exports.var1 = 'NEW VALUE 100';

exports.getVar1 = function() {
	return exports.var1;
}

``` 

-  module_test

```javascript

var custom = require("./custom_module");

var result = custom.sum(100);

console.log("result=" + result);

console.log("var1 =" + custom.getVar1());

```

-  함수를 부를 때 ()를 붙이지 않으면 코드 주소 영역(코드 내용)을 전체다 호출 해주고 ()를 붙이면 함수를 실행시켜준다


## event 처리

-  [부가 정보](https://javafa.gitbooks.io/nodejs_server_basic/content/chapter7.html)

```javascript

var http = require('http');

// 1. 이벤트가 정의되 있는 events 모듈 생성. 이전 버전의 process.EventEmitter() 는 deprecated!
var EventEmitter = require('events');

// 2. 생성된 이벤트 모듈을 사용하기 위해 custom_object로 초기화
var custom_object = new EventEmitter();

// 3. events 모듈에 선언되어 있는 on( ) 함수를 재정의 하여 'call' 이벤트를 처리 
custom_object.on('call', ()=> {
    console.log('called events!');
});

var server = http.createServer((request, response) => {

		//요청한 url이 /call과 같으면 위에 작성된 call 이벤트를 발생시킨다 
		if(request.url == "/call") {
			//call 이벤트는 비동기로 백그라운드에서 동작하게 된다 
			// custom_object.emit("call");
			//call 함수는 현재 thread에서 동작하게 된다 
			call();
		}

		response.end("");
});

server.listen(10000, ()=> {
	console.log("server is running!! ");
});


```


##  파일입출력

```javascript

var http = require('http');
var url = require('url');
var fs = require('fs');
// 1. 서버생성
var server = http.createServer((request,response)=>{
	var parsedUrl = url.parse(request.url);
	var res = parsedUrl.pathname;

	if(res == "/index.html"){
		// 파일을 읽어서 전송한다.
		fs.readFile('index.html', 'utf-8', (error, data)=>{
		    response.writeHead(200, {'Content-Type':'text/html'});
    		response.end(data);
		});
	}else if(res == "/temp.jpg"){
		// 파일을 읽어서 전송한다. 이미지 등의 바이너리 파일은 읽을 때 캐릭터셋(utf-8) 을 지정하지 않는다
		fs.readFile('temp.jpg', (error, data)=>{
		    response.writeHead(200, {'Content-Type':'image/jpeg'});
    		response.end(data);
		});
	// 요청한 페이지가 없을 경우
	}else{
		response.writeHead(404, {'Content-Type':'text/html'});
    	response.end("<h1>404</h1> Page Not Found!");
	}
});
server.listen(1004,()=>{
	console.log("Server is running...");
});


```



-  index.html

```html

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>my homepage</title>
</head>
<body>

contents, <br/>
<img src="temp.jpg"/>

</body>
</html>

```


## server Complete


-  server_alpha1.js

```javascript

var http = require('http');
var url = require('url');
var fs = require('fs');
// mime 모듈 추가. 서비스하려는 파일의 타입을 알아내기 위해서 필요
var mime = require('mime');

// 1. 서버생성
var server = http.createServer((request,response)=>{
	var parsedUrl = url.parse(request.url);
	var res = parsedUrl.pathname;

	// root 처리
	if(res == "/"){
		res = "/index.html";	
	}

	// 제일앞에 / 를 제거하면 fs.readfile에서 실제 경로상의 파일을 접근할 수 있다
	res = res.substring(1);
	var resMime = mime.lookup(res); // 파일의 mimeType을 가져온다
	
	console.log("mime="+resMime);

	// 요청된 파일의 mime type 이 text/html 일 경우만 처리
	if(resMime == "text/html"){
		// 파일을 읽어서 전송한다.
		fs.readFile(res, 'utf-8', (error, data)=>{
		    response.writeHead(200, {'Content-Type':'text/html'});
    		response.end(data);
		});
	// 그 이외의 mime type은 모두 여기서 처리
	} else {
		// 파일을 읽어서 전송한다. 이미지 등의 바이너리 파일은 읽을 때 캐릭터셋(utf-8) 을 지정하지 않는다
		fs.readFile(res, (error, data)=>{
			if(error){
				response.writeHead(404, {'Content-Type':'text/html'});
	    		response.end("<h1>404 page not found!</h1>");
			}else{
			    response.writeHead(200, {'Content-Type':resMime});
	    		response.end(data);
    		}
		});
	// 요청한 페이지가 없을 경우
	}
});
server.listen(1004,()=>{
	console.log("Server is running...");
});

```


-  index.html

```html

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>my homepage</title>
</head>
<body>

<h1>this is index.html</h1> <br/>

<o1>
	<li><a href="a.html">a.html</a></li>
	<li><a href="new/b.html">b.html</a></li>
	<li><a href="http://localhost:1003/temp.jpg">server_file 서버로 이동 </a></li>
	<li><a href="#" onclick="javascript:alert('hi!')"> popUp </a></li>
</o1>

</body>
</html>

```

-  b.html

```html

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>my homepage</title>
</head>
<body>

contents, <br/>
<img src="temp.jpg"/>

<h1> this is B </h1>



</body>
</html>

```

