package com.example.carhelper.UIHelper;

public class AdviceService {

    public String getAdviceMess(int i){
        String res = "На этом всё!";
        switch (i){
            case 0:
                res = "Чтобы удалить автомобиль, нажмите и удерживайте нужную запись.";
                break;
            case 1:
                res = "Приложение позволяет запланировать ТО. Для этого перейдите в основное меню и используйте пункт 'ПЛАНИРОВАНИЕ ТО'";
                break;
            case 2:
                res = "Для удобной навигации воспользуйтесь пунктом меню 'КАРТА'";
                break;
            case 3:
                res = "Вам кажется, что автомобиль потребляет слишком много топлива? Воспользуйтесь сервисом 'Калькулятор расхода топлива!'";
                break;
            case 4:
                res = "Не уверены, как поступить в дорожной ситуации? Справочник ПДД вам в помощь!";
                break;
            case 5:
                res = "Я всего лишь курсач (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧";
                break;

        }
        return res;
    }
}