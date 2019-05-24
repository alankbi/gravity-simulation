import java.util.Scanner;

public class Physics2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Velocity Selector Electric Field Calculator");
        System.out.println("2. Three Physics Haikus");
        System.out.print("Choose an option (1 or 2): ");
        int option = sc.nextInt();
        System.out.println();

        if (option == 1) {
            System.out.println("Velocity Selector Electric Field Calculator");
            System.out.println("\tNote: velocity and magnetic field are assumed to be perpendicular.\n");

            System.out.print("Enter particle speed (m/s): ");
            double speed = Math.abs(sc.nextDouble());

            System.out.print("Enter magnetic field (T): ");
            double magField = Math.abs(sc.nextDouble());

            System.out.print("Show work? (y/n): ");
            String showWork = sc.next().toLowerCase();

            System.out.println();
            double ans = Math.round(speed * magField * 100) / 100.0;

            if (showWork.equals("y") || showWork.equals("yes")) {
                System.out.println("Electric Force = Magnetic Force");
                System.out.println("q * E = q * v x B");
                System.out.println("E = v * B");
                System.out.println("E = " + speed + " m/s * " + magField + " T");
                System.out.println();
            }
            System.out.println("Electric Field Magnitude: " + ans + " N/C");
        } else {
            System.out.println("Three Physics Haikus\n");

            System.out.println("Haiku #1: Circuits");
            System.out.println("How do circuits work?");
            System.out.println("Think water: the wires are tubes");
            System.out.println("Voltage is like pumps");
            System.out.println();

            System.out.println("Haiku #2: Isotherms");
            System.out.println("With a constant temp");
            System.out.println("Internal E is constant");
            System.out.println("Heat in is work by");
            System.out.println();

            System.out.println("Haiku #3: Doppler Effect");
            System.out.println("What is a blue shift?");
            System.out.println("Move away fast, frequency");
            System.out.println("Of waves seems lower");
            System.out.println();
        }
    }
}
