import java.security.InvalidParameterException;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Creator {
    private static String creatorPass;
    static Animal[] createdAnimals = new Animal[0];
    static Map<String, String> dictFoodSpec = new HashMap<>();
    //name - name of the food
    static Map<Animal.typeAnimal, String> dictFoodSpecifier = new HashMap<>();
    //type of the food
    static Map<String, Float> dictFoodPrice = new HashMap<>();
    public Creator (String password)
    {
        if (!(Pattern.matches("^([0-9a-zA-Z]){8,16}$", password)))
        {
            throw new InvalidParameterException("Password must meet following conditions:\n- Length between 8 and 16 characters\n- Only alphanumeric characters allowed");
        }
        creatorPass = password; 
    }


    public void creatorSystem(Scanner sResp) {
        int response;
        while (true) {
            System.out.println("You entered the Creator Command System! Choose operation to execute: " +
            "\n1 - print out animals;\n2 - add new animal;\n3 - delete an animal;\n0 - exit: ");
            
            response = sResp.nextInt();
            sResp.nextLine();
            switch (response) {
                case 1:
                    printAnimal(createdAnimals, sResp);
                    System.out.println("-------------------------------------------");
                    break;
    
                case 2:
                    System.out.println("-------------------------------------------");
                    createAnimal(sResp);
                    System.out.println("-------------------------------------------");
                    break;
    
                case 3:
                    System.out.println("-------------------------------------------");
                    System.out.print("Enter the id of the animal to delete it: ");
                    int deleteID;
                    while (true)
                    {
                        deleteID = sResp.nextInt();
                        sResp.nextLine();
                        if (deleteID == 0)
                        {
                            sResp.close();
                            System.out.println("Operation was cancelled.");
                            return;
                        }
                        if (deleteID < 0 || deleteID > createdAnimals[createdAnimals.length - 1].id)
                        {
                            System.out.println("Id out of bounds. Please, try again (enter '0' to exit): ");
                            continue;
                        }
                        break;
                    }
                    deleteFromAnimals(createdAnimals, deleteID);
                    System.out.println("-------------------------------------------");
                    break;
    
                case 0:
                    System.out.println("-------------------------------------------");
                    System.out.println("Logged out from Creator Command System.");
                    System.out.println("-------------------------------------------");
                    return;
                }
        }
    }
    
    public boolean checkPassword(String tryString)
    {
        return tryString.equals(creatorPass);
    }

    public static void searchAnimal(Animal[] animals, Scanner sResp)
    {
        int searchkey;
        boolean stopFilter = false;
        while (true)
        {
            System.out.println("--Please choose filter to search for animals--");
            System.out.println("1 - by first letter of the name;\n2 - by type of the animal\n0 - to see all the animals:");
            searchkey = sResp.nextInt();
            sResp.nextLine();
            switch(searchkey)
            {
                case 1:
                System.out.println("Enter first letter: ");
                String letter = sResp.nextLine().toUpperCase();
                if (!(Pattern.matches("^[a-zA-Z]+$",letter)))
                {
                    System.out.println("Incorrect input. Filter cancelled.");
                    break;
                }
                System.out.println("Animals with letter '" + letter + "': ");
                for (Animal animal : animals)
                {
                    if (animal.name.startsWith(letter))
                    {
                        System.out.println(animal);
                        System.out.println("-------------------------------------------");
                    }
                }
                break;

                case 2:
                System.out.println("--Choose the type--\n1 - Ape\n2 - Reptile\n3 - Bird\4 - Cat (0 - exit the filter): ");
                int typeKey = sResp.nextInt();
                Animal.typeAnimal type = Animal.typeAnimal.Ape;
                switch(typeKey)
                {
                    case 1:
                    type = Animal.typeAnimal.Ape;
                    break;

                    case 2:
                    type = Animal.typeAnimal.Reptile;
                    break;

                    case 3:
                    type = Animal.typeAnimal.Bird;
                    break;

                    case 4:
                    type = Animal.typeAnimal.Cat;
                    break;

                    default:
                    System.out.println("Incorrect type. Operation cancelled.");
                    break;
                }
                for (Animal animal : animals)
                {
                    if (animal.type == type)
                    {
                        System.out.println(animal);
                        System.out.println("-------------------------------------------");
                    }
                }
                break;

                case 0:
                stopFilter = true;
                System.out.println("Filters are stopped.");
                break;

                default:
                System.out.println("-------------------------------------------");
                System.out.println("Incorrect input. Please, try again: ");
                break;
            }
            if (stopFilter)
            {
                break;
            }
        }
    }

    public static void printAnimal(Animal[] animals, Scanner sResp)
    {
        //print animals by using filters or not
        System.out.println("You chose to print out animals. Would you like to apply filters?(1 - yes/0 - no/2 - exit): ");
        int response;
        while (true)
        {
            response = sResp.nextInt();
            sResp.nextLine();
            if (response == 1)
            {
                System.out.println("-------------------------------------------");
                Creator.searchAnimal(animals, sResp);
                System.out.println("-------------------------------------------");
                break;
            }
            else if (response == 0)
            {
                System.out.println("No filters applied.");
                break;
            }
            else if (response == 2)
            {
                System.out.println("-------------------------------------------");
                System.out.println("Operation cancelled.");
                System.out.println("-------------------------------------------");
                return;
            }
            else
            {
                System.out.println("Incorrect input. Please, try again: ");
            }
        }
        System.out.println("Printing out animals in the database: ");
        for (Animal animal: animals)
        {
            System.out.println(animal);
            System.out.println("-------------------------------------------");
        }

    }

    public void createAnimal(Scanner sResp)
    {
        if (createdAnimals.length == 0)
        {
            System.out.println("There are no animals yet! Please create one.");
        }
        else
        {
            for (int i = 0; i < createdAnimals.length; i++)
            {
                System.out.println((i + 1) + " - " + createdAnimals[i]);
            }
        }
        String name;
        float price, visitPrice;
        int amountInArea;
        System.out.println();
        while(true)
        {
            try
            {
                System.out.println("Enter name: ");
                name = sResp.nextLine();
                if (!(Pattern.matches("^[a-zA-Z ]*$",name)))
                {
                    throw new InvalidParameterException("Name should only contain alphanumeric characters");
                }
                
                System.out.println("Enter price: ");
                price = Float.parseFloat(sResp.nextLine());
                if (price < 0)
                {
                    throw new InvalidParameterException("Price cannot be negative number");
                }
                
                System.out.println("Enter visit price: ");
                visitPrice = Float.parseFloat(sResp.nextLine());
                if (visitPrice < 0)
                {
                    throw new InvalidParameterException("Visit price cannot be negative number");
                }

                System.out.println("Enter max amount in area for the animal: ");
                amountInArea = sResp.nextInt();
                if (amountInArea < 0 || amountInArea >= 31)
                {
                    throw new InvalidParameterException("Amount in area cannot be outside the bounds (1-30)");
                }

                Animal animal = determineType(name, price, visitPrice, amountInArea, sResp);
                createdAnimals = addToAnimals(createdAnimals, animal);
                findAFood(animal.type, animal.name);
                System.out.println("Animal was added.");
                break;
            }
            catch(InvalidParameterException e)
            {
                System.out.println("Error: " + e.getMessage());
                String response;
                System.out.println("Would you like to try again('yes'/'no'): ");
                response = sResp.nextLine().toLowerCase();
                if (response == "yes")
                {
                    System.out.println("Operation continues");
                    continue;
                }
                else if (response == "no")
                {
                    System.out.println("Operation cancelled");
                    return;
                }
                else
                {
                    System.out.println("Incorrect response. Operation continues");
                    continue;
                }
            }
        }
    }

    private Animal determineType(String name, float price, float visitPrice, int amountInArea, Scanner sResp)
    {
        System.out.println("--Enter type for an animal--\n1 - Ape;\n2 - Reptile;\n3 - Bird;\n4 - Cat: ");
        
        int response;
        while (true)
        {
            response = sResp.nextInt();
            sResp.nextLine();
            switch(response)
            {
                case 1:
                System.out.print("--Small Ape--\n1 - yes;\n0 - no:");
                int sizeInput;
                while (true)
                {
                    sizeInput = sResp.nextInt();
                    sResp.nextLine();
                    if (sizeInput == 1)
                    {
                        return new Ape(name, price, visitPrice, true, amountInArea);
                    }
                    else if (sizeInput == 0)
                    {
                        return new Ape(name, price, visitPrice,false, amountInArea);
                    }
                    else
                    {
                        System.out.println("Incorrect input for size. Please, try again: ");
                    }
                }
    
                case 2:
                System.out.print("--Poisonous reptile?--\n1 - yes;\n0 - no: ");
                int poisonInput;
                while (true)
                {
                    poisonInput = sResp.nextInt();
                    sResp.nextLine();
                    if (poisonInput == 1)
                    {
                        return new Reptile(name, price, visitPrice, true, amountInArea);
                    }
                    else if (poisonInput == 0)
                    {
                        return new Reptile(name, price, visitPrice,false, amountInArea);
                    }
                    else
                    {
                        System.out.println("Incorrect input for size. Please, try again: ");
                    }
                }
    
                case 3:
                System.out.print("--Carnivore bird?--\n1 - yes;\n0 - no: ");
                int carnivoreInput;
                while (true)
                {
                    carnivoreInput = sResp.nextInt();
                    sResp.nextLine();
                    if (carnivoreInput == 1)
                    {
                        return new Bird(name, price, visitPrice, true, amountInArea);
                    }
                    else if (carnivoreInput == 0)
                    {
                        return new Bird(name, price, visitPrice,false, amountInArea);
                    }
                    else
                    {
                        System.out.println("Incorrect input for size. Please, try again: ");
                    }
                }
                case 4:
                System.out.print("--Aggressive cat?--\n1 - yes;\n0 - no: ");
                int aggressiveInput;
                while (true)
                {
                    aggressiveInput = sResp.nextInt();
                    sResp.nextLine();
                    if (aggressiveInput == 1)
                    {
                        return new Cat(name, price, visitPrice, true, amountInArea);
                    }
                    else if (aggressiveInput == 0)
                    {
                        return new Cat(name, price, visitPrice,false, amountInArea);
                    }
                    else
                    {
                        System.out.println("Incorrect input for size. Please, try again: ");
                    }
                }
    
                default:
                System.out.println("Incorrect input. Please, try again: ");
                break;
            }
        }
    }

    public static Animal[] addToAnimals(Animal[] animals, Animal newAnimal)
    {
        Animal[] newAnimals = new Animal[animals.length + 1];
        for (int i = 0; i < animals.length; i++)
        {
            newAnimals[i] = animals[i];
        }
        newAnimals[animals.length] = newAnimal;
        newAnimals[animals.length].id = animals.length + 1;
        return newAnimals;
    }

    public static void deleteFromAnimals(Animal[] animals, int deleteID)
    {
        int amount = (animals.length - 1);
        Animal[] newAnimals = new Animal[amount];

        for (int i = 0; i < createdAnimals.length; i++)
        {
            if (deleteID == createdAnimals[i].id)
            {
                continue;
            }
            newAnimals[i] = animals[i];
        }
        System.out.println("Animal was deleted from the database.");
        animals = new Animal[newAnimals.length];
        for (int i = 0; i < createdAnimals.length; i++)
        {
            animals[i] = newAnimals[i];
        }

    }

    public void findAFood(Animal.typeAnimal type, String name)
    {
        switch(type)
        {
            case Animal.typeAnimal.Ape:
            if (!(dictFoodSpecifier.containsKey(type)))
            {
                dictFoodSpecifier.put(Animal.typeAnimal.Ape, "Ape Food");
            }
            if (!(dictFoodPrice.containsKey(name)))
            {
                dictFoodPrice.put("Ape Food", 80.0f);
            }
            if (!(dictFoodSpec.containsKey(name)))
            {
                dictFoodSpec.put(name, "Ape Food");
            }
            break;

            case Animal.typeAnimal.Bird:
            if (!(dictFoodSpecifier.containsKey(type)))
            {
                dictFoodSpecifier.put(Animal.typeAnimal.Bird, "Bird Food");
            }
            if (!(dictFoodPrice.containsKey(name)))
            {
                dictFoodPrice.put("Bird Food", 150.0f);
            }
            if (!(dictFoodSpec.containsKey(name)))
            {
                dictFoodSpec.put(name, "Bird Food");
            }
            break;

            case Animal.typeAnimal.Reptile:
            if (!(dictFoodSpecifier.containsKey(type)))
            {
                dictFoodSpecifier.put(Animal.typeAnimal.Reptile, "Reptile Food");
            }
            if (!(dictFoodPrice.containsKey(name)))
            {
                dictFoodPrice.put("Reptile Food", 275.0f);
            }
            if (!(dictFoodSpec.containsKey(name)))
            {
                dictFoodSpec.put(name, "Reptile Food");
            }
            break;

            case Animal.typeAnimal.Cat:
            if (!(dictFoodSpecifier.containsKey(type)))
            {
                dictFoodSpecifier.put(Animal.typeAnimal.Cat, "Cat Food");
            }
            if (!(dictFoodPrice.containsKey(name)))
            {
                dictFoodPrice.put("Cat Food", 500.0f);
            }
            if (!(dictFoodSpec.containsKey(name)))
            {
                dictFoodSpec.put(name, "Cat Food");
            }
            break;

        }

    }
    public void writeToFile()
    {

    }

    public void readFromFile()
    {

    }

}
