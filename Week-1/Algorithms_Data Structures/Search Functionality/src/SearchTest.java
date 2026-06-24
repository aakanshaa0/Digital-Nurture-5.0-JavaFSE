public class SearchTest {
    public static void main(String[] args){
        Product[] products={
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mobile", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Book", "Education")
        };

        int targetId=104;

        Product linearResult=SearchFunctionality.linearSearch(products,targetId);

        if(linearResult!=null){
            System.out.println("Linear Search Found:");
            System.out.println("Product ID: " + linearResult.getProductId());
            System.out.println("Product Name: " + linearResult.getProductName());
            System.out.println("Category: " + linearResult.getCategory());
        }
        else{
            System.out.println("Product not found using Linear Search.");
        }

        System.out.println();

        Product binaryResult=SearchFunctionality.binarySearch(products,targetId);

        if(binaryResult!=null){
            System.out.println("Binary Search Found:");
            System.out.println("Product ID: " + binaryResult.getProductId());
            System.out.println("Product Name: " + binaryResult.getProductName());
            System.out.println("Category: " + binaryResult.getCategory());
        }
        else{
            System.out.println("Product not found using Binary Search.");
        }
    }
}