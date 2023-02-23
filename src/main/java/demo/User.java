package demo;

import java.util.ArrayList;
import java.util.List;

import org.h2.result.ResultColumn;

public class User {

    private String name;
    private int age;
    private List<Integer> membershipYears = new ArrayList<Integer>();
    private String email;
    private String password;

    public User(String name, int age, String email, String password) {
        this.name = name;
        if (age >= 0) 
            this.age = age;
        if(email.contains("@")){
            this.email=email;
        }
        if(password.trim()==""){this.password="@$-t&%#";}
        else{this.password="@$-"+password.trim()+"&%#";}
    }

    public int countMembershipYearsAfter1999 () {
        int result = 0;
        for(Integer year: membershipYears) {
            if (year > 1999)
                result++;
        }
        return result;
    }

    public int countYearsOfMembership () {
        return membershipYears.size();
    }

    public void addMembershipYear (int year) {
        membershipYears.add(year);
    }

    public int getAge() {
        return this.age;
    }

    public String getName () {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getFirstMembershipYear(){
        if(membershipYears.size()==0){
            return 0;
        }
        else{Integer result=membershipYears.get(0);
        for(Integer year:membershipYears){
            if(year<result){result=year;}
        }
        return result;
        
    }
    }

    public int getNumberOfMembershipYearsIn2000(){
        int result=0;
        for(Integer year:membershipYears){
            if(year>=2000&&year<3000){
                result++;
            }
        }
        return result;
    }

    public boolean isPasswordCorrect(String pass){
        if(pass.equals(password))return true;
        else return false;
    }

    public String toString(){
        return name+" is "+age+" years old and has as email "+email;
    }

    public boolean hasMembershipYear(int year){
        for(Integer year2:membershipYears){
            if(year==year2)return true;
        }
        return false;
    }
}