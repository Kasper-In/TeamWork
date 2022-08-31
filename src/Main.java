import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] food = {"Хлеб", "Масло", "Чай", "Вода", "Колбаса"};
        int[] basket = new int[food.length];
        int[] price = {59, 150, 243, 30, 580};
        String[] sales = {"Вода", "Чай"};
        int sumFood = 0;
        System.out.println("Список товаров доступных для добавления в корзину:");
        StringBuilder listFood = new StringBuilder();
        for (int i = 0; i < food.length; i++) {
            listFood.append((i + 1) + ". " + food[i] + " " + price[i] + " руб/шт \n");
        }
        System.out.println(listFood.toString());
        while (true) {
            System.out.println("Выберите товар путем ввода его номера и количества. " +
                    "Если хотите уменьшить количество товара в корзине, введите количество с минусом");
            System.out.println("Для завершения покупок введите end");
            String inputStr = scanner.nextLine();
            if (inputStr.equals("end")) break;
            else if (inputStr == "") continue;
            String[] parts = inputStr.split(" ");
            if (parts.length != 2) {
                System.out.println("Вы некорректно ввели номер товара и его количество!!");
                continue;
            }
            int numFood;
            int countFood;
            try {
                numFood = Integer.parseInt(parts[0]) - 1;
                countFood = Integer.parseInt(parts[1]);
            } catch (NumberFormatException error) {
                System.out.println("При вводе допущена ошибка. Номер товара и количество должны быть указаны числами через пробел!");
                continue;
            }
            if (numFood < 0 || numFood > (food.length - 1)) {
                System.out.println("Такого номера товара нет в предложенном перечне");
                continue;
            }
            //Уменьшение товара в корзине
            if (countFood < 0 && basket[numFood] < Math.abs(countFood)){
                System.out.println("В корзине нет столько товара для уменьшения. Сейчас в корзине " + basket[numFood] + " шт.");
                System.out.println();
            } else if (countFood == 0) {               // обнуление товара в корзине
                sumFood -= basket[numFood] * price[numFood];
                basket[numFood] = 0;
            } else {
                basket[numFood] += countFood;
                sumFood += countFood * price[numFood];
            }
        }
        if (sumFood == 0) {
            System.out.println("Ваша корзина пуста");
        } else {
            System.out.println("Ваш заказ: \n");
            listFood.setLength(0);
            sumFood = 0;
            for (int i = 0; i < basket.length; i++) {
                if (!(basket[i] == 0)) {
                    if (basket[i] > 2 && sales.length > 0) {
                        boolean find = false;
                        for (String sale : sales) {
                            if (sale.equals(food[i])) {
                                find = true;
                                int countSales = basket[i] / 3;
                                listFood.append(food[i] + " " + basket[i] + " шт. * " + price[i] + " руб = " + (basket[i] * price[i]) + " руб.");
                                listFood.append(" скидка по акции товара составила " + countSales * price[i] + " руб. \n");
                                sumFood += (basket[i] * price[i]) - (countSales * price[i]);
                            }
                        }
                        if (find != true) {
                            listFood.append(food[i] + " " + basket[i] + " шт. * " + price[i] + " руб = " + (basket[i] * price[i]) + " руб. \n");
                            sumFood += basket[i] * price[i];
                        }
                    } else {
                        listFood.append(food[i] + " " + basket[i] + " шт. * " + price[i] + " руб = " + (basket[i] * price[i]) + " руб. \n");
                        sumFood += basket[i] * price[i];
                    }
                }
            }
            listFood.append("Итоговая сумма покупки = " + sumFood + " руб");
            System.out.println(listFood.toString());
        }
    }
}
