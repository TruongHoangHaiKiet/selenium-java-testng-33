package javaBasic;

public class AndOrNot {
    public static void main(String[] args) {
        boolean actorA;
        boolean actorB;
        boolean status;

        /*======================AND=====================*/
        actorA = true;
        actorB = false;
        status = actorA && actorB;
        System.out.println("A đúng and B sai = " + status);

        actorA = false;
        actorB = true;
        status = actorA && actorB;
        System.out.println("A sai and B đúng = " + status);

        actorA = true;
        actorB = true;
        status = actorA && actorB;
        System.out.println("A đúng and B đúng = " + status);

        actorA = false;
        actorB = false;
        status = actorA && actorB;
        System.out.println("A sai and B sai = " + status);

        /*======================OR=====================*/
        actorA = true;
        actorB = false;
        status = actorA || actorB;
        System.out.println("A đúng and B sai = " + status);

        actorA = false;
        actorB = true;
        status = actorA || actorB;
        System.out.println("A sai and B đúng = " + status);

        actorA = true;
        actorB = true;
        status = actorA || actorB;
        System.out.println("A đúng and B đúng = " + status);

        actorA = false;
        actorB = false;
        status = actorA || actorB;
        System.out.println("A sai and B sai = " + status);

        String browserName = "Firefox";
        if(!browserName.equals("chrome")){
            System.out.println("Browser name is FIREFOX");
        }
    }
}
