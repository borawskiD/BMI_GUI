package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GuiClass extends JFrame implements ItemListener, ListSelectionListener, ActionListener {
    JTextField weightInput;
    JTextField heightInput;
    JTextField ageInput;
    JLabel weightLabel;
    JLabel heightLabel;
    JLabel ageLabel;
    JPanel panelLeft;
    JPanel panelRight;
    ButtonGroup buttonGroup;
    JCheckBox male;
    JCheckBox female;
    JButton BMIButton;
    JButton BMRButton;
    String[] activityLevels;
    JList<String> jList;
    JScrollPane jScrollPane;
    int activityLevelValue;
    int genderValue;
    Calculator calculator = new Calculator();

    public GuiClass(){
        super("Kalkulator BMI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,250);

        ageLabel= new JLabel("Wiek");
        panelLeft = new JPanel(new FlowLayout());
        panelRight = new JPanel(new FlowLayout());
        buttonGroup = new ButtonGroup();
        BMIButton = new JButton("BMI");
        BMRButton = new JButton("BMR");
        activityLevels = new String[]{"Siedzący", "Lekka aktywność", "Umiarkowana aktywność", "Bardzo aktywny", "Ekstra aktywność"};
        jList = new JList<>(activityLevels);
        jScrollPane = new JScrollPane(jList);

        weightLabel = new JLabel("Waga");
        panelLeft.add(weightLabel);
        weightInput = new JTextField(3);
        panelLeft.add(weightInput);
        panelLeft.add(new JLabel("kg"));
        heightLabel= new JLabel("Wzrost");
        panelLeft.add(heightLabel);
        heightInput = new JTextField(3);
        panelLeft.add(heightInput);
        ageInput = new JTextField(3);
        panelLeft.add(new JLabel("cm"));
        panelLeft.add(ageLabel, BorderLayout.WEST);
        panelLeft.add(ageInput, BorderLayout.WEST);
        panelLeft.add(new JLabel("lata"));
        panelLeft.setPreferredSize(new Dimension(150,400));
        add(panelLeft, BorderLayout.WEST);

        panelRight.add(new Label("Płeć: "));
        male = new JCheckBox("Mężczyzna");
        female = new JCheckBox("Kobieta");

        male.addItemListener(this);
        female.addItemListener(this);
        buttonGroup.add(male);
        buttonGroup.add(female);
        panelRight.add(male);
        panelRight.add(female);

        panelRight.add(new Label("Styl życia: "));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(this);
        jScrollPane.setPreferredSize(new Dimension(150, 90));
        panelRight.add(jScrollPane);
        panelRight.setPreferredSize(new Dimension(150,400));
        BMIButton.addActionListener(this);
        BMRButton.addActionListener(this);
        panelRight.add(BMIButton);
        panelRight.add(BMRButton);
        add(panelRight);
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox cb = (JCheckBox) e.getItem();
        if(cb.getText().equals("Mężczyzna")){
            genderValue = 1;
        }else{
            genderValue = 0;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       int idx = jList.getSelectedIndex();
       if (idx != -1) activityLevelValue = idx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if(label.equals("BMI")){
            try{
                double weight = Double.parseDouble(weightInput.getText());
                double height = Double.parseDouble(heightInput.getText());
                printDialog(calculator.BMI(weight,height));
            }catch (Exception error){
                System.out.println("Error");
            }
        }else if(label.equals("BMR")){
            try{
                double weight = Double.parseDouble(weightInput.getText());
                double height = Double.parseDouble(heightInput.getText());
                int age = Integer.parseInt(ageInput.getText());
                printDialog(calculator.BMR(weight,height,age,activityLevelValue,genderValue));
            }catch (Exception error){
                System.out.println("Error");
            }
        }
    }
    public void printDialog(String text){
        JDialog d = new JDialog(this, "Wynik", true);
        d.setLayout(new FlowLayout());
        d.add(new Label( text + "\n"));
        Button b = new Button("OK");
        b.addActionListener(e1 -> d.setVisible(false));
        d.add(b);
        d.setSize(350,200);
        d.setVisible(true);
    }

}
