public class Bird extends Animal{
    boolean carnivore;
    float maxVisitPrice;

    public Bird(String animalName, float price,
    float visitPrice, boolean carnivore, int amountInArea)
    {
        super(animalName, price, visitPrice, amountInArea);
        this.carnivore = carnivore;
        this.maxVisitPrice = visitPrice;
        amountOfFeedings = 3;
        super.amountInArea = amountInArea;
        super.type = Animal.typeAnimal.Bird;
    }

    @Override
    public String toString()
    {
        String text = "Bird: " + name + "| ID: "+ id  + "\nMeal Type: ";
        if (carnivore) {
            text += "Carnivore";
        } else {
            text += "Herbivore";
        }
        text += "\nNeeds to be fed: " + amountOfFeedings + " times\n" +
        "Buy/Sell Price: $" + price + "\nVisit Price: $" + visitPrice + "\nMax amount: " + amountInArea;
        return text;
    }

    public void feed(int timeIndicator)
    {
        System.out.println("Bird " + name + " was fed.");
        if (carnivore)
        {
            System.out.println("But it wants more meat!");
        }
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
    //if bird is not fed 2 days - it does not bring any cash and cuts other birds cash
    public float makeFine()
    {
        if ( !fedBefore && !fedDuring && !fedAfter)
        {
            System.out.println("ID: " + id + "| Bird has not be fed today - it is not pleasant with this kind of meal, so it does not bring you money!");
            visitPrice = 0;
            return maxVisitPrice * 5;
        }
        return 0.0f;
    }

    public void recoverFines()
    {
        if (fedBefore && fedDuring && fedAfter)
        {
            System.out.println("ID: " + id + "| Bird was fed and now continues to bring you money again!");
            visitPrice = maxVisitPrice;
        }
    }
}


