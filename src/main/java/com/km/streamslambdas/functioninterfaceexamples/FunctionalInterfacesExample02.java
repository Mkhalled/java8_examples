package com.km.streamslambdas.functioninterfaceexamples;

import com.km.streamslambdas.Category;
import com.km.streamslambdas.ExampleData;
import com.km.streamslambdas.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionalInterfacesExample02 {

    public static void main(String[] args) {
        List<Product> products= ExampleData.getProducts();

        Map<Category,List<Product>> productByCategory=new HashMap<>();

        // Without functional interfaces and lambda expressions.
        for(Product product:products){

            /*
            // Check if the map already has a List for this category; if not, add one.
            if(!productByCategory.containsKey(category)){
                productByCategory.put(category,new ArrayList<>());
            }
              // Add the product to the appropriate list in the map.
                productByCategory.get(category).add(product);
            */

            // With Map.computeIfAbsent()
            // computeIfAbsent() gets the existing List for the category, or calls the given
            // Function<Category, List<Product>> to create the List if there is no entry in the Map for the category.
            productByCategory.computeIfAbsent(product.getCategory(),c->new ArrayList<>()).add(product);


            // Map.forEach() takes a BiConsumer (a Consumer which takes two parameters); the key and value of each entry.
            productByCategory.forEach((category,ps)->{
                System.out.println("Category : "+ category);
                ps.forEach(pr-> System.out.println("--"+ pr.getName() ) );

            });
        }


    }
}
