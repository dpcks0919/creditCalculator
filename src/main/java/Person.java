import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private int num;
    private String name;
    private String cCredit;
    private String pythonCredit;
    private String rCredit;
    private String regDate;

    public Person() {

    }

    public Person(int num, String name, String cCredit, String pythonCredit, String rCredit, String regDate) {
        this.num = num;
        this.name = name;
        this.cCredit = cCredit;
        this.pythonCredit = pythonCredit;
        this.rCredit = rCredit;
        this.regDate = regDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcCredit() {
        return cCredit;
    }

    public void setcCredit(String cCredit) {
        this.cCredit = cCredit;
    }

    public String getPythonCredit() {
        return pythonCredit;
    }

    public void setPythonCredit(String pythonCredit) {
        this.pythonCredit = pythonCredit;
    }

    public String getrCredit() {
        return rCredit;
    }

    public void setrCredit(String rCredit) {
        this.rCredit = rCredit;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double calculate(){
        double cCredit = translate(this.cCredit);
        double pythonCredit = translate(this.pythonCredit);
        double rCredit = translate(this.rCredit);

        double average = (cCredit + pythonCredit + rCredit) / 3;

        return Math.round(average*100)/100.0;
    }

    public double translate(String credit){
        if(credit.equals("A+")){
            return 4.5;
        }else if(credit.equals("A") || credit.equals("A0")){
            return 4;
        }else if(credit.equals("B+")){
            return 3.5;
        }else if(credit.equals("B") || credit.equals("B0")){
            return 3;
        }else if(credit.equals("C+")){
            return 2.5;
        }else if(credit.equals("C") || credit.equals("C0")){
            return 2;
        }else if(credit.equals("D+")){
            return 1.5;
        }else if(credit.equals("D") || credit.equals("D0")){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.num + ". " + this.name + "의 평균학점은 " + calculate() + " 입니다. \n"
                + "등록일자: " +  this.regDate ;
    }
}