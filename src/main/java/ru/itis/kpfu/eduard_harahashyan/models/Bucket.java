package ru.itis.kpfu.eduard_harahashyan.models;

import java.util.HashMap;
import java.util.Map;

public class Bucket {
    private HashMap<Integer, Product> products;
    private int productCount;
    private int totalCost;

    public Bucket() {
        products = new HashMap<Integer, Product>();
        productCount = 0;
        totalCost = 0;
    }

    public void addProduct(Product product, int userQuantity) throws Exception {
        int realQuantity = product.getIntQuantity();
        int productId = product.getId();

        if (realQuantity < userQuantity) {
            throw new Exception("Wait 1 week for more");
        }
        if (productCount == 7) {
            throw new Exception("Need more gold");
        }

        product.setQuantity(userQuantity);

        if (!products.containsKey(productId)) {
            productCount++;
            totalCost += userQuantity * product.getCost();
            products.put(productId, product);

        } else {
            int oldQuantity = products.get(productId).getIntQuantity();

            if (oldQuantity + userQuantity <= realQuantity) {
                totalCost += userQuantity * product.getCost();
                product.setQuantity(userQuantity + oldQuantity);
                products.put(productId, product);

            } else {
                throw new Exception("Wait 1 week for more");
            }
        }
    }

    public boolean removeProduct(int id) {
        if (products.containsKey(id)) {
            Product p = products.get(id);
            productCount--;
            totalCost -= p.getIntQuantity() * p.getCost();
            products.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        productCount = 0;
        totalCost = 0;
        products.clear();
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getTotalPrice() {
        return totalCost;
    }

}
