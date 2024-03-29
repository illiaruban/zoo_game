import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
public class Zoo
{
    boolean isOpened = false;
    static Animal[] animals = new Animal[0];
    static float budget;
    float dayIncome;
    boolean beforeTime = false, workTime = false, afterTime = true;
    static Map<String, Integer> dictFoodAmount = new HashMap<>();
    static Map<String, Integer> isInArea = new HashMap<>();
    
    public Zoo() {
        budget = 2500;
    }

    public void zooCommandSystem(Scanner sResp)
    {
        int response;
        while (true)
        {
            System.out.println("You entered Zoo Command System! Choose operation to execute: " + 
            "\n1 - feed animals;\n2 - buy new animal;\n3 - print out/search animals;\n4 - buy food;\n5 - sell animal\\n" +
                                "\n6 - start next day;\n7 - open for visitors\n8 - end day\n0 - exit: ");
            response = sResp.nextInt();
            sResp.nextLine();
            switch (response)
            {
                case 1:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                feedAnimal();
                System.out.println("-------------------------------------------");
                break;

                case 2:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                if (workTime)
                {
                    System.out.println("Operation cancelled due to working time. Please, end day or try operation allowed during work time.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                zooBuyAnimal(sResp);
                System.out.println("-------------------------------------------");
                break;

                case 3:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                System.out.println("Would you like to search some animal in general database or in your zoo?(1 - general catalog/2 - zoo/any other number - exit)");
                int resp = sResp.nextInt();
                sResp.nextLine();
                switch(resp)
                {
                    case 1:
                    System.out.println("-------------------------------------------");
                    Creator.printAnimal(Creator.createdAnimals, sResp);
                    System.out.println("-------------------------------------------");
                    break;

                    case 2:
                    System.out.println("-------------------------------------------");
                    Creator.printAnimal(animals, sResp);
                    System.out.println("-------------------------------------------");
                    break;

                    default:
                    System.out.println("-------------------------------------------");
                    System.out.println("Operation cancelled.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                break;

                case 4:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                if (workTime)
                {
                    System.out.println("Operation cancelled due to working time. Please, end day or try operation allowed during work time.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                buyFood(sResp);
                System.out.println("-------------------------------------------");
                break;

                case 5:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                if (workTime)
                {
                    System.out.println("Operation cancelled due to working time. Please, end day or try operation allowed during work time.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                zooSellAnimal(animals, sResp);
                System.out.println("-------------------------------------------");
                break;

                case 6:
                System.out.println("-------------------------------------------");
                if (beforeTime || workTime)
                {
                    System.out.println("Operation cancelled due to not finished working day. Please, make it to that time to start a new day.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                moveToNext();
                System.out.println("-------------------------------------------");
                break;

                case 7:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                if (workTime)
                {
                    System.out.println("Operation is already launched. Please, end day or try operation allowed during work time.");
                    System.out.println("-------------------------------------------");
                    break;
                }
                else if (afterTime)
                {
                    System.out.println("Operation must be launched after starting a new day. Please, end day or try operation allowed after work time.");
                    break;
                }
                startDay();
                System.out.println("-------------------------------------------");
                break;

                case 8:
                System.out.println("-------------------------------------------");
                if (!isOpened)
                {
                    System.out.println("Zoo is not opened! Please start your first day!");
                    System.out.println("-------------------------------------------");
                    break;
                }
                if (beforeTime)
                {
                    System.out.println("Ending a day must be enrolled after work time.");
                    break;
                }
                if (afterTime)
                {
                    System.out.println("Operation is already launched. Please, end day and start a new one.");
                    break;
                }
                endDay();
                System.out.println("-------------------------------------------");
                break;

                case 0:
                System.out.println("-------------------------------------------");
                System.out.println("You left the command system.");
                System.out.println("-------------------------------------------");
                return;

                default:
                System.out.println("Incorrect input. Please, try again: ");
                break;

            }
        }

    }
    public void zooBuyAnimal(Scanner sResp)
    {
        System.out.println("Printing out animals is enabled.");
        Creator.printAnimal(Creator.createdAnimals, sResp);
        System.out.println("Enter ID to buy an animal: ");
        int keyID;
        Animal object = null;
        if (Creator.createdAnimals.length == 0)
        {
            System.out.println("No animals available for purchase.");
            return;
        }
        while (true)
        {
            keyID = sResp.nextInt();
            sResp.nextLine();
            if (keyID < 0 || keyID > Creator.createdAnimals[Creator.createdAnimals.length - 1].id)
            {
                System.out.println("Wrong ID. Please, try again: ");
                continue;
            }
            break;
        }
        for (Animal animal: Creator.createdAnimals)
        {
            if (animal.id == keyID)
            {
                object = animal;
                break;
            }
        }
        if (budget < object.price)
        {
            System.out.println("Not enough money to purchase an animal.");
            return;
        }
        if (animals.length != 0)
        { 
            for (Animal animal: animals)
            {
                if (animal.name.equals(object.name) && animal.amountInArea == animal.maxAmountInArea)
                {
                    System.out.println("The max amount of animals in the area is reached! Buy an other one!");
                    return;
                }
        }
        }
        budget -= object.price;
        animals = Creator.addToAnimals(animals, object);
        for (Animal animal: animals)
        {
            if (animal.name.equals(object.name))
            {
                animal.amountInArea++;
            }
        }
        System.out.println("Animal was purchased. Correct budget: " + budget);
    }
    public void feedAnimal()
    {
        String value = "";
        System.out.println("Amount of food: ");
        if (dictFoodAmount.isEmpty())
        {
            System.out.println("No food yet! Please, buy some!");
            return;
        }
        for (String key: dictFoodAmount.keySet())
        {
            System.out.println(key + ": " + dictFoodAmount.get(key) + " packs;");
        }
        System.out.println("Process of feeding is launched!");
        for (Animal animal: animals)
        {
            value = Creator.dictFoodSpecifier.get(animal.type);
            if (dictFoodAmount.get(value) != 0)
            {
                dictFoodAmount.put(value, dictFoodAmount.get(value) - 1);
                if (beforeTime)
                    animal.feed(1);
                if (workTime && animal.amountOfFeedings == 3)
                    animal.feed(2);
                else if (afterTime)
                    animal.feed(3);
            }
            else
            {
                System.out.println("ID: " + animal.id + "| Animal was not fed due to lack of food. Animal: " + animal.name);
            }

        }

    }
    public void zooSellAnimal(Animal[] animals, Scanner sResp)
    {
        System.out.println("Printing out animals is enabled.");
        Creator.printAnimal(animals, sResp);
        int keyID;
        Animal animalDeleted = null;
        if (animals.length == 0)
        {
            System.out.println("No animals available for selling.");
            return;
        }
        System.out.println("Enter ID to sell an animal: ");
        while (true)
        {
            keyID = sResp.nextInt();
            sResp.nextLine();
            if (keyID == 0)
            {
                System.out.println("Operation cancelled.");
                return;
            }
            if (keyID < 0 || keyID > animals[animals.length - 1].id)
            {
                System.out.println("Wrong ID. Please, try again(0 - to exit): ");
                continue;
            }
            break;
        }
        for (Animal animal: animals)
        {
            if (animal.id == keyID)
            {
                animalDeleted = animal;
            }
        }
        budget += (animalDeleted.price / 2.0f);
        Creator.deleteFromAnimals(animals, keyID);
        System.out.println("Current budget: $" + budget);
    }
    public void buyFood(Scanner sResp)
    {
        int counter = 0;
        System.out.println("What food would you like to buy?");
        for (String key: Creator.dictFoodPrice.keySet())
        {
            System.out.println((counter + 1) + ". " + key + ": $" + Creator.dictFoodPrice.get(key));
            counter++;
        }
        
        counter = 1;
        int number;
        float price = 0;
        String keyDict = "";
        while (true)
        {
            System.out.println("Enter number(0 - exit): ");
            number = sResp.nextInt();
            sResp.nextLine();
            if (number == 0)
            {
                System.out.println("Operation was cancelled.");
                sResp.close();
                return;
            }
            if (number < 0 || number > Creator.dictFoodSpecifier.size())
            {
                System.out.println("Invalid option. Enter '0' to exit");
                continue;
            }
            break;
        }
        for (String key: Creator.dictFoodPrice.keySet())
        {
            if (counter == number)
            {
                keyDict = key;
                price = Creator.dictFoodPrice.get(key);
                counter++;
                break;
            }
        }
        System.out.println("How many packs would you like to buy?(0 - exit)");
        int packs = 1;
        while (true)
        {
            packs = sResp.nextInt();
            sResp.nextLine();

            if (packs == 0)
            {
                System.out.println("Operation cancelled.");
                return;
            }
            else if (packs < 0)
            {
                System.out.println("Cannot purchase negative amount of packs. Please, try again");
                continue;
            }
            break;
        }

        price *= (float)packs;
        if (budget < price)
        {
            System.out.println("Not enough money to buy the written amount of food. Operation is cancelled.");
            return;
        }
        budget -= price;
        if (!(dictFoodAmount.containsKey(keyDict)))
        {
            dictFoodAmount.put(keyDict, packs);
        }
        else
        {
            dictFoodAmount.put(keyDict, dictFoodAmount.get(keyDict) + packs);
        }
        System.out.println("Purchasing was successful!");
    }

    public void moveToNext()
    {
        System.out.println("You entered the next day. Maybe it is time to buy food and feed animals before work time!");

        float fine = 0.0f;
        System.out.println("List of fines: ");
        for (Animal animal: animals)
        {
           fine += animal.makeFine();
        }
        System.out.println("List of recoverings: ");
        for (Animal animal: animals)
        {
            if (animal.type == Animal.typeAnimal.Bird || animal.type == Animal.typeAnimal.Cat)
            {
                animal.recoverFines();
            }
        }
        System.out.println("Total fine: $" + fine);
        budget -= fine;
        System.out.println("Current budget: $" + budget);
        beforeTime = true;
        afterTime = false;
        isOpened = true;
    }
    public void startDay()
    {
        System.out.println("Day was started, visitors are coming!");

        beforeTime = false;
        workTime = true;

        for (int i = 0; i < animals.length; i++)
        {
            if (animals[i].amountOfFeedings == 3)
            {
                animals[i].hungry = true;
            }
        }
    }
    public void endDay()
    {
        workTime = false;
        afterTime = true;
        int maxAmount, minAmount;
        Random random = new Random();
        if (animals.length < 10)
        {
            minAmount = 7;
            maxAmount = 18;
        }
        else if (animals.length >= 10 && animals.length <= 25)
        {
            minAmount = 24;
            maxAmount = 40;
        }
        else if (animals.length > 25 && animals.length <= 50)
        {
            minAmount = 40;
            maxAmount = 60;
        }
        else
        {
            minAmount = 65;
            maxAmount = 95;
        }
        int visitorsNumber = random.nextInt(maxAmount - minAmount) + minAmount;
        System.out.println("Day was finished, let`s see what you`ve earned:");
          for (Animal animal: animals)
          {
            dayIncome += animal.resultCash(visitorsNumber);
          }

        System.out.println("Your income: $" + dayIncome);

        System.out.println("Do not forget to feed your animals!");

        for (int i = 0; i < animals.length; i++)
        {
            animals[i].hungry = true;
        }



    }
}