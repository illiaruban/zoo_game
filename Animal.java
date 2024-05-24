import java.security.InvalidParameterException;
import java.util.regex.Pattern;
public abstract class Animal
{
    public String name;
    public int amountInArea = 0, maxAmountInArea;
    public int amountOfFeedings;
    public int id;
    public float price;
    public float visitPrice, maxVisitPrice;
    boolean fedBefore = false, fedAfter = false, fedDuring = false;
    boolean hungry = true;
    enum typeAnimal
    {
        Ape, Reptile, Bird, Cat
    }
    typeAnimal type;
    //correct usage in constructor
    public Animal(String animalName, float price,
                  float visitPrice, int maxAmountInArea)
    {
        //check for right type
        if (!(Pattern.matches("^[a-zA-Z ]*$",animalName)))
        {
            throw new InvalidParameterException("Name should only contain alphabetic characters");
        }
        this.name = animalName;
        if (price <= 0  || visitPrice <= 0)
        {
            throw new InvalidParameterException("Prices for an animal cannot be negative.");
        }
        this.price = price;
        this.visitPrice = visitPrice;
        this.amountInArea = 0;
        this.maxAmountInArea = maxAmountInArea;
    }
    //copy
    public Animal(Animal object)
    {
        this.name = object.name;
        this.price = object.price;
        this.visitPrice = object.visitPrice;
        this.amountOfFeedings = object.amountOfFeedings;
        this.maxAmountInArea = object.maxAmountInArea;
    }

    public abstract void feed(int timeIndicator);

    public float resultCash(float visitorsNumber)
    {
        return (visitPrice * visitorsNumber);
    }

    public abstract float makeFine();

    public abstract void recoverFines();


    public abstract String prepToFile();

    public static Animal constructAnimal(String info)
    {
        String[] parts = info.split(", ");
        String name = parts[0];
        int id = Integer.parseInt(parts[2]);
        float price = Float.parseFloat(parts[3]);
        float visitPrice = Float.parseFloat(parts[4]);
        float maxVisitPrice = Float.parseFloat(parts[5]);
        int amountInArea = Integer.parseInt(parts[6]);
        int maxAmountInArea = Integer.parseInt(parts[7]);
        int spec = Integer.parseInt(parts[8]);
        boolean speciality = false;
        if (spec == 1)
        {
            speciality = true;
        }
        Animal animal = null;
        switch(parts[1])
        {
            case "Ape":
                animal = new Ape(name, price, visitPrice, speciality, maxAmountInArea);
                break;

            case "Reptile":
                animal = new Reptile(name, price, visitPrice, speciality, maxAmountInArea);
                break;

            case "Bird":
                animal = new Bird(name, price, visitPrice, speciality, maxAmountInArea);
                break;

            case "Cat":
                animal = new Cat(name, price, visitPrice, speciality, maxAmountInArea);
                break;
        }
        animal.amountInArea = amountInArea;
        animal.maxVisitPrice = maxVisitPrice;
        animal.id = id;
        return animal;
    }

}