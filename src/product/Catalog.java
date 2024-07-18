package product;

import Errors.ExceptionInsertProduct;
import Errors.ExceptionSearchProduct;
import dto.ProductDto;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Catalog{
    private String file = "product.txt"; // файл дампа каталога
    private HashMap<Integer,HashMap<Product, Integer>> catalog = new HashMap<>();

    public Catalog() {
        loadToFile();
    }

    public boolean isEmpty() {
        return catalog.isEmpty();
    }
//
    public Set<Map.Entry<Integer,HashMap<Product, Integer>>> getCatalog() {
        return catalog.entrySet();
    }
//
    public void setProduct(Product p, Integer count) throws ExceptionInsertProduct {
        if(!catalog.containsKey(p.getId())) {
            HashMap<Product, Integer> productNew = new HashMap<>();
            productNew.put(p, count);
            catalog.put(p.getId(), productNew);
        } else {
            throw new ExceptionInsertProduct();
        }
    }

    public void replaseBy(Product p, Integer count) {
        HashMap<Product, Integer> productNew = new HashMap<>();
        productNew.put(p, count);
        if(catalog.containsKey(p.getId())) {
            catalog.remove(p.getId());
            catalog.put(p.getId(),productNew);
        }
        catalog.put(p.getId(), productNew);
    }
//
    public boolean changeQuantity(Product p, Integer count) {
        // Вычитание при перемещении в корзину
        try {
            HashMap<Product, Integer> productNew = productById(p.getId());
            Integer x = productNew.get(p);
            x = x - count;
            productNew.put(p, x);
            return catalog.replace(p.getId(), productNew) != null;
        } catch (ExceptionSearchProduct e) {
            System.out.println("Продукт не найден");
        }
        return false;

    }
//
    public HashMap<Product, Integer> productById(Integer id) throws ExceptionSearchProduct {
        if(catalog.containsKey(id)){
            return catalog.get(id);
        } else {
            throw new ExceptionSearchProduct();
        }
    }

    public void deleteFrom(Integer id) {
        catalog.remove(id);
    }

    public void beackUp() {
        try(FileWriter writer = new FileWriter(file)){
            BufferedWriter bw = new BufferedWriter(writer);
            for(Map.Entry<Integer, HashMap<Product, Integer>> line : catalog.entrySet()){
                Map.Entry<Product, Integer> element = line.getValue().entrySet().stream().findFirst().get();
                Product product = element.getKey();
                Integer count = element.getValue();

                ProductDto productDto = new ProductDto(product.getId(), product, count);
                bw.write(productDto.getProduct() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadToFile() {
        // 671;product:000000671,Товар_7,true;2
        try (FileReader reader = new FileReader(file)){
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                String[] p = line[1].split(":")[1].split(",");
                Product product = new Product(Integer.parseInt(line[0]),p[1], p[2], Boolean.parseBoolean(p[3]));
                HashMap<Product, Integer> map = new HashMap<>();
                map.put(product,Integer.parseInt(line[2]));

                catalog.put(Integer.parseInt(line[0]), map);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //
    public boolean productCount(Product product, Integer count) {
        Map.Entry<Product, Integer> x = getProductAndCount(product.getId());
        return x.getValue() >= count ? true : false;
    }
//
    public boolean isProduct(Integer id) {
        return catalog.containsKey(id);
    }

    public Map.Entry<Product, Integer> getProductAndCount(Integer id) {
        return catalog.get(id).entrySet().stream().findFirst().get();
    }

    public boolean addQuantity(Product p, Integer count) {
        HashMap<Product, Integer> product = new HashMap<>();
        product.put(p, count);
        return catalog.replace(p.getId(), product) != null ? true : false;
    }

    public void revow(Integer id) {
        catalog.remove(id);
    }
}
