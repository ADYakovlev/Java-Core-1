import dao.UnknownProductException;
import model.Product;
import service.DatabaseInteraction;
import service.JdbcDatabaseInteraction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/*
 *@author Yakovlev Alexandr
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Initialize connect with database...");
            DatabaseInteraction databaseInteraction = new JdbcDatabaseInteraction("jdbc:sqlite:sample_dz.db");
            databaseInteraction.initalize();
            System.out.println("Connection is established");

            showCommandList();
            String command = sc.nextLine();
            while(!command.equals("exit")){
                processCommand(databaseInteraction, command);
                showCommandList();
                command = sc.nextLine();
            }
        } finally {
            sc.close();
        }
    }

    private static void showCommandList(){
        System.out.println("Enter some commands from list:");
        System.out.println("1. /cost <productTitle>");
        System.out.println("2. /changeCost <productTitle> <cost>");
        System.out.println("3. /productByCost <minCost> <maxCost>");
        System.out.println("4. exit");
    }

    private static void processCommand(DatabaseInteraction databaseInteraction, String command){
        try{
            String[] commandWords = command.split("\\s+");
            if(command.startsWith("cost")){
                processCostCommand(databaseInteraction, commandWords[1]);
            }
            else if (command.startsWith("changeCost")){
                processChangeCommand(databaseInteraction, commandWords);
            }
            else if (command.startsWith("productsByCost")){
                processProductsByCommand(databaseInteraction, commandWords);
            }
            else {
                System.out.println("Unknown command!");
            }
        }catch (UnknownProductException e){
            System.out.println(e.getMessage());
        }
    }

    private static void processProductsByCommand (DatabaseInteraction databaseInteraction, String[] commandWords){
        Integer minCost = Integer.parseInt(commandWords[1]);
        Integer maxCost = Integer.parseInt(commandWords[2]);

        List<Product> products = databaseInteraction.getDAO().getProducts(
                "pr",
                String.format("pr.cost BETWEEN %d AND %d",minCost,maxCost));

        System.out.println("Found the fpllowing products:");
        products.forEach(System.out::println);
    }

    private static void processChangeCommand (DatabaseInteraction databaseInteraction, String[] commandWords)throws UnknownProductException{
        String productTitle = commandWords[1];
        BigDecimal cost = new BigDecimal(commandWords[2]);
        databaseInteraction.getDAO().changeProductCost(productTitle, cost);
        System.out.println(String.format("Cost for product %s has been changed. New value is %s", productTitle, cost));
    }

    private static void processChangeCommand (DatabaseInteraction databaseInteraction, String commandWords)throws UnknownProductException {
        String productTitle = commandWords;
        BigDecimal cost = databaseInteraction.getDAO().getProductCostByTitle(productTitle);
        System.out.println(String.format("Product %s has cost %s", productTitle, cost));
    }

    }
