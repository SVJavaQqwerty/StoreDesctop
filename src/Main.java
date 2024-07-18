import Errors.ExceptionInsertProduct;
import view.View;
import product.Catalog;
import product.Product;

public class Main {
    public static void main(String[] args) {
        // Вам предстоит сделать онлайн магазин, без графического интерфейса, обладающего следующими функциями:
        // ●Каталог товаров
        // ●Авторизация почта и пароль (данные возможно хранить в простом незашифрованном виде)
        // ●Реализовать вывод каталога
        // ●Добавление товара по ID
        // ●Возможность очистки корзины


        // Интерфейс дружелюбный, просто нужно запустить!

        View view = new View();
        view.startShop();

        // Про роли в задании небыло, но куда без них...
        // Парсер для почты не писал, так что подойдет любой логин
        // Разделение на слои приложения соответствует стилю Model - View - Controller (мог напутать...)
        // Основное преимущество такой разработки что каждый слой отвечает за свой уровень и здесь будет удобно заменять.
        // Например для того что бы выводить не в консоль а в формат например html нужно изменить только представление.


    }



    private static Catalog fullCatalog() {
// Написал в самом начале для тестов удалять жалко )) на случай если файл потеряется...
        Catalog catalog = new Catalog();
        try {
            catalog.setProduct(new Product(01231, "Товар_1", "Какое-то описание1", true), 2);
            catalog.setProduct(new Product(01232, "Товар_2", "Какое-то описание2", true), 2);
            catalog.setProduct(new Product(01233, "Товар_3", "Какое-то описание3", true), 2);
            catalog.setProduct(new Product(01234, "Товар_4", "Какое-то описание4", true), 2);
            catalog.setProduct(new Product(01235, "Товар_5", "Какое-то описание5", true), 2);
            catalog.setProduct(new Product(01236, "Товар_6", "Какое-то описание6", true), 2);
            catalog.setProduct(new Product(01237, "Товар_7", "Какое-то описание7", true), 2);
            catalog.setProduct(new Product(023253245, "Товар_8", "Какое-то описание8", true), 2);
            catalog.setProduct(new Product(0234256, "Товар_9", "Какое-то описание9", true), 2);
            catalog.setProduct(new Product(01240, "Товар_10", "Какое-то описание10", true), 2);
            catalog.setProduct(new Product(01241, "Товар_11", "Какое-то описание11", true), 2);
            catalog.setProduct(new Product(01242, "Товар_12", "Какое-то описание12", true), 2);
            catalog.setProduct(new Product(01243, "Товар_13", "Какое-то описание13", true), 2);
            catalog.setProduct(new Product(01244, "Товар_14", "Какое-то описание14", true), 2);
            catalog.setProduct(new Product(01345, "Товар_15", "Какое-то описание15", true), 100);
        } catch (ExceptionInsertProduct e) {
            System.out.println("Товар с таким ID уже есть");
        }
        return catalog;
    }
}

