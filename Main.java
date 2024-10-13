
import java.util.*;
public class Main {
    static Scanner input = new Scanner (System.in);
    static String userName = "admin";
    static String password = "123";
    static String[][] supplierDetails = new String[0][2];
    static String[] itemCategory = new String[0];
    static Object[][] supplierItem = new Object[0][6];
    static int v = 0;


    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
//handle the exception
            System.err.println(e.getMessage());
        }
    }

    public static void sortUnitPrice() {
        Object[][] temp = new Object[supplierItem.length][6];

        for (int j = 0; j < supplierItem.length - 1; j++) {
            for (int i = 0; i < supplierItem.length - 1; i++) {
                if(((Double) supplierItem[i][3]) > ((Double) supplierItem[i + 1][3])){
                    for(int x =0; x<supplierItem[i].length; x++){
                        temp[0][x] = supplierItem[i][x];
                        supplierItem[i][x] = supplierItem[i + 1][x];
                        supplierItem[i+1][x] = temp[0][x];
                    }
                }
            }
        }

        System.out.println("\n+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");
        System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|   	%-15s|%n", " SID" , "CODE" , "DESC" , "PRICE" , "QTY" , "CAT");
        System.out.println("+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");

        for(int h=0; h<supplierItem.length;h++){
            System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|   	%-15s|%n",supplierItem[h][0] , supplierItem[h][1] ,supplierItem[h][2] , supplierItem[h][3], supplierItem[h][4] , supplierItem[h][5]);
        }
        System.out.println("+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");
    }

    public static void rankItemsPerUnitPrice(){

        System.out.println("\n+-----------------------------------------------+");
        System.out.println("|		  RANKED UNIT PRICE 		|");
        System.out.println("+-----------------------------------------------+");

        sortUnitPrice();

        System.out.print("\nDo you want to go stock manage page?(Y/N) ");
        String choose = input.next();
        switch(choose){
            case "y" :
                clearConsole();
                stockManage();
                break;
            case "Y" :
                clearConsole();
                stockManage();
                break;
            case "n" :
                clearConsole();
                rankItemsPerUnitPrice();
                break;
            case "N" :
                clearConsole();
                rankItemsPerUnitPrice();
                break;
            default :
        }
    }

    public static void viewItems(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      VIEW ITEMS 			|");
        System.out.println("+-------------------------------------------------------+");

        for(int i = 0 ; i < itemCategory.length; i++){

            System.out.println("\n"+itemCategory[i] + ":");
            System.out.println("+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");
            System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|%n", " SID" , "CODE" , "DESC" , "PRICE" , "QTY");
            System.out.println("+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");

            for(int j = 0; j<supplierItem.length; j++){
                if(itemCategory[i].equals(supplierItem[j][5])){
                    System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|%n",supplierItem[j][0] , supplierItem[j][1] ,supplierItem[j][2] , supplierItem[j][3], supplierItem[j][4] );
                }
            }
            System.out.println("+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+");
        }

        System.out.print("\nDo you want to go stock manage page?(Y/N) ");
        String choose = input.next();

        if(choose.equals( "y" ) || choose.equals( "Y" )){
            clearConsole();
            stockManage();
        }else if (choose.equals( "n" ) || choose.equals( "N" )){
            clearConsole();
            viewItems();
        }
    }

    public static void getItemsSupplierWise(){
        String id;

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      SEARCH SUPPLIER 			|");
        System.out.println("+-------------------------------------------------------+");

        while(true){

            System.out.print("\nEnter Supplier ID: ");
            id = input.next();

            for(int x = 0 ; x< supplierItem.length; x++){
                if(id.equals(supplierItem[x][0])){
                    for(int i=0; i<supplierDetails.length; i++){
                        if(id.equals(supplierDetails[i][0])){
                            System.out.println("Supplier Name: " + supplierDetails[i][1]);
                        }
                    }

                    System.out.println("\n+----------------------+-----------------------+-----------------------+----------------------+------------------------+");
                    System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|%n", "ITEM CODE" , "DESCRIPTION" , "UNIT PRICE" , "QTY ON HAND" , "CATEGORY");
                    System.out.println("+----------------------+-----------------------+-----------------------+----------------------+------------------------+");

                    for(int j = 0 ; j < supplierItem.length; j++){
                        if(id.equals(supplierItem[j][0])){
                            System.out.printf("|   	%-15s|   	%-15s|  	%-15s|   	%-15s|   	%-15s|%n", supplierItem[j][1], supplierItem[j][2] , supplierItem[j][3] , supplierItem[j][4], supplierItem[j][5]);
                        }
                    }
                    System.out.println("+----------------------+-----------------------+-----------------------+----------------------+------------------------+");
                    System.out.print("\nSearch successfully! Do you want to another search?(Y/N) ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        getItemsSupplierWise();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        stockManage();
                    }
                }
            }
            System.out.println("Please enter a valid supplier id.");
        }
    }

    public static void growSupplierItem(){
        Object[][] temp = new Object[supplierItem.length+1][6];

        for (int i = 0; i < supplierItem.length; i++){
            for(int j = 0; j <supplierItem[i].length; j++){
                temp[i][j] = supplierItem[i][j];
            }
        }
        supplierItem = temp;
    }

    public static void iteAdd(){
        String iCode;

        while (true) {
            System.out.print("\nItem Code	: ");
            iCode = input.next();

            boolean iCodeExists = false;
            for (int i = 0; i < supplierItem.length; i++) {
                if (iCode.equals(supplierItem[i][1])) {
                    iCodeExists = true;
                    System.out.println("Item code already exists. Try another item code!");
                    break;
                }
            }
            if (!iCodeExists) {
                break;
            }
        }

        growSupplierItem();
        supplierItem[v][1] = iCode;

        System.out.println("+-------------+------------------+------------------+");
        System.out.println("|	#     |   SUPPLIER ID    |   SUPPLIER NAME  |");
        System.out.println("+-------------+------------------+------------------+");

        for (int i = 0; i < supplierDetails.length; i++) {
            System.out.printf("|	%-6s|   %-15s|   %-15s|%n",i+1, supplierDetails[i][0], supplierDetails[i][1]);
        }
        System.out.println("+-------------+------------------+------------------+");

        while(true){
            System.out.print("\nEnter the supplier number > ");
            int supNo = input.nextInt();


            for ( int g = 0 ; g < supplierDetails.length; g++){
                if(supNo == g+1){

                    supplierItem[v][0] = supplierDetails[supNo-1][0];

                    System.out.println("\nItem Categories: ");

                    System.out.println("+-------------+------------------+");
                    System.out.println("|	#     |   CATEGORY NAME  |");
                    System.out.println("+-------------+------------------+");

                    for (int i = 0; i < itemCategory.length; i++) {
                        System.out.printf("|	%-6s|   %-15s|%n",i+1, itemCategory[i]);
                    }
                    System.out.println("+-------------+------------------+");


                    System.out.print("\nEnter the category number > ");
                    int categoryNo = input.nextInt();
                    supplierItem[v][5] = itemCategory[categoryNo-1];

                    System.out.print("\nDescription : ");
                    String des = input.next();
                    supplierItem[v][2] = des;

                    System.out.print("Unit price : ");
                    double unitPrice = input.nextDouble();
                    supplierItem[v][3] = unitPrice;

                    System.out.print("Qty on hand : ");
                    int qtyOnHand = input.nextInt();
                    supplierItem[v][4] = qtyOnHand;

                    v++;
                    System.out.print("added successfully! Do you want ot add another Item(Y/N)? ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        addItem();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        stockManage();
                    }
                }
            }
            System.out.println("Enter a valid supplier number!");
        }
    }

    public static void addItem(){

        System.out.println("\n+-----------------------------------------------+");
        System.out.println("|		      ADD ITEM 			|");
        System.out.println("+-----------------------------------------------+");

        if(supplierDetails.length == 0){
            System.out.println("\nOOPS! It seems that you dont' t have any supplier in the system.");
            System.out.print("Do you want to add a new supplier?(Y/N) ");
            String choose = input.next();

            if(choose.equals( "y" ) || choose.equals( "Y" )){
                clearConsole();
                addSupplier();
            }else if (choose.equals( "n" ) || choose.equals( "N" )){
                clearConsole();
                stockManage();
            }

        }

        if(itemCategory.length == 0 ){
            System.out.println("\nOOPS! It seems that you dont' t have any category in the system.");
            System.out.print("Do you want to add a new category?(Y/N) ");
            String choose = input.next();

            if(choose.equals( "y" ) || choose.equals( "Y" )){
                clearConsole();
                addNewItemCategory();
            }else if (choose.equals( "n" ) || choose.equals( "N" )){
                clearConsole();
                stockManage();
            }
        }
        iteAdd();
    }

    public static void updateItemCategory(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       UPDATE ITEM CATEGORY		|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nEnter category: ");
            String category = input.next();

            for (int i = 0; i < itemCategory.length; i++) {
                if (category.equals(itemCategory[i])) {

                    System.out.print("\nEnter the new category name : ");
                    String name = input.next();

                    itemCategory[i] = name;

                    System.out.print("Updated Successfully! Do you want to update another category?(Y/N) ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        updateItemCategory();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        manageItemCategories();

                    }
                    break;
                }

            }
            System.out.println("can' t find category name. try again!");
        }

    }

    public static void deleteItemCat(String name){
        String[] temp = new String[itemCategory.length-1];

        int newRow = 0;
        for (String row : itemCategory) {
            if (!row.equals(name)) {
                temp[newRow] = row;
                newRow++;
            }
        }
        itemCategory = temp;
    }

    public static void deleteItemCategory(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       DELETE ITEM CATEGORY		|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nEnter a category name: ");
            String name = input.next();

            for(int i=0; i<itemCategory.length; i++){
                if(name.equals(itemCategory[i])){

                    deleteItemCat(name);

                    System.out.print("deleted successfully! Do you want to delete another category?(Y/N) ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        deleteItemCategory();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        manageItemCategories();
                    }
                }
            }
            System.out.println("can't find category. try again!");
        }
    }

    public static void growItemCategoryArray(){
        String[] temp = new String[itemCategory.length+1];

        for (int i = 0; i < itemCategory.length; i++){
            temp[i] = itemCategory[i];
        }
        itemCategory = temp;
    }

    public static void addNewItemCategory(){
        growItemCategoryArray();

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      ADD ITEM CATEGORY 		|");
        System.out.println("+-------------------------------------------------------+");

        String category;

        while (true) {
            System.out.print("\nEnter the new item category: ");
            category = input.next();

            boolean categoryExists = false;
            for (int i = 0; i < itemCategory.length; i++) {
                if (category.equals(itemCategory[i])) {
                    categoryExists = true;
                    System.out.println("Category already exists. Try another category!");
                    break;
                }
            }

            if (!categoryExists) {
                break;
            }
        }

        itemCategory[itemCategory.length-1] = category;

        System.out.print("added successfully! Do you want to add another category (Y/N)? ");
        String choose = input.next();

        if (choose.equals("Y") || choose.equals("y")) {
            clearConsole();
            addNewItemCategory();
        } else if (choose.equals("N") || choose.equals("n")) {
            clearConsole();
            stockManage();
        }
    }

    public static void manageItemCategories(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      MANAGE ITEM CATEGORY 		|");
        System.out.println("+-------------------------------------------------------+");

        System.out.println("\n[1] Add New Item Category		[2] Delete Item Category");
        System.out.println("[3] Update Item Category 		[4] Stock Management");

        System.out.print("\nEnter an option to continue > ");
        int chooseOption = input.nextInt();

        switch(chooseOption){

            case 1:
                clearConsole();
                addNewItemCategory();
                break;
            case 2:
                clearConsole();
                deleteItemCategory();
                break;
            case 3:
                clearConsole();
                updateItemCategory();
                break;
            case 4:
                clearConsole();
                stockManage();
                break;
            default:
        }
    }

    public static void stockManage(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      STOCK MANAGEMENT 			|");
        System.out.println("+-------------------------------------------------------+");

        System.out.println("\n[1] Manage Item Categories		[2] Add Item");
        System.out.println("[3] Get Items Supplier Wise 		[4] View Items");
        System.out.println("[5] Rank Items Per Unit Price 		[6] Home Page");

        System.out.print("\nEnter an option to continue > ");
        int chooseOption = input.nextInt();

        switch(chooseOption){

            case 1:
                clearConsole();
                manageItemCategories();
                break;
            case 2:
                clearConsole();
                addItem();
                break;
            case 3:
                clearConsole();
                getItemsSupplierWise();
                break;
            case 4:
                clearConsole();
                viewItems();
                break;
            case 5:
                clearConsole();
                rankItemsPerUnitPrice();
                break;
            case 6:
                clearConsole();
                homePage();
                break;
            default:

        }
    }

    public static void searchSupplier(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		      SEARCH SUPPLIER 			|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nSupplier ID : ");
            String id = input.next();

            for(int i = 0 ; i < supplierDetails.length; i++){
                if(id.equals(supplierDetails[i][0])){
                    System.out.println("Supplier Name : " + supplierDetails[i][1]);
                    System.out.print("added successfully! Do you want to add another find(Y/N)? ");
                    String choose = input.next();

                    if(choose.equals("Y") || (choose.equals("y"))){
                        clearConsole();
                        searchSupplier();
                    }else if(choose.equals("N") || (choose.equals("n"))){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        searchSupplier();
                    }
                }
            }

            System.out.println("can't find supplier id. try again!");
        }
    }

    public static void viewSuppliers(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       VIEW SUPPLIER			|");
        System.out.println("+-------------------------------------------------------+");


        System.out.println("+----------------------+-----------------------+");
        System.out.printf("|   	%-15s|   	%-15s|%n", "SUPPLIER ID" , "SUPPLIER NAME");

        //~ System.out.println("|   SUPPLIER ID        |   SUPPLIER NAME       |");
        System.out.println("+----------------------+-----------------------+");

        for (int i = 0; i < supplierDetails.length; i++) {
            System.out.printf("|   	%-15s|   	%-15s|%n", supplierDetails[i][0], supplierDetails[i][1]);
        }

        System.out.println("+----------------------+-----------------------+");

        System.out.print("\nDo you want to go supplier manage(Y/N)? ");
        String choose = input.next();

        if(choose.equals( "y" ) || choose.equals( "Y" )){
            clearConsole();
            supplierManage();
        }else if (choose.equals( "n" ) || choose.equals( "N" )){
            clearConsole();
            viewSuppliers();
        }else{
            clearConsole();
            supplierManage();
        }
    }

    public static void delete(String id){
        String[][]temp=new String[supplierDetails.length-1][2];

        int newRow = 0;
        for (String[] row : supplierDetails){
            if (!row[0].equals(id)) {
                for (int i=0; i<2; i++){
                    temp[newRow][i]=row[i];
                }
                newRow++;
            }
        }
        supplierDetails=temp;
    }

    public static void deleteSupplier(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       DELETE SUPPLIER			|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nSupplier ID : ");
            String id = input.next();

            for(int i=0; i<supplierDetails.length; i++){
                if(id.equals(supplierDetails[i][0])){

                    delete(id);

                    System.out.print("deleted successfully! Do you want to delete another?(Y/N) ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        deleteSupplier();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        deleteSupplier();
                    }
                }
            }
            System.out.println("can't find supplier id. try again!");
        }
    }

    public static void updateSupplier(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       UPDATE SUPPLIER			|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nSupplier ID : ");
            String id = input.next();

            for (int i = 0; i < supplierDetails.length; i++) {
                if (id.equals(supplierDetails[i][0])) {

                    System.out.println("Supplier name : " + supplierDetails[i][1]);
                    System.out.print("\nEnter the new supplier name : ");
                    String name = input.next();

                    supplierDetails[i][1] = name;

                    System.out.print("Updated Successfully! Do you want to update another supplier?(Y/N) ");
                    String choose = input.next();

                    if(choose.equals( "y" ) || choose.equals( "Y" )){
                        clearConsole();
                        updateSupplier();
                    }else if (choose.equals( "n" ) || choose.equals( "N" )){
                        clearConsole();
                        supplierManage();

                    }
                    break;
                }

            }
            System.out.println("can' t find supplier id. try again!");
        }
    }

    public static void grow(){
        String[][] temp = new String[supplierDetails.length+1][2];

        for (int i = 0; i < supplierDetails.length; i++){
            for(int j = 0 ; j<supplierDetails[i].length; j++ )
                temp[i][j] = supplierDetails[i][j];
        }
        supplierDetails = temp;

    }

    public static void addSupplier(){
        grow();
        String id ;
        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       ADD SUPPLIER			|");
        System.out.println("+-------------------------------------------------------+");

        while (true) {
            System.out.print("\nSupplier ID: ");
            id = input.next();

            boolean idExists = false;
            for (int i = 0; i < supplierDetails.length; i++) {
                if (id.equals(supplierDetails[i][0])) {
                    idExists = true;
                    System.out.println("Supplier ID already exists. Try another ID!");
                    break;
                }
            }

            if (!idExists) {
                break;
            }
        }

        supplierDetails[supplierDetails.length - 1][0] = id;

        System.out.print("Supplier name: ");
        String name = input.next();

        supplierDetails[supplierDetails.length - 1][1] = name;

        System.out.print("Supplier added successfully! Do you want to add another supplier (Y/N)? ");
        String choose = input.next();

        if (choose.equals("Y") || choose.equals("y")) {
            clearConsole();
            addSupplier();
        } else if (choose.equals("N") || choose.equals("n")) {
            clearConsole();
            supplierManage();
        }
    }

    public static void supplierManage(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		       SUPPLIER MANAGE			|");
        System.out.println("+-------------------------------------------------------+");

        System.out.println("\n[1] Add Supplier	[2] Update Supplier");
        System.out.println("[3] Delete Supplier	[4] View Suppliers");
        System.out.println("[5] Search Supplier	[6] Home Page");

        System.out.print	("\nEnter a option to continue > ");
        int supplierManageOption = input.nextInt();

        switch(supplierManageOption){

            case 1:
                clearConsole();
                addSupplier();
                break;
            case 2:
                clearConsole();
                updateSupplier();
                break;
            case 3:
                clearConsole();
                deleteSupplier();
                break;
            case 4:
                clearConsole();
                viewSuppliers();
                break;
            case 5:
                clearConsole();
                searchSupplier();
                break;
            case 6:
                clearConsole();
                homePage();
                break;
        }
    }

    public static void credentialManage(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|		   CREDENTIAL MANAGE			|");
        System.out.println("+-------------------------------------------------------+");

        while(true){
            System.out.print("\nPlease enter the user name to verify it's you: ");
            String usName =  input.next();

            if (usName.equals(userName)){
                System.out.println("Hey " + userName);
                break;
            }else{
                System.out.println("User name is invalid. please try again! ");

            }
        }

        while(true){
            System.out.print("\nEnter your current password: ");
            String pass = input.next();

            if(pass.equals(password)){
                System.out.print("Enter your new password: ");
                password = input.next();
                System.out.print("Password change successfully! Do you want to go home page (Y/N): ");
                String choose = input.next();

                if(choose.equals( "y" ) || choose.equals( "Y" )){
                    clearConsole();
                    homePage();
                }else if (choose.equals( "n" ) || choose.equals( "N" )){
                    clearConsole();
                    credentialManage();

                }

            }else{
                System.out.println("incorrect password. try again!");
            }
        }
    }

    public static void homePage(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|	WELCOME TO IJSE STOCK MANAGEMENT SYSTEM		|");
        System.out.println("+-------------------------------------------------------+");

        System.out.println("\n[1] Change the Credentials	[2] Supplier Manage");
        System.out.println("[3] Stock Manage		[4] Log out");
        System.out.println("[5] Exit the system");
        System.out.print("\nEnter an option to continue > ");
        int homePageOption = input.nextInt();

        switch(homePageOption){
            case 1:
                clearConsole();
                credentialManage();
                break;
            case 2:
                clearConsole();
                supplierManage();
                break;
            case 3:
                clearConsole();
                stockManage();
                break;
            case 4:
                clearConsole();
                loginPage();
            case 5:
                clearConsole();
                System.exit(0);
                break;

        }
    }

    public static void loginPage(){

        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|			LOGIN PAGE			|");
        System.out.println("+-------------------------------------------------------+");

        while (true) {
            System.out.print("\nUser Name : ");
            String uName = input.next();

            if(uName.equals(userName)){

                while(true){
                    System.out.print("\nPassword : ");
                    String pass = input.next();

                    if(pass.equals(password)){
                        clearConsole();
                        homePage();
                    }else{
                        System.out.print("user password is invalid. please try again!");
                        System.out.println(" ");
                    }
                }
            }else{
                System.out.print("user name is invalid. please try again!");
                System.out.println(" ");
            }
        }
    }

    public static void main(String args[]){
        clearConsole();
        loginPage();

    }
}


