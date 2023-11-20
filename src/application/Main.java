package application;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner read = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int numberProduct = read.nextInt();
        read.nextLine();

        for(int i = 1; i <= numberProduct; i++){
            System.out.println("Product #" + i +" data:");
            System.out.print("Common, used or imported (c/ u/ i): ");
            char letter = read.nextLine().toLowerCase().charAt(0);
            System.out.print("Name: ");
            String nameProduct = read.nextLine();
            System.out.print("Price: ");
            Double priceProduct = read.nextDouble();

            if(letter == 'c'){
                productList.add(new Product(nameProduct, priceProduct));
            }
            else if(letter == 'u'){
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                String dateProduct = read.next();
                LocalDate date = LocalDate.parse(dateProduct, fmt);

                productList.add(new UsedProduct(nameProduct, priceProduct, date));
            }
            else if(letter == 'i'){
                System.out.print("Customs fee: ");
                double customsFee = read.nextDouble();

                productList.add(new ImportedProduct(nameProduct, priceProduct, customsFee));
            }
            read.nextLine();
        }

        System.out.println("PRICE TAGS: ");
        for(Product product : productList){
            System.out.println(product.priceTag());
        }

        read.close();
    }
}