package controller;

import Errors.ExceptionInsertProduct;
import Errors.ExceptionSearchProduct;
import product.Basket;
import product.Catalog;
import product.Product;

import java.util.HashMap;
import java.util.Map;


public class Controller {

    private Catalog catalog;
    private Basket basket;

    public Controller() {
        this.catalog = new Catalog();
        this.basket = new Basket();
    }

    public Controller(Catalog catalog) {
        this.catalog = catalog;
    }

    public void getList() {
        int maxColumn = 3;
        int counter = 0;
        if (! catalog.isEmpty()) {
            for (Map.Entry<Integer, HashMap<Product, Integer>> p : catalog.getCatalog()) {
                HashMap<Product, Integer> mapProduct = p.getValue();
                Product product = mapProduct.keySet().stream().findFirst().get();
                if(product.isAvailability()) {
                    String name = product.getName();
                    if (name.length() > 20) {
                        name = product.getName().substring(0, 20);
                    } else {
                        for (int i = name.length(); i <= 20; i++) {
                            name += " ";
                        }
                    }

                    String value = String.valueOf(mapProduct.values().stream().findFirst().get());

                    if (value.length() > 1) {
                        value = "ALotOf || ";
                    } else {
                        value += "      || ";
                    }
                    if (counter < maxColumn - 1) {
                        System.out.print(product.getStringId() + "  " + name + "  " + value);
                        counter++;
                    } else {
                        System.out.println(product.getStringId() + "  " + name + "  " + value);
                        counter = 0;
                    }
                }
            }
        } else {
            System.out.println("Магазин пуст");
        }
    }

    public void getBasket() {
        int maxColumn = 3;
        int counter = 0;
        if (! basket.isEmpty()) {
            for (Map.Entry<Integer, HashMap<Product, Integer>> p : basket.getCatalog()) {
                HashMap<Product, Integer> mapProduct = p.getValue();
                Product product = mapProduct.keySet().stream().findFirst().get();
                String name = product.getName();
                if (name.length() > 20) {
                    name = product.getName().substring(0, 20);
                } else {
                    for (int i = name.length(); i <= 20; i++) {
                        name += " ";
                    }
                }

                String value = String.valueOf(mapProduct.values().stream().findFirst().get());

                if (value.length() > 1) {
                    value = "ALotOf || ";
                } else {
                    value += "      || ";
                }
                if (counter < maxColumn - 1) {
                    System.out.print(product.getStringId() + "  " + name + "  " + value);
                    counter++;
                } else {
                    System.out.println(product.getStringId() + "  " + name + "  " + value);
                    counter = 0;
                }
            }
        } else {
            System.out.println("Корзина пуста");
        }
    }


    public void getViewProduct(Integer id) {
        Product p = null;
        Integer count = 0;
        try {
            Map.Entry<Product, Integer> element = catalog.productById(id).entrySet().stream().findFirst().get();
            p = element.getKey();
            count = element.getValue();
        } catch (ExceptionSearchProduct e) {
            System.out.println("С таким ID объект не найден!");
        }
        if (p != null) {
            System.out.println("   Карта товара   ");
            System.out.println(p.getName());
            System.out.println(p.getDescription());
            System.out.println("Количество для заказа: " + count);
        }
    }


    public boolean addProduct(Product p, Integer count) {
        while (true) {
            if (catalog.isProduct(p.getId())) {
                return false;
            } else {
                try {
                    catalog.setProduct(p, count);
                    return true;
                } catch (ExceptionInsertProduct e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void changeProductDescription(Integer id, String description) {
        HashMap<Product, Integer> product = new HashMap<>();

        try {
            product = catalog.productById(id);
            Product p = product.entrySet().stream().findFirst().get().getKey();
            Integer count = product.entrySet().stream().findFirst().get().getValue();
            p.setDescription(description);
            catalog.replaseBy(p, count);
        } catch (ExceptionSearchProduct e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean changeCountProductCatalog(Integer id, Integer count) {
        Map.Entry<Product, Integer> product = catalog.getProductAndCount(id);
        return catalog.addQuantity(product.getKey(), count + product.getValue());
    }

    public void hideProductCard(Integer id, boolean availability) {
        Map.Entry<Product, Integer> product = catalog.getProductAndCount(id);
        Product p = product.getKey();
        Integer count = product.getValue();
        p.setAvailability(availability);
        catalog.replaseBy(p,count);
    }

    public void deleteCardProduct(Integer id) {
        catalog.revow(id);
    }

    public void changeNameProduct(Integer id, String name) {
        Map.Entry<Product, Integer> product = catalog.getProductAndCount(id);
        Product p = product.getKey();
        Integer count = product.getValue();
        p.setName(name);
        catalog.replaseBy(p,count);
    }

    public void changeIdProduct() {
    }

    public void replaceProduct() {
    }

    public void addBasketProduct(Integer id, Integer count) {
        Product product = null;
        if (catalog.isProduct(id)) {
            try {
                product = catalog.productById(id).entrySet().stream().findFirst().get().getKey();
            } catch (ExceptionSearchProduct e){
                System.out.println("продукт не найден");
            }
            if (catalog.productCount(product, count)) {
                basket.addBasket(product, count);
                if (catalog.changeQuantity(product, count)) {
                    return;
                }
            } else {
                System.out.println("Такого количества нет в наличии");
            }
        } else {
            System.out.println("Товара с таким ID нет в каталоге");
        }
    }

    public void removeProductFromBasket(Integer id) {
        if(basket.isProduct(id)){
            basket.deleteFrom(id);
        } else {
            System.out.println("Продукта с таким Id нет в корзине ");
        }
    }

    public void newCountInBasket(Integer id, Integer count) {
        addBasketProduct(id, count);
    }

    public void nextStep() {
    }

    public void clearBasket() {
        basket.clear();
    }

    public void beackUp() {
        catalog.beackUp();
    }

    public boolean getHideProduct(Integer id) {
        Map.Entry<Product, Integer> product = catalog.getProductAndCount(id);
        Product p = product.getKey();
        Integer count = product.getValue();

        return p.isAvailability();
    }


    public void getListToAdmin() {
        int maxColumn = 3;
        int counter = 0;
        if (! catalog.isEmpty()) {
            for (Map.Entry<Integer, HashMap<Product, Integer>> p : catalog.getCatalog()) {
                HashMap<Product, Integer> mapProduct = p.getValue();
                Product product = mapProduct.keySet().stream().findFirst().get();
                String name = product.getName();
                if (name.length() > 20) {
                    name = product.getName().substring(0, 20);
                } else {
                    for (int i = name.length(); i <= 20; i++) {
                        name += " ";
                    }
                }

                String value = String.valueOf(mapProduct.values().stream().findFirst().get());

                if (value.length() > 1) {
                    value = "ALotOf || ";
                } else {
                    value += "      || ";
                }
                if (counter < maxColumn - 1) {
                    System.out.print(product.getStringId() + "  " + name + "  " + value);
                    counter++;
                } else {
                    System.out.println(product.getStringId() + "  " + name + "  " + value);
                    counter = 0;
                }
            }
        } else {
            System.out.println("Магазин пуст");
        }
    }
}
