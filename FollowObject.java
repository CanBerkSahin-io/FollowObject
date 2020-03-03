import edu.cmu.ri.createlab.terk.robot.finch.Finch; //importing the finch API
import java.util.Scanner;
public class FollowObject {
    //Finch myFinch = null;
	public static int Green;
    public static int Red;
    public static int velocityLeft;
    public static int velocityRight;
    public static int turnLeft;
    public static int turnRight;
    public static int Buzz;
    public static int BuzzDuration;
    public static long stopStartTime;
    public static long stopEndTime;
    public static int numOfStops;
    public static long startTime;
    public static long timeTake;
    public static String viewStats;
    public static long stopTime;
    public static int totalSpeed;
    public static int totalCountOfMoves = 0;
    public static int selection;
    public static int averageSpeed;
    static Finch myFinch = new Finch();
   

	public static void main(String[] args) {
        //All of the variables have been declared
       
  Scanner s = new Scanner(System.in);//Scanner to get the input from the user
        Buzz = 300;         // Frequency in Hertz of the tone to be played
        BuzzDuration = 12; // Duration in milliseconds of the tone
        Red = 250;// RGB value of red colour
        Green = 250;// RGB value of green
        velocityLeft = 150;
        velocityRight = 150;
        turnLeft = -50;
        turnRight = -50;
        boolean flag = true;
        numOfStops = 0;
        while (flag) {
         
   //This begins the movement of the finch  
            if (myFinch.isTapped() == true && myFinch.isObstacleLeftSide() == true && myFinch.isObstacleRightSide() == true && myFinch.isObstacle() == true) {
                //LED colours are red, green and blue
                myFinch.setLED(Red, 0, 0);
                myFinch.setWheelVelocities(velocityLeft, velocityRight);
                //Triggers the RunAgain function to true so that the program does not stop in one run so that the Finch will continue to move
                boolean RunAgain = true;
                while (RunAgain) {
                    //Calling the Move method for the Finch movements
                    startTime = System.currentTimeMillis();
                    Move(myFinch);
                    //Inside the while, RunAgain loop , there is a conditional statement that makes the program terminate if the Finch has been tapped twice
                    if (myFinch.isTapped() == true && myFinch.isTapped() == true) {
                        long endTime = System.currentTimeMillis();
                        timeTake = endTime - startTime;
                        break;
                    }
                }
                flag = false;
            }
        }
        System.out.println("Do you want to view the statistics? y/n");
        s.next();
        if (viewStats.equalsIgnoreCase("y")) {
            PrintStatistics();
            System.out.println("Select statistics to print: ");
            selection = s.nextInt();
            switch (selection) {
                case 1:
                    averageSpeed = totalSpeed / totalCountOfMoves;
                    System.out.println("Average Speed: " + averageSpeed);
                    break;
                case 2:
                    travelTime(timeTake);
                    break;
                case 3:
                    totalStopTime(stopTime);
                    break;
                case 4:
                    noOfTimesStopped(numOfStops);
                    break;
                case 5:
                    System.out.println("Average Speed: " + averageSpeed);
                    travelTime(timeTake);
                    totalStopTime(stopTime);
                    totalStopTime(stopTime);
                    break;
            }
        } else {
        }
    }

    public static void Move(Finch myFinch) { // function that will check the movement of the 
     //Move (Finch myfinch)
        if (myFinch.isObstacleRightSide() == false
                && myFinch.isObstacleLeftSide() == false
                && myFinch.isObstacle() == true) {
            MoveStraight(myFinch);
            stopEndTime = System.currentTimeMillis();
            stopTime += stopEndTime - stopStartTime;
        } else if (myFinch.isObstacleRightSide() == false
                && myFinch.isObstacleLeftSide() == true) {
            MoveLeft(myFinch);
            stopEndTime = System.currentTimeMillis();
            stopTime += stopEndTime - stopStartTime;
        } else if (myFinch.isObstacleRightSide() == true
                && myFinch.isObstacleLeftSide() == false) {
            MoveRight(myFinch);
            stopEndTime = System.currentTimeMillis();
            stopTime += stopEndTime - stopStartTime;
        } else if (myFinch.isObstacleRightSide() == true
                && myFinch.isObstacleLeftSide() == true) {
            StopMoving(myFinch);
            stopStartTime = System.currentTimeMillis();
            numOfStops++;
        }
    }
    public static void MoveStraight(Finch myFinch) { // function to move the finch in the straight direction
        myFinch.setLED(0, Green, 0); // calling the LED function to turn the light to green
        myFinch.setWheelVelocities(velocityLeft, velocityRight);// function to set wheel veloctities
        totalSpeed += 150;
        myFinch.buzz(Buzz, BuzzDuration); // function to create a buzz not so loud as the Finch is moving
        totalCountOfMoves++;
    }
    public static void MoveLeft(Finch myFinch) {// function to move the finch in the left direction
        myFinch.setLED(0, Green, 0);// calling the LED function to turn the light to green
        myFinch.setWheelVelocities(turnLeft, velocityRight);// function to set wheel veloctities
        myFinch.buzz(Buzz, BuzzDuration);// function to create a buzz not so loud as the Finch is moving
        totalSpeed += 150;
        totalCountOfMoves++;
    }
    public static void MoveRight(Finch myFinch) {// function to move the finch in the right direction
        myFinch.setLED(0, Green, 0);// calling the LED function to turn the light to green
        myFinch.setWheelVelocities(velocityLeft, turnRight);// function to set wheel veloctities
        myFinch.buzz(Buzz, BuzzDuration);// function to create a buzz not so loud as the Finch is moving
        totalSpeed += 150;
        totalCountOfMoves++;
    }
    public static void StopMoving(Finch myFinch) {// function to stop the finch from moving
        myFinch.setLED(Red, 0, 0);// calling the LED function to turn the light to red
        myFinch.stopWheels();//function to stop the wheels of finch
        myFinch.buzz(Buzz, BuzzDuration); // function to create a buzz not so loud
        totalSpeed += 150;
        totalCountOfMoves++;
    }
    public static void PrintStatistics() { // function to print statistics
        System.out.println("Select the statistic type or enter all to view all statistics:");
        System.out.println("1.Average Speed");
        System.out.println("2.Travel Time");
        System.out.println("3.Stop Time");
        System.out.println("4.Number of stops");
        System.out.println("5.All");
    }
    public void averageSpeed() {
    }
    public static void travelTime(long time) {
        System.out.println("Time taken by finch while moving is given by: " + time + " milliseconds");
    }
    public static void noOfTimesStopped(int noOfTimesStop) {
        System.out.println("The number of times finch stopped is: " + noOfTimesStop);
    }
   
    public static void totalStopTime(long stopTime) {
        System.out.println("The stopping time of the finch is: " + stopTime);
    }
}