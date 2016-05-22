/**
 * Created by ���� on 28.01.2016.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Random;



public class LearningEnglish {

    public static void parseText(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String line;

        while ((line = reader.readLine()) != null) {
            String tempE = "";
            String tempR = "";
            line = line.replaceAll("[0-9]", "");
            line = line.replaceAll("\\.", "");
            line = line.replaceAll("\\t", " ");
            line = line.trim();
            String[] words = line.split("\\s");

            if (words.length > 1 && words[1].length() > 1) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].replaceAll("[a-z]", "").isEmpty())
                        tempE += words[i] + " ";
                    else tempR += words[i] + " ";
                }

                tempE = tempE.trim();
                tempR = tempR.trim();

                LearningEnglish.listEnglish.add(tempE);
                LearningEnglish.listRus.add(tempR);
            }
        }

        reader.close();
    }

    public static ArrayList<String> listEnglish = new ArrayList<String>();
    public static ArrayList<String> listRus = new ArrayList<String>();

    public static String word;
    public static String right;
    public static String wrong1;
    public static String wrong2;
    public static String wrong3;

    

    public static void main(String[] args) {
        
        JFrame jFrame = new JFrame();

        jFrame.setVisible(true);


        String file = System.getProperty("user.dir")+"/text.txt";

        try {
            parseText(file);

            int count = listEnglish.size();
         
            while (true)
            {
                int r = new Random().nextInt(count);
                int r1 = new Random().nextInt(count);
                int r2 = new Random().nextInt(count);
                int r3 = new Random().nextInt(count);

               
                word = listEnglish.get(r);
                right = listRus.get(r);
                wrong1 = listRus.get(r1);
                wrong2 = listRus.get(r2);
                wrong3 = listRus.get(r3);

                int nPrint = new Random().nextInt(4);
                int rightVarriant = -1;

                jFrame.setWordLabel(word);

                if (nPrint == 0) {
                    jFrame.setjRadioButton1(right);
                    jFrame.setjRadioButton2(wrong1);
                    jFrame.setjRadioButton3(wrong2);
                    jFrame.setjRadioButton4(wrong3);
                    rightVarriant = 1;
                }

                if (nPrint == 1) {
                    jFrame.setjRadioButton2(right);
                    jFrame.setjRadioButton1(wrong1);
                    jFrame.setjRadioButton3(wrong2);
                    jFrame.setjRadioButton4(wrong3);
                    rightVarriant = 2;
                }

                if (nPrint == 2) {
                    jFrame.setjRadioButton3(right);
                    jFrame.setjRadioButton2(wrong1);
                    jFrame.setjRadioButton1(wrong2);
                    jFrame.setjRadioButton4(wrong3);
                    rightVarriant = 3;
                }

                if (nPrint == 3) {
                    jFrame.setjRadioButton4(right);
                    jFrame.setjRadioButton2(wrong1);
                    jFrame.setjRadioButton3(wrong2);
                    jFrame.setjRadioButton1(wrong3);
                    rightVarriant = 4;
                }

                boolean answer = false;
               
                while (!jFrame.getNext())
                {
                    while (!answer)
                    {
                        jFrame.setConfirmFalse();

                        while (!jFrame.getConfirm()){}

                        if(jFrame.getUserVar() != 0){
                            if (rightVarriant == jFrame.getUserVar()){
                                jFrame.setResultRight();
                                answer = true;
                            }
                            else {
                                jFrame.setResultWrong();
                            }
                        }
                    }

                    jFrame.refreshUserVar();

                }

                jFrame.setResultEmpty();
                jFrame.setRadioAnselect();
                jFrame.setNextFalse();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


