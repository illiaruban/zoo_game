public class Ape extends Animal
{
    boolean smallOrBig;

    public Ape(String animalName, float price,
               float visitPrice, boolean smallOrBig, int amountInArea)
    {
        super(animalName, price, visitPrice, amountInArea);
        if (smallOrBig)
        {
            amountOfFeedings = 3;
        }
        else
        {
            amountOfFeedings = 2;
        }
        this.smallOrBig = smallOrBig;
        this.type = Animal.typeAnimal.Ape;

    }

    //copy
    public Ape(Ape object)
    {
        super(object);
        this.smallOrBig = object.smallOrBig;
    }
    @Override
    public String toString()
    {
        String text ="Ape: " +  name + " | ID: "+ id  + "\nSmall|Big: ";
        if (smallOrBig) {
            text += "Small";
        } else {
            text += "Big";
        }
        text += "\nNeeds to be fed: " + amountOfFeedings + " times\n" +
                "Buy/Sell Price: $" + price + "\nVisit Price: $" + visitPrice + "\nMax amount: " + maxAmountInArea;
        return text;
    }

    public void feed(int timeIndicator)
    {
        System.out.println("Ape " + name + " was fed. Ape is happy!");
        hungry = false;
        switch(timeIndicator)
        {
            case 1:
                fedBefore = true;
                break;

            case 2:
                fedDuring = true;
                break;

            case 3:
                fedAfter = true;
                break;
        }

    }

    //if ape is not fed a whole day - it will be taken by animal care agency
    public float makeFine()
    {
        //check if zoo was opened
        if (Zoo.isOpened && !fedBefore && !fedDuring && !fedAfter)
        {
            Zoo.animals = Creator.deleteFromAnimals(Zoo.animals, id);
            System.out.println("ID: " + id + "| Ape was taken by animal care agency! Better treat them like alive creatures!");
            return visitPrice * 2.5f;
        }
        return 0.0f;
    }

    public void recoverFines() {}

    public String prepToFile()
    {
        String text = name + ", Ape, "+ id + ", " + price + ", " + visitPrice + ", " + maxVisitPrice + ", " + this.amountInArea + ", " + maxAmountInArea + ", ";
        if (this.smallOrBig)
        {
            text += "1";
        }
        else
        {
            text += "0";
        }
        return text;
    }

}