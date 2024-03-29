public class Cat extends Animal{
    boolean aggressive;
    int notFedTwoDays;

    public Cat(String animalName, float price,
        float visitPrice, boolean aggressive, int amountInArea)
    {
        super(animalName, price, visitPrice, amountInArea);
        this.aggressive = aggressive;
        this.maxVisitPrice = visitPrice;
        super.amountOfFeedings = 3;
        super.amountInArea = amountInArea;
        super.type = Animal.typeAnimal.Cat;
        notFedTwoDays = 0;
    }

    public void feed(int timeIndicator)
    {
        System.out.println("Cat " + name + " was fed. It is sleepy time now!");
        if (aggressive)
        {
            System.out.println("Be careful because it likes to play with it`s food!");
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

    @Override
    public String toString()
    {
        String text = "Cat: " + name + "| ID: "+ id  + "\nAggressive: ";
        if (aggressive) {
            text += "Positive";
        } else {
            text += "Negative";
        }
        text += "\nNeeds to be fed: " + amountOfFeedings + " times\n" +
        "Buy/Sell Price: $" + price + "\nVisit Price: $" + visitPrice + "\nMax Amount: " + amountInArea;
        return text;
    }

    public float makeFine()
    {
        if (!fedBefore && !fedDuring && !fedAfter)
        {
            System.out.println("ID: " + id + "| Cat becomes angry! Feed it next time!");
            notFedTwoDays++;

            return maxVisitPrice * 3;
        }

        else if (!fedBefore && !fedDuring && !fedAfter && notFedTwoDays == 1)
        {
            System.out.println("Cat has attacked someone! Watch out for your pets!");
            visitPrice /= 3;
            return (price / 2);
        }
        return 0.0f;
    }

    public void recoverFines()
    {
        if (fedBefore && fedDuring && fedAfter)
        {
            System.out.println("Kitty is happy now and it is ready to give you money!");
            visitPrice = maxVisitPrice;
        }
    }
}
