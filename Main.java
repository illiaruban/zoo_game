import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        //here read from file
        Creator creator = new Creator("1234567890");
        Scanner sResp = new Scanner(System.in);
        Zoo zoo = new Zoo(); 
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