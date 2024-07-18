package product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Basket extends Catalog {
    private HashMap<Integer, HashMap<Product, Integer>> basket = new HashMap<>();

    public Basket() {
    }

    public void addBasket(Product product, Integer count) {
        HashMap<Product, Integer> map = new HashMap<>();
        map.put(product, count);
        if(basket.containsKey(product.getId())) {
            HashMap<Product, Integer> x = basket.get(product.getId());
            Integer oldCount = x.get(product);
            x.replace(product, count + oldCount);
            basket.put(product.getId(), x);
        } else {
            basket.put(product.getId(), map);
        }
    }

    public void clear() {
        basket.clear();
    }

    @Override
    public Set<Map.Entry<Integer, HashMap<Product, Integer>>> getCatalog() {
        return basket.entrySet();
    }

    @Override
    public void deleteFrom(Integer id) {
        basket.remove(id);
    }

    @Override
    public boolean isEmpty() {
        return basket.isEmpty();
    }
}
