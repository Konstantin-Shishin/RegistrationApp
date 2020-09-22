package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Объявляем об использовании следующих объектов:
    private EditText username;
    private EditText username1;
    private EditText date;
    private EditText password;
    private EditText password1;
    private Button login;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;
    public static String name;

    // Число для подсчета попыток залогиниться:

    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Связываемся с элементами нашего интерфейса:
        username = (EditText) findViewById(R.id.edit_user);
        username1 = (EditText) findViewById(R.id.edit_user1);
        date =  (EditText) findViewById(R.id.edit_date);
        password = (EditText) findViewById(R.id.edit_password);
        password1 = (EditText) findViewById(R.id.edit_password1);
        login = (Button) findViewById(R.id.button_login);
        loginLocked = (TextView) findViewById(R.id.login_locked);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

    }

    // Обрабатываем нажатие кнопки "Войти":
    public void Login(View view) {

    name=username.getText().toString();
        // Если данные введены в нужном формате
        // показываем Toast сообщение об успешном входе:
        if (username.getText().toString().length()>2 && // Имя больше 2 символов
                username1.getText().toString().length()>2 && // Фамилия больше 2 символов
                date.getText().toString().charAt(2)=='.' && // Формат даты рождения: **.**.****
                date.getText().toString().charAt(5)=='.' &&
                Character.isDigit(date.getText().toString().charAt(0)) &&
                Character.isDigit(date.getText().toString().charAt(1)) &&
                Character.isDigit(date.getText().toString().charAt(3)) &&
                Character.isDigit(date.getText().toString().charAt(4)) &&
                Character.isDigit(date.getText().toString().charAt(6)) &&
                Character.isDigit(date.getText().toString().charAt(7)) &&
                Character.isDigit(date.getText().toString().charAt(8)) &&
                Character.isDigit(date.getText().toString().charAt(9)) &&
                password.getText().toString().equals(password1.getText().toString()) && // Пароль и подтверждение пароля должны совпадать
                !password.getText().toString().toLowerCase().equals(password.getText().toString()) && //Пароль должен состоять из
                !password.getText().toString().toUpperCase().equals(password.getText().toString()) // символов верхнего и нижнего регистров
        ) {
            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            // Выполняем переход на экран "Приветствие":
            Intent intent = new Intent(MainActivity.this,Second.class);
            startActivity(intent);
        }

        // В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;

            // Делаем видимыми текстовые поля, указывающие на количество оставшихся попыток:
            attempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

            // Когда выполнено 3 безуспешных попытки залогиниться,
            // делаем видимым текстовое поле с надписью, что все пропало и выставляем
            // кнопке настройку невозможности нажатия setEnabled(false):
            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLocked.setVisibility(View.VISIBLE);
                loginLocked.setBackgroundColor(Color.RED);
                loginLocked.setText("Вход заблокирован!!!");
            }
        }
    }
}