package view;

import authorization.Authorization;
import controller.Controller;
import product.Product;

import java.util.Scanner;

public class View {

    private final Controller controller = new Controller();


    public void startShop() {

        Authorization authorization = new Authorization();
        int role = authorization.authentication();

        if (role == 1) {
            // admin
            admin();
        } else {
            // user
            user();
        }

    }

    public void admin() {
        while (true) {
            String str = boltalca("Введите номер пункта для соответствующего действия: \n" +
                    "1. Добавить товар \n" +
                    "2. Изменить описание \n" +
                    "3. Изменить количество товара \n" +
                    "4. Скрыть/открыть карту товара \n" +
                    "5. Удалить карту товара \n" +
                    "6. Изменить название товара \n" +
                    "7. Выйти в предыдущее меню \n" +
                    "8. Показать список товаров \n" +
                    "9. Сделать запись каталога в файл");

            switch (str) {
                case "1":
                    Product p = getProduct();
                    Integer count = getCount();
                    controller.addProduct(p, count);
                    break;
                case "2":
                    String description = boltalca("Введите новое описание для продукта");
                    Integer id = getId();
                    controller.changeProductDescription(id, description);
                    break;
                case "3":
                    Integer[] x = getIdAndCount();
                    controller.changeCountProductCatalog(x[0], x[1]);
                    break;
                case "4":
                    id = getId();
                    boolean nowAvailability = controller.getHideProduct(id);
                    System.out.println("Текущее значение видимости у указонного продукта:" + nowAvailability);
                    controller.hideProductCard(id, getAvailabillity());
                    break;
                case "5":
                    id = getId();
                    controller.deleteCardProduct(id);
                    break;
                case "6":
                    id = getId();
                    String name = getName();
                    controller.changeNameProduct(id, name);
                    break;
                case "7":
                    startShop();
                    break;
                case "8":
                    controller.getListToAdmin();
                    break;
                case "9":
                    controller.beackUp();
                    break;
                default:
                    System.out.println("Команда не ясна");
            }
        }
    }

    public void user() {

        while (true) {
            String index = boltalca("\nВведите номер пункта для соответствующего действия: \n" +
                    "1. Просмотреть каталог \n" +
                    "2. Просмотреть корзину \n" +
                    "3. Добавить товар в корзину \n" +
                    "4. Убрать товар из корзины \n" +
                    "5. Добавить количество товара в корзину \n" +
                    "6. Очистить корзину \n" +
                    "7. Заполнить адрес доставки и оплату \n" +
                    "8. Выйти в предыдущее меню");

            switch (index) {
                case "1":
                    controller.getList();
                    break;
                case "2":
                    controller.getBasket();
                    break;
                case "3":
                    Integer[] x = getIdAndCount();
                    controller.addBasketProduct(x[0], x[1]);
                    break;
                case "4":
                    controller.removeProductFromBasket(getId());
                    break;
                case "5":
                    x = getIdAndCount();
                    controller.newCountInBasket(x[0], x[1]);
                    break;
                case "6":
                    controller.clearBasket();
                    break;
                case "7":
                    controller.nextStep();
                    break;
                case "8":
                    startShop();
                    break;
                default:
                    System.out.println("Команда не ясна");
            }
        }
    }

    private Integer[] getIdAndCount() {
        while (true) {
            String str = boltalca("Введите в одну строку Id товара и через пробел желаемое количество: ");
            Integer[] x = new Integer[2];
            if (str.contains(" ")) {
                try {
                    x[0] = Integer.parseInt(str.split(" ")[0]);
                    x[1] = Integer.parseInt(str.split(" ")[1]);
                    return x;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не корректный ввод");
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Integer getId() {
        while(true) {
            String str = boltalca("Введите Id товара");
            try{
            Integer id = null;
            id = Integer.parseInt(str);
            return id;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String boltalca(String str) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        return scanner.nextLine();
    }

    private Integer getCount() {
        while (true) {
            String str = boltalca("Введите соличество продукта");
            try{
                Integer count = null;
                count = Integer.parseInt(str);
                return count;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean getAvailabillity() {
        while (true) {
            try {
                String availability = boltalca("Введите true если продкт отображается в каталоге и false если он скрыт");
                boolean availab = Boolean.parseBoolean(availability);
                return availab;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getName() {
        return boltalca("Введите название продукта");
    }

    private Product getProduct() {
        while(true) {
            Integer id = getId();
            String name = getName();
            String description = boltalca("Введите описание продукта");
            boolean availabillity = getAvailabillity();
            return new Product(id, name, description, availabillity);
        }
    }
}
