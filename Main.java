import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        Creator creator = new Creator("1234567890");
        File file = new File("creator_info.txt");
        if (file.exists() && file.length() != 0)
        {
            creator.readFromFile();
        }
        else
        {
            System.out.println("Nothing has been created yet.");
        }
        //here read from file
        Scanner sResp = new Scanner(System.in);
        Zoo zoo = new Zoo();

        File file2 = new File("zoo_info.txt");
        if (file2.exists() && file2.length() != 0)
        {
            zoo.readFromFile();
        }
        else
        {
            System.out.println("You must be a beginner! Let`s make some work here!");
        }
        int response;
        while (true)
        {
            System.out.println("Welcome at the Zoo! What would you like to do?");
            System.out.println("1 - enter the creator mode;\n2 - enter the zoo system;\n0 - exit: ");
            response = sResp.nextInt();
            sResp.nextLine();
            switch (response)
            {
                case 1:
                    System.out.println("Please, enter password to get access to creator mode: ");
                    String password = sResp.nextLine();
                    if (!(creator.checkPassword(password)))
                    {
                        System.out.println("Incorrect password.");
                        break;
                    }
                    creator.creatorSystem(sResp);
                    System.out.println("-------------------------------------------");
                    break;

                case 2:
                    System.out.println("-------------------------------------------");
                    zoo.zooCommandSystem(sResp);
                    break;

                case 0:
                    sResp.close();
                    System.out.println("Come back later :)");
                    System.out.println("-------------------------------------------");
                    //here save to file
                    return;

                default:
                    System.out.println("Incorrect input. Please, try again: ");
                    break;
            }
        }



    }

}