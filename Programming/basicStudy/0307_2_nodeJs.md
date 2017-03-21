
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
