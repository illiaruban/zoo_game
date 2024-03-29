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
    static enum typeAnimal
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
    public abstract void feed(int timeIndicator);

    public float resultCash(float visitorsNumber)
    {
        return (visitPrice * visitorsNumber);
    }

    public abstract float makeFine();

    public abstract void recoverFines();
}