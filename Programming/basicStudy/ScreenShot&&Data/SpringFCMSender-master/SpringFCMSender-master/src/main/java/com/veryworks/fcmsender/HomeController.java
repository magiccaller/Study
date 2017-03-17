package com.veryworks.fcmsender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * 주소 -> 주소를파싱 -> 모듈아래단위의 주소 -> @Controller 애너테이션 
	 * @Controller 클래스 -> 
	 * @RequestMapping (value ="/주소") 함수 [로직연산] -> 결과값 -> jsp 페이지 담아서 호출한 측으로 응답
	 */
	@RequestMapping(value = "/sender", method = RequestMethod.POST)
	public String sender(
			  @RequestParam("to_token") String to_token //@RequestParam 애너테이션으로 클라이언트로부터 전달받은 변수를 꺼낸다
			, @RequestParam("msg") String msg
			, @RequestParam("sender") String sender
			,Model model) {
		
		String result = "";

		// 1. fcm 서버정보 세팅
		String fcm_url = "https://fcm.googleapis.com/fcm/send";
		String content_type = "application/json";
		String server_key = "AAAAahBNznU:APA91bE_Wwup7hAH0kdrU9Fql5HIDzm74XWL_yh6wASQXzkjzCeSjyoEvTrWf0k8EideiB5zw9r49DIVDjoTy3J6Bcm301NADwuWG3_XcjgHpQ9L8xWDN6H5O0Ib_ulSM3PAVEyrf-T_";

		// 2. 메시지정보를 클라이언트(핸드폰)로 부터 수신
		// 위의 함수에 정의된 파라미터에서 값을 받게 된다
		String title    = " 보내는사람:"+sender;
		String point = "576434397";

		// 3. fcm 서버로 메시지를 전송
		// 3.1 수신한 메시지를 json 형태로 변경해준다.
		Msg data = new Msg();
		data.setTo(to_token);
		data.getNotification().setTitle(title);
		data.getNotification().setBody(msg);
		data.getNotification().setClick_action("PointActivity");
		data.getData().setPoint(point);
		Gson gson = new Gson();
		
		// Msg 데이터를 json 스트링으로 변경
		String json_string = gson.toJson(data);
		
		try{
			// 3.2 HttpUrlConnection 을 사용해서 FCM서버측으로 메시지를 전송한다
				//a.서버연결
			URL url = new URL(fcm_url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
				//b.header 설정
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization","key="+server_key);
			con.setRequestProperty("Content-Type",content_type);
				//c.POST데이터(body) 전송
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(json_string.getBytes());
			os.flush();
			os.close();
				//d.전송후 결과처리
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){ // code 200
				// 결과처리후 FCM 서버측에서 발송한 결과메시지를 꺼낸다.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String dataLine = "";
				// 메시지를 한줄씩 읽어서 result 변수에 담아두고 
				while((dataLine = br.readLine()) != null){
					result = result + dataLine;
				}
				br.close();
			}
		}catch(Exception e){
			result = e.toString();
		}
		
		// 모델에 결과값을 변수와 함께 담아서 return 에 작성한 문자열로 된 이름을 가진 jsp 파일에 넘겨준다.
		model.addAttribute("r", result);
		
		return "result";
	}
	
}












