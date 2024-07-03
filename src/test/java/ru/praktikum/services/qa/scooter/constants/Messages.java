package ru.praktikum.services.qa.scooter.constants;

public class Messages {
    public static final String OK_MESSAGE_RESPONSE = "{ok=true}";
    public static final String THIS_LOGIN_ALREADY_EXIST_MESSAGE_RESPONSE = "{code=409, message=Этот логин уже используется. Попробуйте другой.}";
    public static final String INSUFFICIENT_DATA_TO_CREATE = "{code=400, message=Недостаточно данных для создания учетной записи}";
    public static final String INSUFFICIENT_DATA_TO_LOG_IN = "{code=400, message=Недостаточно данных для входа}";
    public static final String USER_NOT_FOUND_TO_LOG_IN = "{code=404, message=Учетная запись не найдена}";
}
