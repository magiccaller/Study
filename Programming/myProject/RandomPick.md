#RandomPick

##Main

```java
package com.hyungmin.Randompick;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /** 사람이름 + 벌칙을 입력하고 어레이리스트에 집어넣고 랜덤함수로  
           	난수를 생성해서 생성한 난수와 어레이리스트 인덱스 값이 같으면 당첨!
         */

        Main main  = new Main();
        main.run();
    }

    public void run() {
        PickController pick = new PickController();
        Scanner scan = new Scanner(System.in);

        int num = 0;
        
       
        boolean runFlag = true;
       
        while(runFlag) {
        	print("1부터 5까지 숫자를 입력하세요. 1 = 이름/벌칙 입력, 2= 리스트 출력, 3=리스트 지우기, 4=뽑기, 5= 종료");
        	boolean inputFlag = scan.hasNextInt(); //문자입력시 예외처리미구현
        	switch(num = scan.nextInt()) {
            case 1:
                addList(scan, pick);
                break;
            case 2:
                readList(pick);
                break;
            case 3:
                deleteList(scan, pick);
                break;

            case 4:
                pickPerson(pick);
                break;
            case 5:
                exit();
                runFlag = false;
                break;
            }
        
        	}
        }    
       
    public void addList(Scanner scan, PickController pick){
        RandomPick rdp = new RandomPick();
        print("이름을 입력하세요.");
        scan.nextLine();
        rdp.setName(scan.nextLine());

        print("벌칙 내용을 입력하세요. 다 쓰쎴으면 quit를 입력하세요.");
        boolean keepInput = true;
        String escapeWord = "";
        String inputWord = "";
        while(keepInput) {
            escapeWord = scan.nextLine();
            if(escapeWord.equals("quit")) {
                keepInput = false;
            }
            else {
                inputWord = inputWord + escapeWord + "\n";
            }
            }
        rdp.setContents(inputWord);
        pick.addList(rdp);

        }

    public void readList(PickController pick){
        pick.readList();
    }

    public void deleteList(Scanner scan, PickController pick){ //문자입력시 예외처리 미구현
        print("몇 번째로 입력한 사람인가요? 숫자로 입력하세요. 모르겠으면 리스트를 참고 하세요"); 
        scan.nextLine();
        int ordinal = Integer.parseInt(scan.nextLine());
        pick.deleteList(ordinal);
    }

    public void pickPerson(PickController pick) {
        print("뽑기 시작합니다 두근두근 ");
        RandomPick rdp = pick.pickPerson();
        print("걸린 사람은 " + rdp.getName()+ "할 일은 " + rdp.getContents());
    }

    public void exit() {
        print("종료합니다 ~ ㅂㅂ");
    }

    public void print(String input) {
        System.out.println(input);
    }
}

```

##Controller
```java
package com.hyungmin.Randompick;

import java.util.ArrayList;

public class PickController {
            //addList();

            //readList();

            //deleteList();

            //pickPerson();
    ArrayList<RandomPick> pickList;
    int count;
    public PickController() {
        int count = 0;
        pickList = new ArrayList<RandomPick>();
    }

    public void addList(RandomPick rdp){
        count++;
        rdp.setOrdinal(count);
        pickList.add(rdp);
    }


    public void readList() {
        for(RandomPick item : pickList) {
                System.out.println("이름 : " + item.getName() + " 벌칙 : " + item.getContents() );
            }
        }

    public void deleteList(int ordinal){

        for(RandomPick item : pickList) {
            if(item.getOrdinal() == ordinal-1) {  //객체비교는 오류난다 왜 그럴까? - 직접값비교로 바꿈 
                pickList.remove(item);
            }
        }       
    }

    public RandomPick pickPerson() { //리스트 마지막 사람은 안뽑히는 문제 - double수는 2.9999기 때문인걸로 판명 ->고침 
        int pickNumber = (int)((Math.random()*(pickList.size())+1));
        for(RandomPick item : pickList) {
            if(item.getOrdinal()==pickNumber) {
                return item;
            }
        }
        return null;
    }

}

```

##Class
```java
package com.hyungmin.Randompick;

public class RandomPick {
    private String name;
    private String contents;
    private int pick;   
    private int count;


    public RandomPick(){ 

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public int getPick() {
        return pick;
    }
    public void setPick(int pick) {
        this.pick = pick;
    }

    public int getOrdinal() {
        return count;
    }

    public void setOrdinal(int count) {
        this.count = count;
    }


}   

```
