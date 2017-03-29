var http = require('http');
var express = require('express');
var client = require('./lib/realm');

var bodyParser = require('body-parser')

var app = express();

// 일반적인 쿼리스트링 형태를 처리하는 파서 모듈사용
app.use(bodyParser.urlencoded({ extended: false }))

// json 을 처리하는 파서 모듈사용
app.use(bodyParser.json())

// express를 이용한 get 처리
app.get("/user",(request,response)=>{
	readAll(response, NaN, NaN);
});

// express 를 이용한 resful api 처리
app.get("/user/:skip/:offset",(request,response)=>{
	var skip = parseInt(request.params.skip);
	var offset = parseInt(request.params.offset);
	readAll(response, skip, offset);
});

// 입력처리
app.post("/user",(request,response)=>{
	var postdata = request.body;
	createData(response, postdata);
});

http.createServer(app).listen(8080, ()=>{
	console.log('Server is running...');
});

function readAll(response, skip, offset){
	var users = client.UserRealm.objects('User').sorted('date', true);
	console.log(users);
	response.send( {data:users} );
}

function createData(response, data){
	// data의 date 변수값만 서버시간으로 변경해준다.
	data.date = new Date();
	client.UserRealm.write( () => { 
		client.UserRealm.create('User', data);
		send200(response, '등록되었습니다.', 'text/html');
	});
}

// 요청 응답처리
function send200(response, data, mimeType){
	response.writeHead(200, {'Content-Type':mimeType});
	response.end(data);
}

function send404(response){
	response.writeHead(404, {'Content-Type':'text/html'});
	response.end("<h1>404 page not found!</h1>");
}

function send500(response, msg){
	console.log(msg);
	response.writeHead(500, {'Content-Type':'text/html'});
	response.end('500 Server internal error.');
}