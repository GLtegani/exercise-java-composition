import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("Email: ");
        String clientEmail = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String clientBirthday = sc.nextLine();
        LocalDate clientBirthdayFormatted = LocalDate.parse(clientBirthday, formatter);
        Client client = new Client(clientName, clientEmail, clientBirthdayFormatted);

        System.out.println("Enter order data:");
        System.out.print("Status: ");
        String orderStatus = sc.nextLine();
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        LocalDateTime orderMoment = LocalDateTime.now();
        Order order = new Order(orderMoment, status, client);


        System.out.print("How many items to this order? ");
        int quantityItems = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < quantityItems; i++) {
            System.out.println("Enter #" + (1 + i) + " item data:");

            System.out.print("Product name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            Double productPrice = sc.nextDouble();
            Product product = new Product(productName, productPrice);
            System.out.print("Quantity: ");
            Integer productAmount = sc.nextInt();
            sc.nextLine();
            OrderItem orderItem = new OrderItem(productAmount, productPrice, product);

            order.addItem(orderItem);
        }
        System.out.println();
        System.out.print(order);

        sc.close();
    }
}