import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    BufferedReader br;
    private List<Person> list;

    public Menu() {
        this.list = new ArrayList<>();
    }

    public void printMenu() {
        System.out.println("---menu---");
        System.out.println("1. Read");
        System.out.println("2. Create");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.println("----------");
    }

    public boolean menuChoose(String input) {
        switch (input) {

            case "1":
                readData();
                break;

            case "2":
                createData();
                break;

            case "3":
                updateData();
                break;

            case "4":
                deleteData();
                break;

            case "5":
                System.out.println("종료");
                return false;

            default:
                System.out.println("잘못된 메뉴 선택");
        }
        return true;
    }

    private void deleteData() {

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }
        for (Person p: this.list) {
            System.out.println(p.getNum() + "번째 학생 이름:" + p.getName());
            System.out.println(" c언어 성적: " + p.getcCredit());
            System.out.println(" 파이썬 성적: " + p.getPythonCredit());
            System.out.println(" r언어 성적: " + p.getrCredit());
        }
        System.out.println("삭제할 번호 입력");
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                this.list.remove(num);
                for(int i=0; i < list.size(); i++)
                    this.list.get(num).setNum(i);
            }else {
                deleteData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateData() {

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }

        try {
            for (Person p: this.list) {
                System.out.println(p.getNum() + "번째 학생 이름:" + p.getName());
                System.out.println(" c언어 성적: " + p.getcCredit());
                System.out.println(" 파이썬 성적: " + p.getPythonCredit());
                System.out.println(" r언어 성적: " + p.getrCredit());
            }
            System.out.println("수정할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                System.out.println("이름 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setName(br.readLine());
                System.out.println("C언어 성적 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setrCredit(br.readLine());
                System.out.println("파이썬 성적 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setPythonCredit(br.readLine());
                System.out.println("R프로그래밍 성적 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setrCredit(br.readLine());
            } else {
                updateData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createData() {
        Person p = new Person();
        try {
            System.out.println("이름 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setName(br.readLine());
            System.out.println("학점은 대문자로 입력");
            System.out.println("C언어 학점 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setcCredit(br.readLine());
            System.out.println("파이썬 학점 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setPythonCredit(br.readLine());
            System.out.println("R프로그래밍 학점 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setrCredit(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setNum(this.list.size());

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String regDate = date.format(formatter);
        p.setRegDate( regDate );

        this.list.add(p);
    }

    private void readData() {

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }

        System.out.println("학생의 수는 " + this.list.size() + "명 입니다");
        for (Person p:
                this.list) {
            System.out.println(p.toString());
        }
    }

    private boolean valid(int num) {
        // 배열 범위
        if (this.list.size() <= num) {
            System.out.println("없는 번호 입니다.");
            return false;
        }

        return true;
    }
}
