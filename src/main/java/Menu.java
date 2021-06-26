import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
        System.out.println("1. 조회");
        System.out.println("2. 추가");
        System.out.println("3. 수정");
        System.out.println("4. 삭제");
        System.out.println("5. 이름 검색");
        System.out.println("6. 학점 검색");
        System.out.println("0. 종료");
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
                searchByName();
                break;

            case "6":
                searchByGrade();
                break;

            case "0":
                System.out.println("종료");
                return false;

            default:
                System.out.println("잘못된 메뉴 선택");
        }
        return true;
    }

    private void searchByGrade(){

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }

        System.out.println("검색할 학점 입력");
        br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String grade = br.readLine();
            boolean check = false;
            for( Person p : list ){
                if( p.getGrade().equals(grade) ){
                    if(!check){
                        System.out.println("No Name Kor Eng Math Sum Avg Grade RegDate");
                        System.out.println("==========================================");
                        check = true;
                    }
                    System.out.println(p.toString());
                }
            }

            if(!check )
                System.out.println("검색 결과가 없습니다.");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchByName(){


        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }

        System.out.println("검색할 이름 입력");
        br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String name = br.readLine();
            boolean check = false;

            for( Person p : list ){
                if( p.getName().equals(name) ){
                    if(! check  ){
                        System.out.println("No Name Kor Eng Math Sum Avg Grade RegDate");
                        System.out.println("==========================================");
                        check = true;
                    }
                    System.out.println(p.toString());
                }
            }

            if(!check )
                System.out.println("검색 결과가 없습니다.");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteData() {

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }
        for (Person p: this.list) {
            System.out.println(p.getNum() + "번째 학생 이름:" + p.getName());
            System.out.println(" 국어 성적: " + p.getKorScore());
            System.out.println(" 영어 성적: " + p.getEngScore());
            System.out.println(" 수학 성적: " + p.getMathScore());
        }

        System.out.println("삭제할 번호 입력");
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                this.list.remove(num);
                for(int i=0; i < list.size(); i++)
                    this.list.get(i).setNum(i);
                System.out.println("삭제되었습니다.");
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
                System.out.println(" 국어 성적: " + p.getKorScore());
                System.out.println(" 영어 성적: " + p.getEngScore());
                System.out.println(" 수학 성적: " + p.getMathScore());
            }

            System.out.println("수정할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                System.out.println("이름 입력");
                this.list.get(num).setName(br.readLine());
                System.out.println("국어 성적 입력");
                this.list.get(num).setKorScore(Integer.parseInt(br.readLine()));
                System.out.println("영어 성적 입력");
                this.list.get(num).setEngScore(Integer.parseInt(br.readLine()));
                System.out.println("수학 성적 입력");
                this.list.get(num).setMathScore(Integer.parseInt(br.readLine()));
                this.list.get(num).setGrade(calculateGrade(this.list.get(num).getKorScore(), this.list.get(num).getEngScore(), this.list.get(num).getMathScore()));
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String regDate = date.format(formatter);
                this.list.get(num).setRegDate(regDate);
                System.out.println("수정되었습니다.");
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
            System.out.println("국어 성적 입력");
            p.setKorScore(Integer.parseInt(br.readLine()));
            System.out.println("영어 성적 입력");
            p.setEngScore(Integer.parseInt(br.readLine()));
            System.out.println("수학 성적 입력");
            p.setMathScore(Integer.parseInt(br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setNum(this.list.size());
        p.setGrade(calculateGrade(p.getKorScore(), p.getEngScore(), p.getMathScore()));
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String regDate = date.format(formatter);
        p.setRegDate(regDate);

        this.list.add(p);
        System.out.println("추가되었습니다.");
    }

    private void readData() {

        if( this.list.size() == 0 ){
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }

        System.out.println("No Name Kor Eng Math Sum Avg Grade RegDate");
        System.out.println("==========================================");
        for (Person p: this.list) {
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

    private String calculateGrade(int korScore, int engScore, int mathScore){
        int sum = korScore + engScore + mathScore;
        double avg = sum / 3;

        if(avg >= 95){
            return "A+";
        }else if( avg >= 90 ){
            return "A";
        }else if( avg >= 85 ){
            return "B+";
        }else if( avg >= 80 ){
            return "B";
        }else if( avg >= 75 ){
            return "C+";
        }else if( avg >= 70 ){
            return "C";
        }else if( avg >= 65 ){
            return "D+";
        }else if( avg >= 60 ){
            return "D";
        }else{
            return "F";
        }
    }
}
