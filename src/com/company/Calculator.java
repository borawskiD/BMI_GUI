package com.company;

import java.util.Scanner;

public class Calculator {
    public void run(){
        double weight = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Kalkulator BMMI I BMR");
        System.out.println("Podaj wage:");
        weight = sc.nextDouble();
        System.out.println("Podaj wzrost:");
        double height = sc.nextDouble();
        BMI(weight,height/100);
        System.out.println("Podaj plec:");
        System.out.println("1. Mezczyzna");
        System.out.println("2. Kobieta");
        int gender = sc.nextInt();
        System.out.println("Podaj swoj wiek:");
        int age = sc.nextInt();
        System.out.println("Podaj swoja aktywnosc fizyczna (cyfra)");
        System.out.println("1. Siedzacy");
        System.out.println("2. Lekko aktywny");
        System.out.println("3. Umiarkowanie aktywny");
        System.out.println("4. Bardzo aktywny");
        System.out.println("5. Ekstra aktywny");
        int activity = sc.nextInt();
        BMR(weight, height, age, activity, gender);
    }
    public String BMI(double weight, double height){
        height = height/100;
        double BMI = weight/(height * height);
        BMI = Math.round(BMI * 100)/100;
        String output = "BMI: " + BMI + "\n";
        System.out.println("Twoje BMI: " + BMI);
        if (BMI < 16) output+=("Twoje BMI wskazuje na wyglodzenie");
        else if (BMI >= 16 && BMI < 16.99) output+=("Twoje BMI wskazuje na wychodzenie");
        else if (BMI >= 17 && BMI < 18.49) output+=("Twoje BMI wskazuje na niedowaga");
        else if (BMI >= 18.5 && BMI < 24.99) output+=("Twoje BMI wskazuje na optymalna wage");
        else if (BMI >= 25 && BMI < 29.99) output+=("Twoje BMI wskazuje na nadwage");
        else if (BMI >= 30 && BMI < 34.99) output+=("Twoje BMI wskazuje na otylosc Ist");
        else if (BMI >= 35 && BMI < 39.99) output+=("Twoje BMI wskazuje na otylosc IIst");
        else if (BMI >= 40) output+=("Twoje BMI wskazuje na otylosc IIIst");
        return output;
    }
    public String BMR(double weight, double height, int age, int activity, int gender){
        double BMR = 0;
        double multipler = 1;
        if(gender == 1){
            BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        }else{
            BMR = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
        switch(activity){
            case 1 -> multipler = 1.2;
            case 2 -> multipler = 1.375;
            case 3 -> multipler = 1.55;
            case 4 -> multipler = 1.725;
            case 5 -> multipler = 1.9;

        }
        BMR = BMR * multipler;
        return ("Twoje BMR wynosi: " + BMR);
    }
}
